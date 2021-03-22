function sendAjax(settings) {
    let response;

    $.ajax({
        type: settings.method,
        url: settings.url,
        async: false,
        processData: false,
        crossDomain: true,
        contentType: "application/json; charset=utf-8",
        data: settings.params ? JSON.stringify(settings.params) : null,
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", Cookies.get('token'));
        },
        success: function (data) {
            response = data;
        },
        error: function (xhr, str) {
            alert('Возникла ошибка: ' + xhr.responseCode);
        }
    });

    return response ?? null;
}

/**
 * News
 */
// Get
function getPostsList(tbodyWrap) {
    let data;
    let ajaxParams = {};
    ajaxParams.method = 'GET';
    // ajaxParams.url = '/main';
    ajaxParams.url = '/main';
    ajaxParams.params = null;

    data = sendAjax(ajaxParams);

    if (data === null) {
        return;
    }

    buildPostsList(data, tbodyWrap);
}

// Build
function buildPostsList(data, tbodyWrap) {
    Object.entries(data).forEach(([key, value]) => {
        let $tr = $('<tr>').append(
            $('<td>').html('<label><input type="checkbox" name="posts-checks" value="' + value.id + '"><span></span></label>'),
            $(`<td><img src="${value.filename}" class="prod_img">`),
            $('<td>').text(value.title),
            $('<td>').text(value.information),
            $('<td>').text(value.localDate),
            $('<td>').html('<img src="images/delete.svg">')
        );
        $tr.appendTo(tbodyWrap);
    });
}

function postsDelete(arrId) {
    let data;
    let ajaxParams = {};
    ajaxParams.method = 'DELETE';
    ajaxParams.url = '/admin/delete_news';
    ajaxParams.params = arrId;

    data = sendAjax(ajaxParams);
}

$(document).ready(function (response) {
    // Open News page
    $('.menu [data-id="posts"]').on('click', function (e) {
        e.preventDefault();

        let tbodyWrap = $('.adm_content[data-id="posts"] table tbody');

        // Clear table list
        tbodyWrap.html('');

        // Get news list
        getPostsList(tbodyWrap);
    });

    // Delete checked posts
    $('.adm_content [data-action="posts-delete"]').on('click', function (e) {
        e.preventDefault();

        let arrId = [];
        let checkedItems = $('[name="posts-checks"]:checked');

        if (checkedItems.length === 0) {
            alert("Выберите необходимые посты");
            return false;
        }

        $.each(checkedItems, function(key, item) {
            let object = {};
            object.id = item.value;
            arrId.push(object);
        });

        postsDelete(arrId[0]);
    })
});
