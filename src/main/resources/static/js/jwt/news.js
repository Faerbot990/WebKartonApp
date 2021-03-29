let postDelete = false;

// Ajax
function sendAjax(settings) {
    return $.ajax({
        type: settings.method,
        url: settings.url,
        processData: false,
        crossDomain: true,
        contentType: "application/json; charset=utf-8",
        data: settings.params ? JSON.stringify(settings.params) : null,
        dataType: settings.dataType,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", Cookies.get('token'));
        },
        success: function (response) {
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
function getPostsList() {
    let data;
    let tbodyWrap = $('.adm_content[data-id="posts"] table tbody');
    let ajaxParams = {};
    ajaxParams.method = 'GET';
    ajaxParams.url = '/main';
    ajaxParams.params = null;
    ajaxParams.dataType = 'json';

    sendAjax(ajaxParams).done(function (response) {
        data = response;

        if (data === null) {
            return;
        }

        // Clear table list
        tbodyWrap.html('');

        buildPostsList(data, tbodyWrap);
    });
}

// Add
function postAdd(data) {
    let ajaxParams = {};
    ajaxParams.method = 'POST';
    ajaxParams.url = '/admin/add_news';
    ajaxParams.params = data;
    ajaxParams.dataType = 'json';
    ajaxParams.successDefaultParam = "";

    sendAjax(ajaxParams).done(function () {});
}

// Delete
function postsDelete(arrId) {
    let ajaxParams = {};
    ajaxParams.method = 'DELETE';
    ajaxParams.url = '/admin/delete_news';
    ajaxParams.params = arrId;
    ajaxParams.dataType = 'text';

    sendAjax(ajaxParams).done(function () {});
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

        // Get news list
        getPostsList();
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

        postAdd(data);

        setTimeout(function () {
            // Reset table list
            $('.menu [data-id="posts"]').click();
        }, 1000);
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

        $.each(checkedItems, function (key, item) {
            let object = {};
            object.id = item.value;
            arrId.push(object);
        });

        // Delete posts
        $.each(arrId, function (key, item) {
            let isLastElement = key == arrId.length -1;

            if (isLastElement) {
                console.log('last item')
            } else {
                console.log('Not last item');
            }
            postsDelete(item);
        });

        setTimeout(function () {
            // Reset table list
            $('.menu [data-id="posts"]').click();
        }, 1000);
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

        // Delete post
        postsDelete(object);

        setTimeout(function () {
            // Reset table list
            $('.menu [data-id="posts"]').click();
        }, 1000);
    });
});
