package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Especialistas;
import connection.DBConnection;

public class EspecialistaController implements IEspecialistaController {

  @Override
  public String listar(boolean ordenar, String orden) {

    Gson gson = new Gson();

    DBConnection con = new DBConnection();
    String sql = "Select * from especialista";

    if (ordenar == true) {
      sql += " order by especialidad " + orden;
    }

    List<String> especialistas = new ArrayList<String>();

    try {

      Statement st = con.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);

      while (rs.next()) {

        int id_especialista = rs.getInt("id_especialista");
        String pass = rs.getString("pass_especialista");
        String nombre_especialista = rs.getString("nombre_especialista");
        String apellido_especialista = rs.getString("apellido_especialista");
        String especialidad = rs.getString("especialidad");
        String email_especialista = rs.getString("email_especialista");
        int citas_especialista = rs.getInt("citas_especialista");
        boolean novedad = rs.getBoolean("novedad");

        Especialistas especialista = new Especialistas(id_especialista,pass,
                nombre_especialista, apellido_especialista, especialidad,
                email_especialista, citas_especialista, novedad);

        especialistas.add(gson.toJson(especialista));

      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }

    return gson.toJson(especialistas);

  }

  @Override
  public String devolver(int id_especialista, String username) {

    DBConnection con = new DBConnection();
    String sql = "Delete from cita where id= " + id_especialista + " and username = '"
            + username + "' limit 1";

    try {
      Statement st = con.getConnection().createStatement();
      st.executeQuery(sql);

      this.sumarCantidad(id_especialista);

      return "true";
    } catch (Exception ex) {
      System.out.println(ex.toString());
    } finally {
      con.desconectar();
    }

    return "false";
  }

  @Override
  public String sumarCantidad(int id_especialista) {

    DBConnection con = new DBConnection();

    String sql = "Update especialista set citas_especialista ="
            + " (Select citas_especialista from especialista where id = "
            + id_especialista + ") + 1 where id = " + id_especialista;

    try {
      Statement st = con.getConnection().createStatement();
      st.executeUpdate(sql);

      return "true";
    } catch (Exception ex) {
      System.out.println(ex.toString());
    } finally {
      con.desconectar();
    }

    return "false";

  }

  @Override
  public String alquilar(int id_especialista, String username) {

    Timestamp fecha = new Timestamp(new Date().getTime());
    DBConnection con = new DBConnection();
    String sql = "Insert into cita values ('" + id_especialista + "', '" + username + "',"
            + " '" + fecha + "')";

    try {
      Statement st = con.getConnection().createStatement();
      st.executeUpdate(sql);

      String modificar = modificar(id_especialista);

      if (modificar.equals("true")) {
        return "true";
      }

    } catch (Exception ex) {
      System.out.println(ex.toString());
    } finally {
      con.desconectar();
    }
    return "false";
  }

  @Override
  public String modificar(int id_especialista) {

    DBConnection con = new DBConnection();
    String sql = "Update especialista set citas_especialista = (citas_especialista - 1)"
            + " where id_especialista = " + id_especialista;

    try {
      Statement st = con.getConnection().createStatement();
      st.executeUpdate(sql);

      return "true";
    } catch (Exception ex) {
      System.out.println(ex.toString());
    } finally {
      con.desconectar();
    }

    return "false";

  }
}
