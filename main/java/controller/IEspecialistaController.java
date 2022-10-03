package controller;

public interface IEspecialistaController {

  public String listar(boolean ordenar, String orden);

  public String devolver(int id_especialista, String username);

  public String sumarCantidad(int id_especialista);

  public String alquilar(int id_especialista, String username);

  public String modificar(int id_especialista);
}
