function deleteBy(id) {
  console.log("ejecutamos la funcion");
  const jwt = localStorage.getItem("JWT");
  console.log("jwtes: ", id, jwt);
  if (!jwt) {
    swal("Necesita registrarse para esta operación", "", "info");
    return;
  }

  var myHeaders = new Headers();
  myHeaders.append("Authorization", "Bearer " + jwt);

  var requestOptions = {
    method: "DELETE",
    headers: myHeaders,
    redirect: "follow",
  };

  fetch("/odontologo/" + id, requestOptions)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al eliminar el odontólogo");
      }
      return response.text();
    })
    .then((text) => {
      swal("Odontologo eliminado correctamente", "", "success");
      console.log("TEXT ES: ", text);
      loadOdon();
    })
    .catch((error) => {
      swal(
        "Error al eliminar el odontologo",
        "Token expirado, vuelva a iniciar sesion",
        "error"
      );
      localStorage.clear();
      const container = document.querySelector(".contenedorBotonesAcciones");

      container.innerHTML = `<button><a href="login.html">Iniciar Sesion</a></button> <button><a href="registrarme.html">Registrarme</a></button>`;

      console.log("error", error);
    });
}

function devueltaguardar() {
  const h1Ed = document.querySelector(".h1-form");
  h1Ed.innerHTML = "Agregar Odontologo";

  const but = document.querySelector(".formulario__btn");
  but.innerHTML = "Enviar";

  const formm = document.querySelector("form");
  formm.removeAttribute("idOdon");

  formm.reset();
}

function editt(e) {
  console.log(
    "editando",
    e,
    e.getAttribute("dataid"),
    e.getAttribute("dataMatricula")
  );

  const h1Ed = document.querySelector(".h1-form");
  h1Ed.innerHTML = "Editar Odontologo";

  const but = document.querySelector(".formulario__btn");
  but.innerHTML = "Editar";

  document
    .querySelector("form")
    .setAttribute("idOdon", e.getAttribute("dataid"));

  document.querySelector("h1").innerHTML += `
  <svg
    xmlns="http://www.w3.org/2000/svg"
    viewBox="0 0 24 24"
    width="24px"
    height="24px"
    onclick="devueltaguardar()"
  >
    <path d="M 4.7070312 3.2929688 L 3.2929688 4.7070312 L 10.585938 12 L 3.2929688 19.292969 L 4.7070312 20.707031 L 12 13.414062 L 19.292969 20.707031 L 20.707031 19.292969 L 13.414062 12 L 20.707031 4.7070312 L 19.292969 3.2929688 L 12 10.585938 L 4.7070312 3.2929688 z" />
  </svg>
  `;

  const nombree = document.querySelector("[name='usuario']");
  const apellidoo = document.querySelector("[name='nombre']");
  const matriculaa = document.querySelector("[name='correo']");
  const generoo = document.querySelector("[name='telefono']");

  nombree.value = e.getAttribute("datanombre");
  apellidoo.value = e.getAttribute("dataApellido");
  matriculaa.value = e.getAttribute("dataMatricula");
  generoo.value =
    e.getAttribute("datagenero") == "true" ? "masculino" : "femenino";
}

function loadOdon() {
  const url = "/odontologo/buscarTodos";
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      console.log("data: ", data);
      //recorremos la colección de estudiantes del JSON
      var main = document.querySelector(".contenedorOdon");
      main.innerHTML = "";
      data = data.sort(function (a, b) {
        return a.id - b.id;
      });
      for (student of data) {
        main.innerHTML += `<div class="odontologo">
          <div class="odontologo_id">${student.id}</div>
          <img class="" src="./public/icons8-enfermer${
            student.masculino ? "o" : "a"
          }-94.png" />
          <div class="odontologoInfo">

          <h1 class="odontologo_h1">${
            student.nombre + " " + student.apellido
          }</h1>
          <p class="odontologo_p">${student.matricula}</p>

        </div>
        <div class="botonesAcciones">
        <button class="delete" onclick="deleteBy(${
          student.id
        })"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24px" height="24px">    <path d="M 10 2 L 9 3 L 4 3 L 4 5 L 20 5 L 20 3 L 15 3 L 14 2 L 10 2 z M 5 7 L 5 22 L 19 22 L 19 7 L 5 7 z M 8 9 L 10 9 L 10 20 L 8 20 L 8 9 z M 14 9 L 16 9 L 16 20 L 14 20 L 14 9 z"/></svg></button>
        <button onclick="editt(this)" dataid="${student.id}" dataNombre="${
          student.nombre
        }" dataApellido="${student.apellido}"dataMatricula="${
          student.matricula
        }"dataGenero="${
          student.masculino
        }" class="edit"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24px" height="24px">    <path d="M 18 2 L 15.585938 4.4140625 L 19.585938 8.4140625 L 22 6 L 18 2 z M 14.076172 5.9238281 L 3 17 L 3 21 L 7 21 L 18.076172 9.9238281 L 14.076172 5.9238281 z"/></svg></button>

        </div>
          </div>

          `;
      }
    });
}

window.addEventListener("load", loadOdon());
