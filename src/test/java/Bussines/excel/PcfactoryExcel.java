package Bussines.excel;

import cucumber.api.java.en_old.Ac;

public class PcfactoryExcel {

    private String nombrExcel= "resultadoPcfactory";
    AccionesExcel excel = new AccionesExcel();

    public void excelPcfactory(String dato[]){

        if(!excel.existeExcel(nombrExcel)){
            String [] cabeceraPcfactory = {"Id_Producto", "nombre_producto", "Marca", "Precio_referencial", "precio_oferta_efec", "url"};
            excel.crearNuevoExcel(nombrExcel, "Producto",cabeceraPcfactory);
        }
        excel.agregarLineaExcel(nombrExcel,dato);
    }
}
