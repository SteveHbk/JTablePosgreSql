package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class Conexion {

    String bd = "crud";
    String forName = "org.postgresql.Driver";
    String url = "jdbc:potgresql://localhost:5432" + bd;
    String user = "postgres";
    String pass = "19264696";

    public Connection cx;
   
    
    public Connection conectar() {
        try {            
            Class.forName(forName);
            cx = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/crud",user,pass);
            System.out.println("SE CONECTO");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("NO SE CONECTO"+ex);
        }
        return cx;
    }
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            ex.printStackTrace();            
        }
        
    }
    public static void main(String[] args) {
         Conexion c = new Conexion();
         c.conectar();
         
        
    }

}
