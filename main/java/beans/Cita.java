package beans;

import java.sql.Date;

public class Cita {
    
    private int id_especialista;
    private String username;
    private Date fecha;
    private boolean novedad;
    private String especialidad;

  public Cita(int id_especialista, String username, Date fecha, boolean novedad, String especialidad) {
    this.id_especialista = id_especialista;
    this.username = username;
    this.fecha = fecha;
    this.novedad = novedad;
    this.especialidad = especialidad;
  }

  public int getId_especialista() {
    return id_especialista;
  }

  public void setId_especialista(int id_especialista) {
    this.id_especialista = id_especialista;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public boolean isNovedad() {
    return novedad;
  }

  public void setNovedad(boolean novedad) {
    this.novedad = novedad;
  }

  public String getEspecialidad() {
    return especialidad;
  }

  public void setEspecialidad(String especialidad) {
    this.especialidad = especialidad;
  }

  @Override
  public String toString() {
    return "Cita{" + "id_especialista=" + id_especialista + ", username="
            + username + ", fecha=" + fecha + ", novedad=" + novedad + ", especialidad="
            + especialidad + '}';
  }

    
}