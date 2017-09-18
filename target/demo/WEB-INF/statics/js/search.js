$(document).ready(function() {
    $.ajax({
        url: "/data/title",
        type: "GET",
        dataType: "json"
    }).done(function(data) {
        console.log(data);
        $("#searchBox").autocomplete({
            minLength: 0,
            source:function (request, response) {
                var term = $.ui.autocomplete.escapeRegex(request.term)
                    , startsWithMatcher = new RegExp("^" + term, "i")
                    , startsWith = $.grep(data, function(value) {
                    return startsWithMatcher.test(value.label || value.value || value);
                })
                    , containsMatcher = new RegExp(term, "i")
                    , contains = $.grep(data, function (value) {
                    return $.inArray(value, startsWith) < 0 &&
                        containsMatcher.test(value.label || value.value || value);
                });

                response(startsWith.concat(contains));
            },
            focus: function (event, ui) {
                $("#searchBox").val(ui.item.value);
                return false;
            },
            select: function (event, ui) {
                $("#searchBox").val(ui.item.value);
                return false;
            }
        });
    });

    $("#searchBar").submit(function (event) {
        // stop the form from submitting
        event.preventDefault();

        $.ajax({
            url: "/data/getLocationByTitle",
            contentType : 'application/json; charset=utf-8',
            type: "GET",
            dataType: "json",
            data:  { "title" : $("#searchBox").val() }
        }).done(function (data) {
            console.log(data);
            clearMarkers();
            $.each(data, function (key, value) {
                addMarker(value);
            })
        });
    })

});