package controller;

import java.util.Map; //importando api para identificar y autorizar usuario

public interface IUsuarioController {

  public String login(String username, String pass);

  public String register(String username, String pass,
          String nombre_usuario, String apellido_usuario,
          String email_usuario, String eps_usario, int citas_usuario, boolean premium);

  public String pedir(String username);

  public String modificar(String username, String nuevaContrasena,
          String nuevoNombre, String nuevosApellidos, String nuevoEmail, 
          String nuevaEps, int citas_usaurio, boolean nuevoPremium);

  public String verCopias(String username);

  public String devolverEspecialistas(String username, Map<Integer, Integer> citas_especialista);

  public String eliminar(String username);
  
  public String restarDinero(String username, int nuevasCitas);
}
