const userId = document.cookie.match(/userId=([^;]+)/)?.[1];

if(userId){
    const cart = document.getElementById("carthref")
    cart.href =`/cart?userid=${userId}`
    cart.classList.remove("invisible");
    }
