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
function getNewsList(tbodyWrap) {
    let data;
    let ajaxParams = {};
    ajaxParams.method = 'GET';
    ajaxParams.url = '/main.json';
    ajaxParams.params = null;

    data = sendAjax(ajaxParams);

    if (data === null) {
        return;
    }

    buildNewsList(data, tbodyWrap);
}

// Build
function buildNewsList(data, tbodyWrap) {
    Object.entries(data).forEach(([key, value]) => {
        var $tr = $('<tr>').append(
            $('<td><lable>').text(''),
            $(`<td><img src="${value.filename}" class="prod_img">`),
            $('<td>').text(value.title),
            $('<td>').text(value.information),
            $('<td>').text(value.date),
            $('<td><lable>').text('')
        );
        $tr.appendTo(tbodyWrap);
        // $tr.wrap('<p>').html();
        console.log(`${key} ${value.id}`);
    });
}

$(document).ready(function (response) {
    $('.menu [data-id="posts"]').on('click', function (e) {
        e.preventDefault();

        let tbodyWrap = $('.adm_content[data-id="posts"] table tbody');
        let newsList;

        // Clear table list
        tbodyWrap.html('');

        // Get news list
        getNewsList(tbodyWrap);


        /*let url = $(this).attr('action');
        let data = {};

        let file = $('[name="filename"]').prop('files')[0];


        data.name = $("[name='name']").val();
        data.parentCategorySlug = $("[name='parentCategorySlug']").val();
        data.image = $('[name="fileBase64"]').val();

        $.ajax({
            type: 'POST',
            url: url,
            processData: false,
            crossDomain: true,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Authorization", Cookies.get('token'));
            },
            success: function () {
                window.location.replace('/panel');
            },
            error: function (xhr, str) {
                alert('Возникла ошибка: ' + xhr.responseCode);
            }
        });*/
    });
});
