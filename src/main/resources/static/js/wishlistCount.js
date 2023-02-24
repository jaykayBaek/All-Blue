$(document).ready(function() {
    const base64EncodedMap = document.cookie.replace(/(?:(?:^|.*;\s*)wishlist\s*\=\s*([^;]*).*$)|^.*$/, "$1");

    let wishlistCount = 0;

    if(base64EncodedMap != ''){
        const decodedMap = atob(base64EncodedMap);
        const map = JSON.parse(decodedMap);
        wishlistCount = Object.keys(map).length;
    }

    $('#wishlist__count').html(wishlistCount);
});