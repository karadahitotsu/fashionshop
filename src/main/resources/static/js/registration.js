function SendReg(){
    if(document.getElementById('password').value!==document.getElementById('confirmpassword').value){
    }
    const formData = new FormData();
    const password = document.getElementById('password');
    const email = document.getElementById('email');
    formData.append("email",email.value);
    formData.append("password",password.value);
    const response = fetch("/api/createuser", {
        method: "POST",
        body: formData,
      });
    if (response.ok) {
    //location.href = "/profile?userid="+userId;
    // ...
    } else {
    // Обработайте ошибку загрузки
    // ...
     }

    }
