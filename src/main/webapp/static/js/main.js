const BASE_URL = 'http://localhost:8080';

function fetchData(route) {
    window.history.pushState(null, null, BASE_URL);
    fetch(BASE_URL + route)
        .then(response => response.json())
        .catch(error => {
            console.log("")
        });
}

function bindEventListeners() {
    let addToCart = document.querySelectorAll('div.card-text');
    addToCart.forEach((button) => {
        button.addEventListener("click", addPointer);
        button.addEventListener("click", createNewOrder)
    });
    // let cart = document.querySelector('nav form  button  a');
    // cart.addEventListener("click", createNewOrder);
}

function createNewOrder(event) {
    fetchData("/api" + "/productId " + event.target.parentElement.id);
}

function addPointer() {
    let cart = document.querySelector('nav form  button  a');
    if (cart.innerHTML === '🛒') {
        cart.innerHTML = 1;
    } else {
        cart.innerHTML++;
    }
}

(function () {
    bindEventListeners();
    // let route = "/top?page=1";
    // fetchData(route);
})();
