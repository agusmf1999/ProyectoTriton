package Logica;

//Clases importadas
import Persistencia.ConexionBD; //Clase para el manejo de información y de la conexion con la base de datos
import Presentacion.FrmPrincipal;   //Clase para el manejo de ventanas de la aplicacion

public class Main {

    public static void main(String[] args) {
        
        //Se establece conexion con la base de datos
        ConexionBD.Conectar();
        
        //Se inicia el programa abriendo la ventana principal
        FrmPrincipal frmPrincipal= new FrmPrincipal();
        frmPrincipal.setVisible(true);
    }
    
}
