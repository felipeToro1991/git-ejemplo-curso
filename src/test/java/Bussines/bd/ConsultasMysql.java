package Bussines.bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultasMysql extends Mysql {

    private Connection con = getConenection();

    public List<String> consultaProductos(String id_producto) {
        List<String> datos = null;
        ResultSet rs;

        String query = "SELECT * FROM `productos` WHERE `id_producto` = '" + id_producto + "'";

        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            datos = new ArrayList<>();
            while (rs.next()) {
                datos.add(rs.getString("id_producto"));
                datos.add(rs.getString("nombre_producto"));
                datos.add(rs.getString("marca_producto"));
                datos.add(rs.getString("precio_referencial"));
                datos.add(rs.getString("precio_oferta_efec"));
                datos.add(rs.getString("url_producto"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta sql");
        }
        return datos;
    }

    public String extraerId(String nombreProducto) {
        String idproducto = "";
        ResultSet rs;

        String query = "SELECT id_producto FROM `productos` WHERE `nombre_producto` = '" + nombreProducto + "'";

        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                idproducto = rs.getString("id_producto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idproducto;
    }


}
