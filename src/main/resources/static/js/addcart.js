function addCart(productId){
        const userId = document.cookie.match(/userId=([^;]+)/)?.[1];
        if(userId===""){
            alert("войдите в аккаунт")
            return ;
        }

        if(!selected){
        alert("выберите размер")
        return ;
        }
        const formData = new FormData();
        formData.append("userid",userId);
        formData.append("productid",productId);
        formData.append("size",selected)
        const response = fetch("/api/cart/add",{
            method: "POST",
            body: formData,
        });
        const sshref =`/cart?userid=${userId}`;
        window.location.href=sshref;
        if (response.ok) {









        } else {

        }
    }