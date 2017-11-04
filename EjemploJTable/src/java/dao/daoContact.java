package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Contact;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class daoContact {
    
    Conexion cx;
    String tabla="contact";
    
    public daoContact(){
        cx=new Conexion();
    }
    
    public JSONObject select(int id) {
        JSONObject varJsonObjectP = new JSONObject();
        JSONArray varJsonArrayP = new JSONArray();
        JSONObject varJsonObjectResultado = new JSONObject();
        
        String varSql = "";
        String sql = "";
        int total = 0;
    
                try {
                varSql = "SELECT * FROM contact WHERE IdContact "+id;
                System.out.println(varSql);
                sql = "SELECT COUNT(*) as TotalRecordCount FROM contact";
                PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
                PreparedStatement tt = cx.conectar().prepareStatement(sql);
                ResultSet rs = tt.executeQuery();
                if (rs.next()) {
                    total = Integer.parseInt(rs.getString("TotalRecordCount"));
                }
                ResultSet varResultado = varPst.executeQuery();
                while (varResultado.next()) {
                    varJsonObjectP.put("IdData", varResultado.getString("Iddata"));
                    varJsonObjectP.put("Direccion", varResultado.getString("Direccion"));
                    varJsonObjectP.put("Telefono", varResultado.getString("Telefono"));
                    varJsonObjectP.put("Email", varResultado.getString("Email"));
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
    




    public JSONObject insert(Contact a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
                insertarBD(a);
                String varSql = "SELECT * FROM \"" + tabla + "\";";
                PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
                ResultSet varResultado = varPst.executeQuery();
                while (varResultado.next()) {
                    
                    varJsonObjectRegistro.put("Direccion", varResultado.getString("Direccion"));
                    varJsonObjectRegistro.put("Telefono", varResultado.getString("Telefono"));
                    varJsonObjectRegistro.put("Email", varResultado.getString("Email"));
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

    private void insertarBD(Contact a) {
        
            try {
                String sql = "INSERT INTO " + tabla + " "
                        + "(\"Direccion\",\"Telefono\",\"Email\") "
                        + "VALUES (?,?,?)";
                PreparedStatement varPst = cx.conectar().prepareStatement(sql);                
                varPst.setString(1, a.getDireccion());
                varPst.setString(2, a.getTelefono());
                varPst.setString(3, a.getEmail());
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

    public JSONObject update(Contact a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
                System.out.println("ID" + a.getIdData());
                String sql = "UPDATE " + tabla + " "
                        + "SET "
                        + " \"Direccion\"= ?,"
                        + " \"Telefono\"= ?,"
                        + " \"Email\"= ?"
                        + " WHERE \"IdData\"= ? ";
                PreparedStatement varPst = cx.conectar().prepareStatement(sql);
                varPst.setString(2, a.getTelefono());
                varPst.setString(3, a.getEmail());
                varPst.setString(1, a.getDireccion());
                varPst.setInt(4, a.getIdData());
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

    public JSONObject delete(String IdData) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
                String sql = "DELETE FROM  \"" + tabla + "\" "
                        + "WHERE \"IdData\" =?;";
                PreparedStatement varPst = cx.conectar().prepareStatement(sql);
                varPst.setInt(1, Integer.parseInt(IdData));
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
