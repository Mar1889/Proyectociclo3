package beans;

public class Especialistas {

  private int id_especialista;
  private String pass_especialista;
  private String nombre_especialista;
  private String apellido_especialista;
  private String especialidad;
  private String email_especialista;
  private int citas_especialista;
  private boolean novedad;

  public Especialistas(int id_especialista, String pass_especialista, String nombre_especialista, String apellido_especialista, String especialidad, String email_especialista, int citas_especialista, boolean novedad) {
    this.id_especialista = id_especialista;
    this.pass_especialista = pass_especialista;
    this.nombre_especialista = nombre_especialista;
    this.apellido_especialista = apellido_especialista;
    this.especialidad = especialidad;
    this.email_especialista = email_especialista;
    this.citas_especialista = citas_especialista;
    this.novedad = novedad;
  }

  public int getId_especialista() {
    return id_especialista;
  }

  public void setId_especialista(int id_especialista) {
    this.id_especialista = id_especialista;
  }

  public String getPass_especialista() {
    return pass_especialista;
  }

  public void setPass_especialista(String pass_especialista) {
    this.pass_especialista = pass_especialista;
  }

  public String getNombre_especialista() {
    return nombre_especialista;
  }

  public void setNombre_especialista(String nombre_especialista) {
    this.nombre_especialista = nombre_especialista;
  }

  public String getApellido_especialista() {
    return apellido_especialista;
  }

  public void setApellido_especialista(String apellido_especialista) {
    this.apellido_especialista = apellido_especialista;
  }

  public String getEspecialidad() {
    return especialidad;
  }

  public void setEspecialidad(String especialidad) {
    this.especialidad = especialidad;
  }

  public String getEmail_especialista() {
    return email_especialista;
  }

  public void setEmail_especialista(String email_especialista) {
    this.email_especialista = email_especialista;
  }

  public int getCitas_especialista() {
    return citas_especialista;
  }

  public void setCitas_especialista(int citas_especialista) {
    this.citas_especialista = citas_especialista;
  }

  public boolean isNovedad() {
    return novedad;
  }

  public void setNovedad(boolean novedad) {
    this.novedad = novedad;
  }

  @Override
  public String toString() {
    return "Especialistas{" + "id_especialista=" + id_especialista + ", pass_especialista="
            + pass_especialista + ", nombre_especialista="
            + nombre_especialista + ", apellido_especialista="
            + apellido_especialista + ", especialidad="
            + especialidad + ", email_especialista="
            + email_especialista + ", citas_especialista="
            + citas_especialista + ", novedad=" + novedad + '}';
  }

     
} 
