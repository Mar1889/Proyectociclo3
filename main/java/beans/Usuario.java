
package beans;

public class Usuario {
    
    private String username;
    private String pass;
    private String nombre_usuario;
    private String apellido_usuario;
    private String email_usuario;
    private String eps_usuario;
    private int citas_usuario;
    private boolean premium;

  public Usuario(String username, String pass, String nombre_usuario, String apellido_usuario, String email_usuario, String eps_usuario, int citas_usuario, boolean premium) {
    this.username = username;
    this.pass = pass;
    this.nombre_usuario = nombre_usuario;
    this.apellido_usuario = apellido_usuario;
    this.email_usuario = email_usuario;
    this.eps_usuario = eps_usuario;
    this.citas_usuario = citas_usuario;
    this.premium = premium;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getNombre_usuario() {
    return nombre_usuario;
  }

  public void setNombre_usuario(String nombre_usuario) {
    this.nombre_usuario = nombre_usuario;
  }

  public String getApellido_usuario() {
    return apellido_usuario;
  }

  public void setApellido_usuario(String apellido_usuario) {
    this.apellido_usuario = apellido_usuario;
  }

  public String getEmail_usuario() {
    return email_usuario;
  }

  public void setEmail_usuario(String email_usuario) {
    this.email_usuario = email_usuario;
  }

  public String getEps_usuario() {
    return eps_usuario;
  }

  public void setEps_usuario(String eps_usuario) {
    this.eps_usuario = eps_usuario;
  }

  public int getCitas_usuario() {
    return citas_usuario;
  }

  public void setCitas_usuario(int citas_usuario) {
    this.citas_usuario = citas_usuario;
  }

  public boolean isPremium() {
    return premium;
  }

  public void setPremium(boolean premium) {
    this.premium = premium;
  }

  @Override
  public String toString() {
    return "Usuario{" + "username=" + username + ", pass=" + pass + ", nombre_usuario="
            + nombre_usuario + ", apellido_usuario=" + apellido_usuario + ", email_usuario="
            + email_usuario + ", eps_usuario=" + eps_usuario + ", citas_usuario="
            + citas_usuario + ", premium=" + premium + '}';
  }
  
}
