var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

  $(function () {
    $('[data-toggle="tooltip"]').tooltip();
  });

  getUsuario().then(function () {

    $("#mi-perfil-btn").attr("href", "profile.html?username=" + username);

    $("#user-citas_usuario").html(user.citas_usuario.toFixed(0));

    getEspecialistas(false, "ASC");

    $("#ordenar-especialidad").click(ordenarEspecialistas);
  });
});


async function getUsuario() {

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
      } else {
        console.log("Error recuperando los datos del usuario");
      }
    }
  });

}
function getEspecialistas(ordenar, orden) {

  $.ajax({
    type: "GET",
    dataType: "html",
    url: "./ServletEspecialistaListar",
    data: $.param({
      ordenar: ordenar,
      orden: orden
    }),
    success: function (result) {
      let parsedResult = JSON.parse(result);

      if (parsedResult != false) {
        mostrarEspecialistas(parsedResult);
      } else {
        console.log("Error recuperando los datos de los especialistas");
      }
    }
  });
}
function mostrarEspecialistas(especialistas) {

  let contenido = "";

  $.each(especialistas, function (index, especialista) {

    especialista = JSON.parse(especialista);
    let cantidad_citas;

    if (especialista.citas_especialista > 0) {

      if (user.premium) {

        if (especialista.novedad) {
          cantidad_citas = especialista.citas_especialista;
        } else {
          cantidad_citas = 0;
        }
      } else {
        if (especialista.novedad) {
          cantidad_citas = 6700;
        } else {
          cantidad_citas = 0;
        }
      }

      contenido += '<tr><th scope="row">' + especialista.id_especialista + '</th>' +
              '<td>' + especialista.nombre_especialista + '</td>' +
              '<td>' + especialista.apellido_especialista + '</td>' +
              '<td>' + especialista.especialidad + '</td>' +
              '<td>' + especialista.email_especialista + '</td>' +
              '<td>' + especialista.citas_especialista + '</td>' +
              '<td><input type="checkbox" name="novedad" id="novedad'
              + especialista.id_especialista + '" disabled ';
      if (especialista.novedad) {
        contenido += 'checked';
      }
      contenido += '></td>' +
              '<td>' + cantidad_citas + '</td>' +
              '<td><button onclick="sacarCita(' + especialista.id_especialista + ','
              + cantidad_citas + ');" class="btn btn-success" ';
      if (user.citas_usuario < cantidad_citas) {
        contenido += ' disabled ';
      }

      contenido += '>Pedir Cita</button></td></tr>'

    }
  });
  $("#especialistas-tbody").html(contenido);
}
function ordenarEspecialistas() {

  if ($("#icono-ordenar").hasClass("fa-sort")) {
    getEspecialistas(true, "ASC");
    $("#icono-ordenar").removeClass("fa-sort");
    $("#icono-ordenar").addClass("fa-sort-down");
  } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
    getEspecialistas(true, "DESC");
    $("#icono-ordenar").removeClass("fa-sort-down");
    $("#icono-ordenar").addClass("fa-sort-up");
  } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
    getEspecialistas(false, "ASC");
    $("#icono-ordenar").removeClass("fa-sort-up");
    $("#icono-ordenar").addClass("fa-sort");
  }
}
function sacarCita(id_especialista,cantidad_citas ) {

  $.ajax({
    type: "GET",
    dataType: "html",
    url: "./ServletEspecialistaAlquilar",
    data: $.param({
      id: id_especialista,
      username: username

    }),
    success: function (result) {
      let parsedResult = JSON.parse(result);

      if (parsedResult != false) {
        restarDinero(cantidad_citas).then(function () {
          location.reload();
        })
      } else {
        console.log("Error en la reserva el Especialista");
      }
    }
  });
}


async function restarDinero(citas_usuario) {

  await $.ajax({
    type: "GET",
    dataType: "html",
    url: "./ServletUsuarioRestarDinero",
    data: $.param({
      username: username,
      saldo: parseInt(user.cantidad_citas - citas_usuario)

    }),
    success: function (result) {
      let parsedResult = JSON.parse(result);

      if (parsedResult != false) {
        console.log("Citas actualizadas");
      } else {
        console.log("Error en el proceso de pago");
      }
    }
  });
}