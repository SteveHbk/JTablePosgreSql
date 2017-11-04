package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Relatives;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class daoRelatives {
    
    Conexion cx;
    String tabla="relatives";
    
    
    public daoRelatives(){
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
                varSql = "SELECT * FROM relatives ORDER BY \"Nombre\" ASC" + " LIMIT " + jtPageSize + " OFFSET " + jtStartIndex + ";";
                System.out.println(varSql);
                sql = "SELECT COUNT(*) as TotalRecordCount FROM relatives";
                PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
                PreparedStatement tt = cx.conectar().prepareStatement(sql);
                ResultSet rs = tt.executeQuery();
                if (rs.next()) {
                    total = Integer.parseInt(rs.getString("TotalRecordCount"));
                }
                ResultSet varResultado = varPst.executeQuery();
                while (varResultado.next()) {
                    varJsonObjectP.put("IdRelative", varResultado.getString("IdRelative"));
                    varJsonObjectP.put("Parentesco", varResultado.getString("Parentesco"));
                    varJsonObjectP.put("Nombre", varResultado.getString("Nombre"));
                    varJsonObjectP.put("EmailR", varResultado.getString("EmailR"));
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
    




    public JSONObject insert(Relatives a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        JSONObject varJsonObjectRegistro = new JSONObject();
        try {
                insertarBD(a);
                String varSql = "SELECT * FROM \"" + tabla + "\";";
                PreparedStatement varPst = cx.conectar().prepareStatement(varSql);
                ResultSet varResultado = varPst.executeQuery();
                while (varResultado.next()) {
                    
                    varJsonObjectRegistro.put("Parentesco", varResultado.getString("Parentesco"));
                    varJsonObjectRegistro.put("Nombre", varResultado.getString("Nombre"));
                    varJsonObjectRegistro.put("EmailR", varResultado.getString("EmailR"));
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

    private void insertarBD(Relatives a) {
        
            try {
                String sql = "INSERT INTO " + tabla + " "
                        + "(\"Parentesco\",\"Nombre\",\"EmailR\") "
                        + "VALUES (?,?,?)";
                PreparedStatement varPst = cx.conectar().prepareStatement(sql);                
                varPst.setString(1, a.getParentesco());
                varPst.setString(2, a.getNombre());
                varPst.setString(3, a.getEmailR());
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

    public JSONObject update(Relatives a) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
                System.out.println("ID" + a.getIdRelative());
                String sql = "UPDATE " + tabla + " "
                        + "SET "
                        + " \"Parentesco\"= ?,"
                        + " \"Nombre\"= ?,"
                        + " \"EmailR\"= ?"
                        + " WHERE \"IdRelative\"= ? ";
                PreparedStatement varPst = cx.conectar().prepareStatement(sql);
                varPst.setString(2, a.getNombre());
                varPst.setString(3, a.getEmailR());
                varPst.setString(1, a.getParentesco());
                varPst.setInt(4, a.getIdRelative());
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

    public JSONObject delete(String IdRelative) {
        JSONObject varJsonObjectResultado = new JSONObject();
        try {
                String sql = "DELETE FROM  \"" + tabla + "\" "
                        + "WHERE \"IdRelative\" =?;";
                PreparedStatement varPst = cx.conectar().prepareStatement(sql);
                varPst.setInt(1, Integer.parseInt(IdRelative));
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
