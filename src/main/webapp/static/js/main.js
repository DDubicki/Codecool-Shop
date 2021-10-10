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
    let cart = document.querySelector('nav form  button  a');
    cart.addEventListener("click", checkShopCart);
}

function checkShopCart(){
    fetchData("/api/checkShopCart")
}

function createNewOrder(event) {
    fetchData("/api/productId/" + event.target.parentElement.id);
}

function addPointer() {
    let cart1 = document.querySelectorAll('nav form  button  a')[0];
    let cart2 = document.querySelectorAll('nav form  button  a')[1];
    if (cart2 === undefined) {
        if (cart1.innerText === '🛒'){
            cart1.innerText = '🛒 (1)';
        } else {
            let quantity = Number(cart1.innerText.split(" ")[1].replace("(", "").replace(")", ""));
            quantity++;
            cart1.innerHTML = '🛒' + " (" + quantity + ")";
        }
    } else {
        let quantity = Number(cart2.innerText.replace("(", "").replace(")", ""));
        if (isNaN(quantity)) {
            quantity = Number(cart2.innerText.split(" ")[1].replace("(", "").replace(")", ""));
        }
        quantity++;
        cart1.innerHTML = "";
        cart2.innerHTML = '🛒' + " (" + quantity + ")";
    }
}

(function () {
    bindEventListeners();
})();
