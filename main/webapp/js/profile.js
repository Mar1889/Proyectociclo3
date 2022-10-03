var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {


  fillUsuario().then(function () {

    $("#user-citas_usuario").html("$" + user.citas_usuario.toFixed());

    getAlquiladas(user.username);
  });

  $("#reservar-btn").attr("href", `home.html?username=${username}`);

  $("#form-modificar").on("submit", function (event) {

    event.preventDefault();
    modificarUsuario();
  });

  $("#aceptar-eliminar-cuenta-btn").click(function () {

    eliminarCuenta().then(function () {
      location.href = "login.html";
    })
  })

});

async function fillUsuario() {
  await $.ajax({
    type: "GET",
    dataType: "html",
    url: "./ServletUsuarioPedir",
    data: $.param({
      username: username
    }),
    success: function (result) {
      let parsedResult = JSON.parse(result);

      if (parsedResult != false) {
        user = parsedResult;

        $("#input-username").val(parsedResult.username);
        $("#input-pass").val(parsedResult.pass);
        $("#input-nombre_usuario").val(parsedResult.nombre_usuario);
        $("#input-apellido_usuario").val(parsedResult.apellido_usuario);
        $("#input-email_usuario").val(parsedResult.email_usuario);
        $("#input-eps_usuario").val(parsedResult.eps_usuario);
        $("#input-citas_usaurio").val(parsedResult.citas_usaurio);
        $("#input-premium").prop("checked", parsedResult.premium);
        

      } else {
        console.log("Error recuperando los datos del usuario");
      }
    }
  });
}

function getAlquiladas(username) {


  $.ajax({
    type: "GET",
    dataType: "html",
    url: "./ServletCitaListar",
    data: $.param({
      username: username
    }),
    success: function (result) {
      let parsedResult = JSON.parse(result);

      if (parsedResult != false) {

        mostrarHistorial(parsedResult)

      } else {
        console.log("Error recuperando los datos de las reservas");
      }
    }
  });
}

function mostrarHistorial(especialista) {
  let contenido = "";
  if (especialista.length >= 1) {
    $.each(especialista, function (index, especialista) {
      especialista = JSON.parse(especialista);

      contenido += '<tr><th scope="row">' + especialista.id_especialista + '</th>' +
              '<td>' + especialista.nombre_especialista + '</td>' +
              '<td>' + especialista.apellido_especialista + '</td>' +
              '<td>' + especialista.especialidad + '</td>' +
              '<td><input type="checkbox" name="novedad" id="novedad' + especialista.id_especialista
              + '" disabled ';
      if (especialista.novedad) {
        contenido += 'checked';
      }
      contenido += '></td><td>' + especialista.fechaAlquiler + '</td>' +
              '<td><button id="devolver-btn" onclick= "devolverEspecialista(' + especialista.id_especialista
              + ');" class="btn btn-danger">Devolver especialista</button></td></tr>';

    });
    $("#historial-tbody").html(contenido);
    $("#historial-table").removeClass("d-none");
    $("#historial-vacio").addClass("d-none");

  } else {
    $("#historial-vacio").removeClass("d-none");
    $("#historial-table").addClass("d-none");
  }
}


function devolverEspecialista(id_especialista) {

  $.ajax({
    type: "GET",
    dataType: "html",
    url: "./ServletEspecialistaDevolver",
    data: $.param({
      username: username,
      id: id
    }),
    success: function (result) {

      if (result != false) {

        location.reload();

      } else {
        console.log("Error devolviendo el Pelicula");
      }
    }
  });

}

function modificarUsuario() {

  let username = $("#input-username").val();
  let pass = $("#input-pass").val();
  let nombre_usuario = $("#input-nombre_usuario").val();
  let apellido_usuario = $("#input-apellido_usuario").val();
  let email_usaurio = $("#input-email_usuario").val();
  let eps_usuario = $("#input-eps_usuario").val();
  let citas_usuario = $("#input-citas_usuario").val();
  let premium = $("#input-premium").prop('checked');

  $.ajax({
    type: "GET",
    dataType: "html",
    url: "./ServletUsuarioModificar",
    data: $.param({
      username: username,
      pass: pass,
      nombre_usuario: nombre_usuario,
      apellido_usuario: apellido_usuario,
      email_usuario: email_usaurio,
      eps_usuario: eps_usuario,
      citas_usuario: citas_usuario,
      premium: premium

    }),
    success: function (result) {

      if (result != false) {
        $("#modificar-error").addClass("d-none");
        $("#modificar-exito").removeClass("d-none");
      } else {
        $("#modificar-error").removeClass("d-none");
        $("#modificar-exito").addClass("d-none");
      }

      setTimeout(function () {
        location.reload();
      }, 3000);

    }
  });

}

async function eliminarCuenta() {

  await $.ajax({
    type: "GET",
    dataType: "html",
    url: "./ServletUsuarioEliminar",
    data: $.param({
      username: username
    }),
    success: function (result) {

      if (result != false) {

        console.log("Usuario eliminado")

      } else {
        console.log("Error eliminando el usuario");
      }
    }
  });
}