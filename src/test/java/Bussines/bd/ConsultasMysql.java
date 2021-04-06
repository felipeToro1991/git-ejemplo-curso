package Bussines.bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultasMysql extends Mysql{

    private Connection con = getConenection();

    private ResultSet ejecutarQueryResultado(String query) {
        ResultSet rs = null;
        try {

            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            System.out.println("Conexion de Mysql, Se realizó la siguiente consulta SQL en Mysql: " + query);


            if (rs != null) {
                if (rs.next()) {
                    System.out.println("Conexion A Mysql : La simulación se Journalizo correctamente en Mysql.");

                } else {
                    System.out.println("Conexion A Mysql: La simulación no se Journalizo en Mysql.");

                }
            } else {
                System.out.println("Resultado Mysql La ejecucion del Proceso fue Null.");

            }


        } catch (SQLException sqe) {
            System.out.println("Unexpected exception : " + sqe.toString() + ", sqlstate = " + sqe.getSQLState());
        }

        return rs;
    }

    public List<String> consultaProductos(String id_producto){
        List<String> datos = null;
        ResultSet rs;

        String query = "    SELECT * FROM `productos` WHERE `id_producto` = '"+id_producto +"'";

        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            //rs = ejecutarQueryResultado(query);



            int i = 0;
            datos = new ArrayList<>();
            if(rs != null){
                System.out.println(rs);

                while (rs.next()){
                    datos.add(rs.getString("id_producto"));
                    datos.add(rs.getString("nombre_producto"));
                    datos.add(rs.getString("marca_producto"));
                    datos.add(rs.getString("precio_referencial"));
                    //datos.add(rs.getString("precio_oferta"));
                    datos.add(rs.getString("precio_oferta_efec"));
                    datos.add(rs.getString("url_producto"));
                    i++;

                }
            }


        }catch (SQLException e){
            e.printStackTrace();
        }


        return datos;
    }





}
