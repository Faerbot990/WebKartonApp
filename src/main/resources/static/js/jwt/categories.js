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

// Сategories Get
function getCategoryList() {
    let data;
    let tbodyWrap = $('.adm_content[data-id="categories"] .categories_list tbody');
    let ajaxParams = {};
    ajaxParams.method = 'GET';
    ajaxParams.url = '/categories';
    ajaxParams.params = null;
    ajaxParams.dataType = 'json';

    sendAjax(ajaxParams).done(function (response) {
        data = response;

        if (data === null) {
            return;
        }

        // Clear table list
        tbodyWrap.html('');

        buildCategoryList(data, tbodyWrap);
    });
}

// News Add
function postAdd(data) {
    let ajaxParams = {};
    ajaxParams.method = 'POST';
    ajaxParams.url = '/admin/add_news';
    ajaxParams.params = data;
    ajaxParams.dataType = 'json';

    sendAjax(ajaxParams).done(function () {
        // Reset table list
        $('.menu [data-id="posts"]').click();
    });
}

// News Edit
function postsGetItem(data) {
    let ajaxParams = {};
    ajaxParams.method = 'GET';
    ajaxParams.url = '/main/news/' + data.id;
    ajaxParams.dataType = 'json';

    return sendAjax(ajaxParams);
}

// News Update
function postUpdate(data) {
    let ajaxParams = {};
    ajaxParams.method = 'PUT';
    ajaxParams.url = '/admin/update_news';
    ajaxParams.params = data;
    ajaxParams.dataType = 'text';

    sendAjax(ajaxParams).done(function () {
        // Reset table list
        $('.menu [data-id="posts"]').click();
    });
}

// News Delete
function postsDelete(data, flagAction = false) {
    let ajaxParams = {};
    ajaxParams.method = 'DELETE';
    ajaxParams.url = '/admin/delete_news';
    ajaxParams.params = data;
    ajaxParams.dataType = 'text';

    sendAjax(ajaxParams).done(function () {
        if (flagAction) {
            // Reset table list
            $('.menu [data-id="posts"]').click();
        }
    });
}

// Categories Build
function buildCategoryList(data, tbodyWrap) {
    Object.entries(data).forEach(([key, value]) => {
        let $tr = $('<tr>').attr('data-id', value.slug).append(
            $('<td>').text(value.name),
            $('<td>').html('<img src="images/edit.svg" data-category-edit="' + value.slug + '"><img src="images/delete.svg" data-category-delete="' + value.slug + '">')
        );
        $tr.appendTo(tbodyWrap);
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

$(document).ready(function () {
    // Action Open Category page
    $('.menu [data-id="categories"]').on('click', function (e) {
        e.preventDefault();

        // Get category list
        getCategoryList();
    });

    // Action Category add
    $('#add-posts').on('submit', function (e) {
        e.preventDefault();

        let data = {};

        data.title = $(this).find("[name='title']").val();
        data.information = $(this).find("[name='information']").val();
        data.filename = $(this).find('[name="fileBase64"]').val();

        postAdd(data);

        $(this).trigger("reset");
    });

    // Action Edit post
    $('.adm_content[data-id="posts"]').on('click', '[data-post-edit]', function (e) {
        e.preventDefault();

        let item = $(this).attr('data-post-edit');
        let object = {};
        object.id = item;

        // Get edit post
        let post = postsGetItem(object);
        post.done(function (data) {
            let formEdit = $('#edit-posts');
            let inputFilename = formEdit.find('input[name="filename"]');
            $.each(data, function(name, value) {
                if (
                    name === 'localDate'
                    || name === 'filename'
                ) {
                    return;
                }

                formEdit.find('[name="'+ name +'"]').val(value);
            });

            inputFilename.after("<input type='hidden' name='fileBase64' value='" + data.filename + "'>");
            inputFilename.closest(".current.flx").find('> img').remove();
            inputFilename.closest(".current.flx").prepend("<img src='images/delete.svg' class='delete'>").prepend("<img src='" + data.filename + "'>");

            $('.change_window[data-id="edit_posts"]').click();
        });
    });

    // Action Update post
    $('#edit-posts').on('submit', function (e) {
        e.preventDefault();

        let data = {};

        data.id = $(this).find("[name='id']").val();
        data.title = $(this).find("[name='title']").val();
        data.information = $(this).find("[name='information']").val();
        data.filename = $(this).find('[name="fileBase64"]').val();

        postUpdate(data);

        $(this).trigger("reset");
    });

    // Action Delete checked posts
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
            let isLastElement = key === arrId.length - 1;

            postsDelete(item, isLastElement);
        });
    });

    // Action Delete item post
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
    });

    // Category Add Image
    $('#add-posts input[name="filename"]').on('change', encodeImageFileAsURL(function (base64Img) {
        let inputFilename = $('#add-posts input[name="filename"]');
        inputFilename.after("<input type='hidden' name='fileBase64' value='" + base64Img + "'>");
        inputFilename.closest(".current.flx").find('> img').remove();
        inputFilename.closest(".current.flx").prepend("<img src='images/delete.svg' class='delete'>").prepend("<img src='" + base64Img + "'>");
    }));

    // Category Edit Image
    $('#edit-posts input[name="filename"]').on('change', encodeImageFileAsURL(function (base64Img) {
        let inputFilename = $('#edit-posts input[name="filename"]');
        inputFilename.after("<input type='hidden' name='fileBase64' value='" + base64Img + "'>");
        inputFilename.closest(".current.flx").find('> img').remove();
        inputFilename.closest(".current.flx").prepend("<img src='images/delete.svg' class='delete'>").prepend("<img src='" + base64Img + "'>");
    }));

    // Category Delete Image
    $('.adm_content[data-id="edit_posts"] #edit-posts .current').on('click', '.delete', function () {
        $(this).closest('.current').find('[name="fileBase64"]').remove();
        $(this).closest('.current').find('img').remove();
    });
});
