package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;

import beans.Usuario;
import connection.DBConnection;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController implements IUsuarioController {

  @Override //para conectar con la interface
  public String login(String username, String pass) {

    Gson gson = new Gson();

    DBConnection con = new DBConnection();//conexion BD

    String sql = "SELECT * from usuario where username = '" + username
            + "' and pass = '" + pass + "'";//seleccion de datos para saber si coinciden
    try {
      Statement st = con.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);

      while (rs.next()) {
        String nombre_usuario = rs.getString("nombre_usuario");
        String apellido_usuario = rs.getString("apellido_usuario");
        String email_usuario = rs.getString("email_usuario");
        String eps_usuario = rs.getString("eps_usuario");
        int citas_usuario = rs.getInt("citas_usuario");
        boolean premium = rs.getBoolean("premium");

        Usuario usuario = new Usuario(username, pass, nombre_usuario,
                apellido_usuario, email_usuario, eps_usuario, citas_usuario, premium);
        return gson.toJson(usuario);

      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }

    return "false";
  }

  @Override
  public String register(String username, String pass, String nombre_usuario,
          String apellido_usuario, String email_usuario,
          String eps_usuario, int citas_usuario, boolean premium) {

    Gson gson = new Gson();

    DBConnection con = new DBConnection();
    String sql = "Insert into usuario values('" + username + "', '" + pass + "',"
            + " '" + nombre_usuario + "', '" + apellido_usuario + "', '"
            + email_usuario + "', '" + eps_usuario + "', " + citas_usuario + ", " + premium + ")";

    try {
      Statement st = con.getConnection().createStatement();
      st.executeUpdate(sql);

      Usuario usuario = new Usuario(username, pass, nombre_usuario,
              apellido_usuario, email_usuario, eps_usuario, citas_usuario, premium);

      st.close();

      return gson.toJson(usuario);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());

    } finally {
      con.desconectar();
    }

    return "false";

  }

  @Override
  public String pedir(String username) {

    Gson gson = new Gson();

    DBConnection con = new DBConnection();
    String sql = "Select * from usuario where username = '" + username + "'";

    try {

      Statement st = con.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);

      while (rs.next()) {
        String pass = rs.getString("pass");
        String nombre_usuario = rs.getString("nombre_usuario");
        String apellido_usuario = rs.getString("apellido_usuario");
        String email_usuario = rs.getString("email_usuario");
        String eps_usuario = rs.getString("eps_usuario");
        int citas_usuario = rs.getInt("citas_usuario");
        boolean premium = rs.getBoolean("premium");

        Usuario usuario = new Usuario(username, pass,
                nombre_usuario, apellido_usuario, email_usuario,
                eps_usuario, citas_usuario, premium);

        return gson.toJson(usuario);
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }

    return "false";
  }

  @Override
  public String modificar(String username, String nuevoPass,
          String nuevoNombre, String nuevosApellidos,
          String nuevoEmail, String nuevaEps, int nuevaCita, boolean nuevoPremium) {

    DBConnection con = new DBConnection();

    String sql = "Update usuario SET pass = '" + nuevoPass
            + "', nombre_usuario = '" + nuevoNombre + "', "
            + "apellido_usuario = '" + nuevosApellidos + "', email_usuario = '"
            + nuevoEmail + "', eps_usuario = '" + nuevaEps + "', citas_usuario = "
            + nuevaCita + ", premium = ";

    if (nuevoPremium == true) {
      sql += " 1 ";
    } else {
      sql += " 0 ";
    }

    sql += " where username = '" + username + "'";

    try {

      Statement st = con.getConnection().createStatement();
      st.executeUpdate(sql);

      return "true";
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }

    return "false";

  }

  @Override
  public String verCopias(String username) {

    DBConnection con = new DBConnection();
    String sql = "Select id_especialista, count(*) as num_citas"
            + " from cita where username = '" + username + "' "
            + "group by id_especialista;"; //cambie id por id_especialista

    Map<Integer, Integer> citas_especialista = new HashMap<Integer, Integer>();

    try {

      Statement st = con.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);

      while (rs.next()) {
        int id_especialista = rs.getInt("id_especialista");
        int num_citas = rs.getInt("num_citas");

        citas_especialista.put(id_especialista, num_citas);
      }

      devolverEspecialistas(username, citas_especialista);

      return "true";
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }

    return "false";

  }

  @Override
  public String devolverEspecialistas(String username, Map<Integer, Integer> citas_especialista) {

    DBConnection con = new DBConnection();

    try {
      for (Map.Entry<Integer, Integer> especialista : citas_especialista.entrySet()) {
        int id_especialista = especialista.getKey();
        int num_citas = especialista.getValue();

        String sql = "Update especialista set citas_especialista = (Select citas_especialista + " + num_citas
                + " from especialista where id_especialista = "
                + id_especialista + ") where id_especialista = " + id_especialista;

        Statement st = con.getConnection().createStatement();
        st.executeUpdate(sql);

      }

      this.eliminar(username);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }
    return "false";
  }

  public String eliminar(String username) {

    DBConnection con = new DBConnection();

    String sql1 = "Delete from cita where username = '" + username + "'";
    String sql2 = "Delete from usuario where username = '" + username + "'";

    try {
      Statement st = con.getConnection().createStatement();
      st.executeUpdate(sql1);
      st.executeUpdate(sql2);

      return "true";
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }

    return "false";
  }
@Override
  public String restarDinero(String username, int nuevasCitas) {

    DBConnection con = new DBConnection();
    String sql = "Update usuarios set citas_usuario = " + nuevasCitas + " where username = '"
            + username + "'";

    try {

      Statement st = con.getConnection().createStatement();
      st.executeUpdate(sql);

      return "true";
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }

    return "false";
  }
}
