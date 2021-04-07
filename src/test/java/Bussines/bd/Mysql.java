package Bussines.bd;

import org.junit.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Mysql {

    Connection conn;

    public Connection getConenection(){
        try{
            String url = "jdbc:mysql://localhost/pcfactory";
            String user = "root";
            String pass = "admin";

            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);
            return conn;

        }catch (SQLException e){
            Assert.fail("Error de conexion a MySql, se detenta en: "+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /*public void cerrarConexion() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
