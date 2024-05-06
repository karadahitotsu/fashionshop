async function SendLog(){
      const formData = new FormData();
          const password = document.getElementById('password');
          const email = document.getElementById('email');
          formData.append("email",email.value);
          formData.append("password",password.value);

          const response = await fetch("/api/loginuser", {
                  method: "POST",
                  body: formData,
                })
              .then(response => {
                  // Check for successful response
                  if (!response.ok) {
                    throw new Error(`Error: ${response.statusText}`); // Handle non-2xx response
                  }
                  return response.json(); // Parse JSON response
                })
                .then(data => {

                  const userId = data.userid;


                  document.cookie = `userId=${userId}; path=/; max-age=Infinity`;

                  // Redirect to the homepage
                  location.href = "/";
                })
                .catch(error => {
                  console.error("Error:", error);
                  // Handle errors (network, parsing, etc.)
                });
                };

