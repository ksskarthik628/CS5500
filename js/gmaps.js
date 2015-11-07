var x = document.getElementById('map');
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(displaymap, showError);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function showError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            x.innerHTML = "User denied the request for Geolocation."
            break;
        case error.POSITION_UNAVAILABLE:
            x.innerHTML = "Location information is unavailable."
            break;
        case error.TIMEOUT:
            x.innerHTML = "The request to get user location timed out."
            break;
        case error.UNKNOWN_ERROR:
            x.innerHTML = "An unknown error occurred."
            break;
    }
}


function displaymap(position) {
    
    var lat = position.coords.latitude;
    var long = position.coords.longitude;
    var myLatLng = { lat: lat, lng: long };

    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 16,
        center: new google.maps.LatLng(lat, long),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        title: 'Your Location'
    });
}