window.addEventListener("load", function () {
    (function () {
      //con fetch invocamos a la API de estudiantes con el método GET
      //nos devolverá un JSON con una colección de estudiantes
      const url = "/paciente/buscarTodos";
      const settings = {
        method: "GET",
      };
      fetch(url, settings)
        .then((response) => response.json())
        .then((data) => {
          console.log("data: ", data);
          //recorremos la colección de estudiantes del JSON
          for (student of data) {
            var main = document.querySelector("main");
  
            main.innerHTML += `<div class="odontologo">
            <div class="odontologo_id">${student.id}</div>
            <img class="" src="./public/icons8-enfermer${
              student.masculino ? "o" : "a"
            }-94.png" /> 
            <div class="odontologoInfo">
            
            <h1 class="odontologo_h1">${
              student.nombre + " " + student.apellido
            }</h1>
            <p class="odontologo_p">${student.dni}</p>
            <p class="odontologo_p">${student.fechaAlta}</p>

  
          </div>
          <div class="botonesAcciones">
          <button class="delete"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24px" height="24px">    <path d="M 10 2 L 9 3 L 4 3 L 4 5 L 20 5 L 20 3 L 15 3 L 14 2 L 10 2 z M 5 7 L 5 22 L 19 22 L 19 7 L 5 7 z M 8 9 L 10 9 L 10 20 L 8 20 L 8 9 z M 14 9 L 16 9 L 16 20 L 14 20 L 14 9 z"/></svg></button>
          <button class="edit"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24px" height="24px">    <path d="M 18 2 L 15.585938 4.4140625 L 19.585938 8.4140625 L 22 6 L 18 2 z M 14.076172 5.9238281 L 3 17 L 3 21 L 7 21 L 18.076172 9.9238281 L 14.076172 5.9238281 z"/></svg></button>
          
          </div>
            </div>
            
            `;
          }
        });
    })(function () {
      let pathname = window.location.pathname;
      if (pathname == "/studentsList.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    });
  });
  