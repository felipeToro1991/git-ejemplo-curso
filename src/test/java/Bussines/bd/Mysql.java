package Bussines.bd;

import Bussines.reportes.EstadoPrueba;
import Bussines.reportes.PdfBciReports;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Mysql {

    Connection conn;

    public Connection getConenection(){
        try{
            String url = "jdbc:mysql://localhost:3306/pcfactory";
            String user = "root";
            String pass = "";

            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);
            PdfBciReports.addReport("Conexion a Mysql", "Validacion a la conexion BD de Mysql", EstadoPrueba.PASSED, false);
            return conn;

        }catch (SQLException e){
            PdfBciReports.addReport("Conexion a Mysql", "Validacion a la conexion BD de Mysql", EstadoPrueba.FAILED, true);
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
