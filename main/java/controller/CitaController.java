package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Cita;
import connection.DBConnection;

public class CitaController implements ICitaController {

  @Override
  public String listarAlquileres(String username) {

    Gson gson = new Gson();

    DBConnection con = new DBConnection();

    String sql = "Select l.id_especialista, l.nombre_especialista,"
            + " l.genero, l.novedad, a.fecha from especialista l"
            + " inner join cita a on l.id_especialista = a.id_especialista"
            + " inner join usuario u on a.username = u.username "
            + "where a.username = '" + username + "'";

    List<String> citas = new ArrayList<String>();

    try {

      Statement st = con.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);

      while (rs.next()) {
        int id = rs.getInt("id_especialista");
        String nombre = rs.getString("nombre_especialista");
        String especialidad = rs.getString("especialidad");
        boolean novedad = rs.getBoolean("novedad");
        Date fechaAlquiler = rs.getDate("fecha");

        Cita cita = new Cita(id, nombre, fechaAlquiler, novedad, especialidad);

        citas.add(gson.toJson(cita));
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }
    return gson.toJson(citas);
  }
}
