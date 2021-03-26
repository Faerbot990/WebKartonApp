let defaultParam;

// Ajax
function sendAjax(settings) {
    return $.ajax({
        type: settings.method,
        url: settings.url,
        // async: false,
        processData: false,
        crossDomain: true,
        contentType: "application/json; charset=utf-8",
        data: settings.params ? JSON.stringify(settings.params) : null,
        dataType: settings.dataType,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", Cookies.get('token'));
        },
        success: function (response) {
            // defaultParam = data ? data : settings.successDefaultParam;
        },
        error: function (xhr) {
            alert('Возникла ошибка: ' + xhr.responseCode);
        }
    });
}

// Encode image to base64
function encodeImageFileAsURL(cb) {
    return function () {
        var file = this.files[0];
        var reader = new FileReader();
        reader.onloadend = function () {
            cb(reader.result);
        }
        reader.readAsDataURL(file);
    }
}

/**
 * News
 */
// Get
function getPostsList(tbodyWrap) {
    let data;
    let ajaxParams = {};
    ajaxParams.method = 'GET';
    ajaxParams.url = '/main';
    ajaxParams.params = null;
    ajaxParams.dataType = 'json';
    ajaxParams.successDefaultParam = null;

    sendAjax(ajaxParams).done(function(response){
        console.log(response);
        data = ajaxParams;

        if (data === null) {
            return;
        }

        buildPostsList(data, tbodyWrap);
    });
    // data = sendAjax(ajaxParams);

    // if (data === null) {
    //     return;
    // }
    //
    // buildPostsList(data, tbodyWrap);
}

// Add
function postAdd(data) {
    let ajaxParams = {};
    ajaxParams.method = 'POST';
    ajaxParams.url = '/admin/add_news';
    ajaxParams.params = data;
    ajaxParams.dataType = 'json';
    ajaxParams.successDefaultParam = "";

    return sendAjax(ajaxParams);
}

// Delete
function postsDelete(arrId) {
    let ajaxParams = {};
    ajaxParams.method = 'DELETE';
    ajaxParams.url = '/admin/delete_news';
    ajaxParams.params = arrId;
    ajaxParams.dataType = 'text';
    ajaxParams.successDefaultParam = true;

    return console.log(typeof sendAjax(ajaxParams));
}

// Build
function buildPostsList(data, tbodyWrap) {
    Object.entries(data).forEach(([key, value]) => {
        let $tr = $('<tr>').attr('data-id', value.id).append(
            $('<td>').html('<label><input type="checkbox" name="posts-checks" value="' + value.id + '"><span></span></label>'),
            $(`<td><img src="${value.filename}" class="prod_img">`),
            $('<td>').text(value.title),
            $('<td>').text(value.information),
            $('<td>').text(value.localDate),
            $('<td>').html('<img src="images/delete.svg" data-post-delete="' + value.id + '">')
        );
        $tr.appendTo(tbodyWrap);
    });
}

$(document).ready(function (response) {
    // Open Posts page
    $('.menu [data-id="posts"]').on('click', function (e) {
        e.preventDefault();

        let tbodyWrap = $('.adm_content[data-id="posts"] table tbody');

        // Clear table list
        tbodyWrap.html('');

        // Get news list
        getPostsList(tbodyWrap);
    });

    // Post Image
    $('#add-posts input[name="filename"]').on('change', encodeImageFileAsURL(function (base64Img) {
        let inputFilename = $('#add-posts input[name="filename"]');
        inputFilename.after("<input type='hidden' name='fileBase64' value='" + base64Img + "'>");
        inputFilename.closest(".current.flx").find('> img').remove();
        inputFilename.closest(".current.flx").prepend("<img src='images/delete.svg' class='delete'>").prepend("<img src='" + base64Img + "'>");
    }));

    // Post add
    $('#add-posts').on('submit', function (e) {
        e.preventDefault();

        let data = {};

        data.title = $(this).find("[name='title']").val();
        data.information = $(this).find("[name='information']").val();
        data.filename = $(this).find('[name="fileBase64"]').val();

        if (postAdd(data) === true) {
            window.location.replace('/panel');
        }
    });

    // Delete checked posts
    $('.adm_content[data-id="posts"] [data-action="posts-delete"]').on('click', function (e) {
        e.preventDefault();

        if (confirm("Вы уверены, что хотите удалить выбранные посты?") === false) {
            return false;
        }

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

        $.each(arrId, function (key, item) {
            if (console.log(typeof postsDelete(item)) === true) {
                $('.adm_content[data-id="posts"] tr[data-id="' + item.id + '"]').remove();
            }
        });
    });

    // Delete item post
    $('.adm_content[data-id="posts"]').on('click', '[data-post-delete]', function (e) {
        e.preventDefault();

        if (confirm("Вы уверены, что хотите удалить пост?") === false) {
            return false;
        }

        let item = $(this).attr('data-post-delete');
        let object = {};
        object.id = item;

        if (postsDelete(object) === true) {
            $('.adm_content[data-id="posts"] tr[data-id="' + item.id + '"]').remove();
        }
    });
});
