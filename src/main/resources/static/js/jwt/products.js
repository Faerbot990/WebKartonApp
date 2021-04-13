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
    let optionList = $('#add-prods #parent');
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
        optionList.html('<option value="">Верхний уровень</option>');

        buildCategoryList(data, optionList);
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
    // Action Open Products page
    $('.menu [data-id="prods"]').on('click', function (e) {
        e.preventDefault();

        // Get category list
        getCategoryList();
    });

});