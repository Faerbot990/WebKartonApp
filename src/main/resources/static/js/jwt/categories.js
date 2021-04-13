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
    let optionList = $('#category-parent');
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
        optionList.html('<option value="">Верхний уровень</option>');

        buildCategoryList(data, tbodyWrap, optionList);
    });
}

// Category Add
function categoryAdd(data) {
    let ajaxParams = {};
    ajaxParams.method = 'POST';
    ajaxParams.url = '/admin/add_category';
    ajaxParams.params = data;
    ajaxParams.dataType = 'json';

    sendAjax(ajaxParams).done(function () {
        // Reset table list
        $('.menu [data-id="categories"]').click();
    });
}

// Sub Category Add
function subCategoryAdd(data) {
    let ajaxParams = {};
    ajaxParams.method = 'POST';
    ajaxParams.url = '/admin/add_subcategory';
    ajaxParams.params = data;
    ajaxParams.dataType = 'json';

    sendAjax(ajaxParams).done(function () {
        // Reset table list
        $('.menu [data-id="categories"]').click();
    });
}

// Сategory Edit
function categoryGet(data) {
    let ajaxParams = {};
    ajaxParams.method = 'GET';
    ajaxParams.url = '/category/' + data.id;
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

// Category Delete
function categoryDelete(data) {
    let ajaxParams = {};
    ajaxParams.method = 'DELETE';
    ajaxParams.url = '/admin/delete_category';
    ajaxParams.params = data;
    ajaxParams.dataType = 'text';

    sendAjax(ajaxParams).done(function () {
        // Reset table list
        $('.menu [data-id="categories"]').click();
    });
}

// Sub Category Delete
function subCategoryDelete(data) {
    let ajaxParams = {};
    ajaxParams.method = 'DELETE';
    ajaxParams.url = '/admin/delete_subcategory';
    ajaxParams.params = data;
    ajaxParams.dataType = 'text';

    sendAjax(ajaxParams).done(function () {
        // Reset table list
        $('.menu [data-id="categories"]').click();
    });
}

// Categories Build
function buildCategoryList(data, tbodyWrap, optionList) {
    Object.entries(data).forEach(([key, category]) => {
        let $tr = $('<tr>').attr('data-category-id', category.id).append(
            $('<td>').html('<img src="' + category.image + '" class="prod_img">'),
            $('<td>').text(category.name),
            $('<td>').text(''),
            $('<td>').html('<img src="images/edit.svg" data-category-edit="' + category.id + '"><img src="images/delete.svg" data-category-delete="' + category.id + '">')
        );
        let $option = $('<option>').attr('value', category.id).text(category.name);

        $tr.appendTo(tbodyWrap);
        $option.appendTo(optionList);

        Object.entries(category.subCategory).forEach(([key, value]) => {
            let $tr = $('<tr>').attr('data-subcategory-id', value.id).append(
                $('<td>').html('<img src="' + value.image + '" class="prod_img">'),
                $('<td>').text(category.name),
                $('<td>').text(value.subCategoryName),
                $('<td>').html('<img src="images/edit.svg" data-subcategory-edit="' + value.id + '"><img src="images/delete.svg" data-subcategory-delete="' + value.id + '">')
            );

            $tr.appendTo(tbodyWrap);
        });
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
    $('#categories').on('submit', function (e) {
        e.preventDefault();

        let data = {};
        let parentId = $(this).find("[name='parent']").val();

        if (parentId === '') {
            data.name = $(this).find("[name='name']").val();
            data.image = $(this).find('[name="fileBase64"]').val();

            categoryAdd(data);
        } else {
            data.subCategoryName = $(this).find("[name='name']").val();
            data.categoryId = $(this).find("[name='parent']").val();
            data.image = $(this).find('[name="fileBase64"]').val();

            subCategoryAdd(data);
        }

        $(this).trigger("reset");
    });

    // Action Edit post
    $('.adm_content[data-id="categories"]').on('click', '[data-category-edit]', function (e) {
        e.preventDefault();

        let item = $(this).attr('data-category-edit');
        let object = {};
        object.id = item;

        // Get edit post
        let post = categoryGet(object);
        post.done(function (data) {
            let formEdit = $('#categories');
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

    // Action Delete Category
    $('.adm_content[data-id="categories"]').on('click', '[data-category-delete]', function (e) {
        e.preventDefault();

        if (confirm("Вы уверены, что хотите удалить категорию?") === false) {
            return false;
        }

        let item = $(this).attr('data-category-delete');
        let object = {};
        object.id = item;

        // Delete category
        categoryDelete(object);
    });

    // Action Delete Sub Category
    $('.adm_content[data-id="categories"]').on('click', '[data-subcategory-delete]', function (e) {
        e.preventDefault();

        if (confirm("Вы уверены, что хотите удалить подкатегорию?") === false) {
            return false;
        }

        let item = $(this).attr('data-subcategory-delete');
        let object = {};
        object.id = item;

        // Delete sub category
        subCategoryDelete(object);
    });

    // Category Add Image
    $('#categories input[name="filename"]').on('change', encodeImageFileAsURL(function (base64Img) {
        let inputFilename = $('#categories input[name="filename"]');
        inputFilename.after("<input type='hidden' name='fileBase64' value='" + base64Img + "'>");
        inputFilename.closest(".current.flx").find('> img').remove();
        inputFilename.closest(".current.flx").prepend("<img src='images/delete.svg' class='delete'>").prepend("<img src='" + base64Img + "'>");
    }));

    // Category Edit Image
    $('#categories input[name="filename"]').on('change', encodeImageFileAsURL(function (base64Img) {
        let inputFilename = $('#edit-posts input[name="filename"]');
        inputFilename.after("<input type='hidden' name='fileBase64' value='" + base64Img + "'>");
        inputFilename.closest(".current.flx").find('> img').remove();
        inputFilename.closest(".current.flx").prepend("<img src='images/delete.svg' class='delete'>").prepend("<img src='" + base64Img + "'>");
    }));

    // Category Delete Image
    $('.adm_content[data-id="categories"] #categories .current').on('click', '.delete', function () {
        $(this).closest('.current').find('[name="fileBase64"]').remove();
        $(this).closest('.current').find('img').remove();
    });
});
