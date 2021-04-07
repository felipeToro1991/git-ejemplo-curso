package Bussines.excel;

public class PcfactoryExcel {

    private String nombreExcel = "resultadoPcfactory";
    AccionesExcel excel = new AccionesExcel();

    public void excelPcfactory(String datos[]){
        if(!excel.existeExcel(nombreExcel)){
            String [] nombreColumnaPcfactory =
                    {"id_producto", "nombre_producto", "marca", "precio_referencial", "precio_oferta_efec", "url"};
            excel.crearNuevoExcel(nombreExcel, "Producto", nombreColumnaPcfactory);
        }
        excel.agregarLineaExcel(nombreExcel,datos);
    }

}
