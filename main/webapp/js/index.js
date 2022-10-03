$(document).ready(function () {

  $("#form-login").submit(function (event) {

    event.preventDefault();
    autenticarUsuario();
  });

  $("#form-register").submit(function (event) {

    event.preventDefault();
    registrarUsuario();
  });

});

function autenticarUsuario() {

  let username = $("#usuario").val();
  let pass = $("#pass").val();

  $.ajax({
    type: "GET",
    dataType: "html",
    url: "./ServletUsuarioLogin",
    data: $.param({
      username: username,
      pass: pass
    }),
    success: function (result) {
      let parsedResult = JSON.parse(result);
      if (parsedResult !== false) {
        $("#login-error").addClass("d-none");
        let username = parsedResult['username'];
        document.location.href = "home.html?username=" + username;
      } else {
        $("#login-error").removeClass("d-none");
      }
    }
  });
}

function registrarUsuario() {

  let username = $("#input-username").val();
  let pass = $("#input-pass").val();
  let passConfirmacion = $("#input-pass-repeat").val();
  let nombre_usuario = $("#input-nombre_usuario").val();
  let apellido_usuario = $("#input-apellido_usuario").val();
  let email_usuario = $("#input-email_usuario").val();
  let eps_usuario = $("#input-eps_usuario").val();
  let citas_usuario = $("#input-citas_usuario").val();
  let premium = $("#input-premium").prop("checked");

  if (pass == passConfirmacion) {

    $.ajax({
      type: "GET",
      dataType: "html",
      url: "./ServletUsuarioRegister",
      data: $.param({
        username: username,
        pass: pass,
        nombre_usuario: nombre_usuario,
        apellido_usuario: apellido_usuario,
        email_usuario: email_usuario,
        eps_usuario: eps_usuario,
        citas_usuario: citas_usuario,
        premium: premium
      }),
      success: function (result) {
        let parsedResult = JSON.parse(result);

        if (parsedResult != false) {
          $("#register-error").addClass("d-none");
          let username = parsedResult['username'];
          document.location.href = "home.html?username=" + username;
        } else {
          $("#register-error").removeClass("d-none");
          $("#register-error").html("Error en el registro del usuario");
        }
      }
    });
  } else {
    $("#register-error").removeClass("d-none");
    $("#register-error").html("Las contraseñas no coinciden");
  }
}

