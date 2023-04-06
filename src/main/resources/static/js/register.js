const regForm = document.querySelector("form");
regForm.addEventListener("submit", (e) => {
  e.preventDefault();

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    firstname: document.querySelector("#floatingInput").value,
    lastname: document.querySelector("#floatingInput2").value,
    email: document.querySelector("#floatingInputGrid").value,
    password: document.querySelector("#floatingPassword").value,
  });

  var requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch("/api/v1/auth/register", requestOptions)
    .then((response) => {
      console.log("response", response);
      if (response.ok) {
        return response.json();
      }
    })
    .then((result) => {
      console.log("token: ", result);
      localStorage.setItem("JWT", result.token);
      localStorage.setItem("name", result.name);
      localStorage.setItem("lastname", result.lastname);
      location.href = "/odontologos.html";
    })
    .catch((error) => {
      console.log("error", error);
    });
});
