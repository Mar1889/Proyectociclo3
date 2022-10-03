package test;

import beans.Especialistas;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBD {

  public static void main(String[] args) {
    listarEspecialista();
    //actualizarEspecialista(2122, "Cardiologo");
  }

  public static void actualizarEspecialista(int id_especialista, String tipo_especialista) {
    DBConnection con = new DBConnection();
    String sql = "UPDATE especialista SET tipo_especialista = '" + tipo_especialista + "'WHERE  id_especialista = " + id_especialista;

    try {// realizando la conexion a la BD para la sentencia UPDATE
      Statement st = con.getConnection().createStatement();
      st.execute(sql);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }
  }

  public static void listarEspecialista() {
    DBConnection con = new DBConnection();//crear objeto
    String sql = "SELECT * FROM especialista";

    try {// realizando la conexion a la BD para la sentencia SELECT
      Statement st = con.getConnection().createStatement();
      ResultSet rs = st.executeQuery(sql);
      while (rs.next()) {
        int id_especialista = rs.getInt("id_especialista");
        String pass_especialista = rs.getString("pass_especialista");
        String nombre_especialista = rs.getString("nombre_especialista");
        String apellido_especialista = rs.getString("apellido_especialista");
        String tipo_especialista = rs.getString("tipo_especialista");
        String email_especialista = rs.getString("email_especialista");
        int citas = rs.getInt("citas");
        boolean novedad = rs.getBoolean("novedad");

        Especialistas especialista = new Especialistas(id_especialista,
                pass_especialista, nombre_especialista, apellido_especialista,
                tipo_especialista, email_especialista, citas, novedad);
        System.out.println(especialista.toString());
      }

      st.executeQuery(sql);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      con.desconectar();
    }
  }
}
