const saveImageButton = document.getElementById("buttonimage");


saveImageButton.addEventListener("click", async () => {
  const formData = new FormData();
 const filee = document.getElementById("file-input");
 const name = document.getElementById("name");
 const sizes = document.getElementById("sizes")
 const sizess = sizes.value.split(" ")
 const description = document.getElementById("description")
 const category = document.getElementById("category");
 const brand = document.getElementById("brand")
 const price = document.getElementById("price");
  formData.append("file", filee.files[0]);
  formData.append("name",name.value);
  formData.append("sizes",sizess);
  formData.append("brand",brand.value)
  formData.append("category",category.value);
  formData.append("price",price.value);
  formData.append("description",description.value);


  const response = await fetch("/api/image/catalog", {
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
});