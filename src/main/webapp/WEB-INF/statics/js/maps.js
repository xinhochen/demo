
var geocoder;
var map;
var markers = [];
function initMap() {
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(37.775, -122.419);
    var mapOptions = {
        zoom: 14,
        center: latlng
    }
    map = new google.maps.Map(document.getElementById("map"), mapOptions);
}

function addMarker(address) {
    geocoder.geocode( { 'address': address + " San Francisco, USA"}, function(results, status) {
        if (status == 'OK') {
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location,
                label: address
            });
            markers.push(marker);
        } else {
            console.log('Geocode was not successful for the following reason: ' + status);
        }
    });
}

function clearMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}