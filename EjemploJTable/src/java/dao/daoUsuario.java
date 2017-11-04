
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Usuario;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class daoUsuario {
    Conexion cx;
    String tabla="usuarios";
    
    
    public daoUsuario(){
        cx=new Conexion();
    }
    
    public JSONObject select(int jtStartIndex, int jtPageSize, String jtSorting) {
        JSONObject varJsonObjectP = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        JSONObject varJsonObjectResultado = new JSONObject();
        
        String varSql = "";
        String sql = "";
        int total = 0;
    
                try {
                varSql = "SELECT * FROM usuarios ORDER BY \"Nombre\" ASC" + " LIMIT " + jtPageSize + " OFFSET " + jtStartIndex + ";";
                System.out.println(varSql);
                sql = "SELECT COUNT(*) as TotalRecordCount FROM usuarios";
                PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
                PreparedStatement tt = cx.conectar().prepareStatement(sql);
                ResultSet rs = tt.executeQuery();
                if (rs.next()) {
                    total = Integer.parseInt(rs.getString("TotalRecordCount"));
                }
                ResultSet varResultado = varPst.executeQuery();
                while (varResultado.next()) {
                    varJsonObjectP.put("IdUsuario", varResultado.getString("IdUsuario"));
                    varJsonObjectP.put("Usuario", varResultado.getString("Usuario"));
                    varJsonObjectP.put("Password", varResultado.getString("Password"));
                    varJsonObjectP.put("Nombre", varResultado.getString("Nombre"));
                    varJsonArrayP.add(varJsonObjectP);
                }
                varResultado.close();
                varResultado = null;
                varPst.close();
                varPst = null;
                cx.desconectar();
            } catch (Exception e) {
                System.out.print(e);
            }
    
                varJsonObjectResultado.put("Result", "OK");
        varJsonObjectResultado.put("TotalRecordCount", total);
        varJsonObjectResultado.put("Records", varJsonArrayP);
        return varJsonObjectResultado;
    }
    




    public JSONObject insert(Usuario a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
                insertarBD(a);
                String varSql = "SELECT * FROM \"" + tabla + "\";";
                PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
                ResultSet varResultado = varPst.executeQuery();
                while (varResultado.next()) {
                    
                    varJsonObjectRegistro.put("Usuario", varResultado.getString("Usuario"));
                    varJsonObjectRegistro.put("Password", varResultado.getString("Password"));
                    varJsonObjectRegistro.put("Nombre", varResultado.getString("Nombre"));
                }
                varJsonObjectResultado.put("Result", "OK");
                varJsonObjectResultado.put("Record", varJsonObjectRegistro);
                varResultado.close();
                varResultado = null;
                varPst.close();
                varPst = null;
                cx.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print(e);
            }
        return varJsonObjectResultado;
    }

    private void insertarBD(Usuario a) {
        
            try {
                String sql = "INSERT INTO " + tabla + " "
                        + "(\"Usuario\",\"Password\",\"Nombre\") "
                        + "VALUES (?,?,?)";
                PreparedStatement varPst = cx.conectar().prepareStatement(sql);                
                varPst.setString(1, a.getUsuario());
                varPst.setString(2, a.getPassword());
                varPst.setString(3, a.getNombre());
                System.out.println("*****" + varPst.toString());
                varPst.execute();
                varPst.close();
                varPst = null;
                cx.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print(e);
            }
    }

    public JSONObject update(Usuario a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
                System.out.println("ID" + a.getIdUsuario());
                String sql = "UPDATE " + tabla + " "
                        + "SET "
                        + " \"Usuario\"= ?,"
                        + " \"Password\"= ?,"
                        + " \"Nombre\"= ?"
                        + " WHERE \"IdUsuario\"= ? ";
                PreparedStatement varPst = cx.conectar().prepareStatement(sql);
                varPst.setString(2, a.getPassword());
                varPst.setString(3, a.getNombre());
                varPst.setString(1, a.getUsuario());
                varPst.setInt(4, a.getIdUsuario());
                varPst.executeUpdate();
                varJsonObjectResultado.put("Result", "OK");
                varPst.close();
                varPst = null;
                cx.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print(e);
            }
        return varJsonObjectResultado;
    }

    public JSONObject delete(String IdUsuario) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
                String sql = "DELETE FROM  \"" + tabla + "\" "
                        + "WHERE \"IdUsuario\" =?;";
                PreparedStatement varPst = cx.conectar().prepareStatement(sql);
                varPst.setInt(1, Integer.parseInt(IdUsuario));
                varPst.executeUpdate();
                varJsonObjectResultado.put("Result", "OK");
                varPst.close();
                varPst = null;
                cx.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print(e);
            }
        return varJsonObjectResultado;
    }
    
}
