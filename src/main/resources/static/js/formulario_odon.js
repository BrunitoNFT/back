const formulario = document.getElementById("formulario");
const inputs = document.querySelectorAll("#formulario input");

const expresiones = {
  usuario: /^[a-zA-Z0-9\_\-]{4,16}$/, // Letras, numeros, guion y guion_bajo
  nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
  correo: /^\d{8}$/,
};

const campos = {
  usuario: false,
  nombre: false,
  correo: false,
  telefono: false,
};

const validarFormulario = (e) => {
  switch (e.target.name) {
    case "usuario":
      validarCampo(expresiones.usuario, e.target, "usuario");
      break;
    case "nombre":
      validarCampo(expresiones.nombre, e.target, "nombre");
      break;
    case "correo":
      validarCampo(expresiones.correo, e.target, "correo");
      break;
  }
};

const validarCampo = (expresion, input, campo) => {
  if (expresion.test(input.value)) {
    document
      .getElementById(`grupo__${campo}`)
      .classList.remove("formulario__grupo-incorrecto");
    document
      .getElementById(`grupo__${campo}`)
      .classList.add("formulario__grupo-correcto");
    document
      .querySelector(`#grupo__${campo} i`)
      .classList.add("fa-check-circle");
    document
      .querySelector(`#grupo__${campo} i`)
      .classList.remove("fa-times-circle");
    document
      .querySelector(`#grupo__${campo} .formulario__input-error`)
      .classList.remove("formulario__input-error-activo");
    campos[campo] = true;
  } else {
    document
      .getElementById(`grupo__${campo}`)
      .classList.add("formulario__grupo-incorrecto");
    document
      .getElementById(`grupo__${campo}`)
      .classList.remove("formulario__grupo-correcto");
    document
      .querySelector(`#grupo__${campo} i`)
      .classList.add("fa-times-circle");
    document
      .querySelector(`#grupo__${campo} i`)
      .classList.remove("fa-check-circle");
    document
      .querySelector(`#grupo__${campo} .formulario__input-error`)
      .classList.add("formulario__input-error-activo");
    campos[campo] = false;
  }
};

inputs.forEach((input) => {
  input.addEventListener("keyup", validarFormulario);
  input.addEventListener("blur", validarFormulario);
});

formulario.addEventListener("submit", (e) => {
  e.preventDefault();

  const jwt = localStorage.getItem("JWT");

  if (!jwt) {
    swal(
      "Tienes que iniciar sesion para agregar o modificar un odontologo",
      "Inicia Sesion",
      "error"
    );
    return;
  }

  console.log("submit");
  const nombre = document.querySelector("[name='usuario']").value;
  const apellido = document.querySelector("[name='nombre']").value;
  const matricula = document.querySelector("[name='correo']").value;
  const genero =
    document.querySelector("[name='telefono']").value == "masculino"
      ? true
      : false;

  console.log("valores: ", nombre);

  if (nombre && apellido && matricula) {
    formulario.reset();
    console.log("con validacion");
    const formm = document.querySelector("form");

    //GUARDAR O EDITAR
    if (formm.getAttribute("idOdon")) {
      var myHeaders = new Headers();
      myHeaders.append("Authorization", "Bearer " + jwt);
      myHeaders.append("Content-Type", "application/json");

      var raw = JSON.stringify({
        id: formm.getAttribute("idOdon"),
        nombre: nombre,
        apellido: apellido,
        matricula: matricula,
        masculino: genero,
      });

      var requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: raw,
        redirect: "follow",
      };

      fetch("/odontologo/actualizar", requestOptions)
        .then((response) => {
          if (!response.ok) {
            throw new Error("La respuesta no fue exitosa");
          }
          return response.text();
        })
        .then((result) => {
          console.log("res", result);
          swal("Odontologo editado correctamente", "", "success");
          loadOdon();
        })
        .catch((error) => {
          console.log("error", error);
          localStorage.clear();
          console.log("fun: ", verificacion());
          console.log("errrrror");
          const container = document.querySelector(
            ".contenedorBotonesAcciones"
          );

          container.innerHTML = `<button><a href="login.html">Iniciar Sesion</a></button> <button><a href="registrarme.html">Registrarme</a></button>`;
          swal("Error al agregar un odontologo", "Inicia Sesion", "error");
        });
    } else {
      var myHeaders = new Headers();
      myHeaders.append("Authorization", "Bearer " + jwt);
      myHeaders.append("Content-Type", "application/json");

      var raw = JSON.stringify({
        nombre: nombre,
        apellido: apellido,
        matricula: matricula,
        masculino: genero,
      });

      var requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
        redirect: "follow",
      };

      fetch("/odontologo/registrar", requestOptions)
        .then((response) => {
          if (!response.ok) {
            throw new Error("La respuesta no fue exitosa");
          }
          return response.text();
        })
        .then((result) => {
          console.log("res", result);
          swal("Odontologo agregado correctamente", "", "success");
          loadOdon();
        })
        .catch((error) => {
          console.log("error", error);
          localStorage.clear();
          console.log("fun: ", verificacion());
          console.log("errrrror");
          const container = document.querySelector(
            ".contenedorBotonesAcciones"
          );

          container.innerHTML = `<button><a href="login.html">Iniciar Sesion</a></button> <button><a href="registrarme.html">Registrarme</a></button>`;
          swal("Error al agregar un odontologo", "Inicia Sesion", "error");
        });
    }

    document
      .querySelectorAll(".formulario__grupo-correcto")
      .forEach((icono) => {
        icono.classList.remove("formulario__grupo-correcto");
      });
  } else {
    swal("Rellene el formulario correctamente", "Vuelva a intentar", "error");
  }
});
