package Bussines.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Ejemplo {
    public static void main(String[] args){
        ConsultasMysql sql = new ConsultasMysql();
        List<String> datos = sql.consultaProductos("30968");



        for (String info: datos){
            System.out.println(info);
        }







    }
}
