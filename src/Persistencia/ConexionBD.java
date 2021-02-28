package Persistencia;

//Clases importadas
import Logica.Empleado; //Clase para el manejo de los empleados
import Logica.Empleados;    //Clase para el manejo de la coleccion de empleados
import java.sql.*;  //Clase para el manejo de consultas a la base de datos
import java.util.Calendar;  //Calendario para facilitar el manejo de fechas
import java.util.Date;  //Clase para crear objetos tipo fecha
import javax.swing.JOptionPane; //Clase para crear ventanas emergentes

public class ConexionBD {

    //Se establece un objeto Connection para iniciar una conexion con la base de datos
    public static Connection conexion;

    //Metodo para iniciar la conexion con la base de datos
    public static void Conectar() {
        try {

            //Se define el driver a utilizar, en este caso es el JDBC Driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Se establece la conexión al servidor local utilizado
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "");
        } 
        
        //Si no se encuentra el driver o ocurre un error en la base de datos
        catch (ClassNotFoundException | SQLException ex) {
            //Se muestra un mensaje de error
            JOptionPane.showConfirmDialog(null, "No se pudo establecer conexión", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            System.exit(0); //Se cierra el programa
        }
    }
    
    //Metodo para importar los datos de los empleados desde la base de datos
    public static void BuscarEmpleados() {
        //Se vacia la coleccion de empleados
        Empleados.ArrayEmpleados.clear();  
        
        try {
            //Se establece un objeto Statement utilizado para ejecutar consultas SQL
            Statement consulta = conexion.createStatement();
            
            /*Se define un objeto ResultSet para recibir los datos obtenidos con la consulta, en este caso 
            se utiliza una consulta que devuelve todos los empleados donde baja_logica sea falso*/
            ResultSet resultadosEmpleados = consulta.executeQuery("select * from empresa.empleados where baja_logica=0");
            
            //Se ejecuta un loop while mientras haya filas con información que leer
            while (resultadosEmpleados.next() == true) {
                
                //Se obtienen los datos de cada columna especificando el indice de cada una
                String nombreCompleto = resultadosEmpleados.getString(1); 
                Date fechaNacimiento = resultadosEmpleados.getDate(2);
                int documentoId = resultadosEmpleados.getInt(3);
                int salarioBase = resultadosEmpleados.getInt(4);
                Date fechaIngreso = resultadosEmpleados.getDate(5);
                
                //Se calcula la edad del empleado mediante un metodo, ingresando como parametro su fecha de nacimiento y la fecha actual
                Date fechaActual = new Date();
                int edad = Empleado.CalcularDistanciaEntreFechas(fechaNacimiento, fechaActual);                
                
                //Se calcula la antiguedad en años del empleado, ingresando como parametro su fecha de ingreso
                String antiguedad = Empleado.CalcularAntiguedad(fechaIngreso);                
                
                //Se calcula el salario real del empleado, ingresando como parametro su salario base y su fecha de ingreso
                int salarioReal = Empleado.CalcularSalarioReal(salarioBase, fechaIngreso);
                
                //Se crea un objeto Empleado y se agrega a la collecion ArrayEmpleados
                Empleado empleado = new Empleado(nombreCompleto, fechaNacimiento, documentoId, salarioBase, fechaIngreso, salarioReal, edad, antiguedad);
                Empleados.ArrayEmpleados.add(empleado);
            }
        } 
        //Si ocurre un error en la base de datos
        catch (SQLException ex) {
            //Mensaje de error
            JOptionPane.showConfirmDialog(null, "Error de conexión", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }

    }
    
    //Metodo que comprueba si un empleado ya existe en la base de datos utilizando su numero de cedula
    public static boolean EmpleadoExiste(int nroCedula) {
        try {
            
            //Se establece un objeto Statement utilizado para ejecutar consultas SQL
            Statement consulta = conexion.createStatement();
            
            /*Se define un objeto ResultSet para recibir los datos obtenidos con la consulta, en este caso, 
            se busca si existe un empleado con el numero de cedula ingresado como parametro*/
            ResultSet resultadoCedula = consulta.executeQuery("SELECT * FROM empresa.empleados where ci=" + String.valueOf(nroCedula));
            
            //Se ejecuta un loop while mientras haya filas con información que leer
            if (resultadoCedula.next() == true) {
                
                //Si se obtiene un resultado de la consulta
                return true;                
            }
            
        }        
        //Si ocurre un error en la base de datos
        catch (SQLException ex) {
            //Mensaje de error
            JOptionPane.showConfirmDialog(null, "Error de conexión", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static void IngresarEmpleado(Empleado empleado) {
        try {
            //Se transforman los datos de las fechas a ingresar para ser compatibles con la base de datos
            java.sql.Date fechaNacimiento = transformarFechaSQL(empleado.getFechaNacimiento());
            java.sql.Date fechaIngreso = transformarFechaSQL(empleado.getFechaIngreso());            
            
            //Se define la consulta para ingresar los datos
            String consulta = "INSERT INTO empresa.empleados(nombre_completo,fecha_nac,ci,salario_base,fecha_ingreso,baja_logica) values(?, ?, ?, ?, ?, ?)";            
            PreparedStatement prepararConsulta = conexion.prepareStatement(consulta);
            
            //Se establecen los datos por orden en la consulta, cada dato equivale a un '?' en la consulta
            prepararConsulta.setString(1, empleado.getNombreCompleto());
            prepararConsulta.setDate(2, fechaNacimiento);
            prepararConsulta.setInt(3, empleado.getDocumentoId());
            prepararConsulta.setInt(4, empleado.getSalarioBase());
            prepararConsulta.setDate(5, fechaIngreso);
            prepararConsulta.setBoolean(6, false);
            
            //Se ejecuta la consulta
            prepararConsulta.execute();
        } 
        //Si ocurre un error en la base de datos
        catch (SQLException ex) {
            //Mensaje de error
            JOptionPane.showConfirmDialog(null, "Error de conexión", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void ModificarEmpleado(Empleado empleado) {
        try {
            //Se transforman los datos de las fechas a ingresar para ser compatibles con la base de datos
            java.sql.Date fechaNacimiento = transformarFechaSQL(empleado.getFechaNacimiento());
            java.sql.Date fechaIngreso = transformarFechaSQL(empleado.getFechaIngreso());
            
            //Se define la consulta para actualizar los datos. Se actualizan los datos del empleado donde coincide el numero de cedula.
            String query = "UPDATE empresa.empleados SET nombre_completo= ?,fecha_nac= ?,salario_base= ?,fecha_ingreso= ? where ci=" + String.valueOf(empleado.getDocumentoId());
            PreparedStatement prepararConsulta = conexion.prepareStatement(query);
            
            //Se establecen los datos por orden en la consulta, cada dato equivale a un '?' en la consulta
            prepararConsulta.setString(1, empleado.getNombreCompleto());
            prepararConsulta.setDate(2, fechaNacimiento);
            prepararConsulta.setInt(3, empleado.getSalarioBase());
            prepararConsulta.setDate(4, fechaIngreso);
            
            //Se ejecuta la consulta
            prepararConsulta.execute();
        }         
        //Si ocurre un error en la base de datos
        catch (SQLException ex) {
            //Mensaje de error
            JOptionPane.showConfirmDialog(null, "Error de conexión", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void RemoverEmpleado(int nroCedula) {
        try {
            
            /*Se define la consulta. La consulta actualiza los datos del empleado
            con el numero de cedula ingresado como parametro. Se hace una baja logica*/
            String consulta = "UPDATE empresa.empleados SET baja_logica=true WHERE ci=" + String.valueOf(nroCedula);
            PreparedStatement prepararConsulta = conexion.prepareStatement(consulta);
            
            //Se ejecuta la consulta
            prepararConsulta.execute();
        } 
        //Si ocurre un error en la base de datos
        catch (SQLException ex) {
            //Mensaje de error
            JOptionPane.showConfirmDialog(null, "Error de conexión", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    //Metodo para transformar fecha al formato de la base de datos
    public static java.sql.Date transformarFechaSQL(Date fecha){
            //Se define un objeto Calendar para que la fecha sea compatible con el metodo java.sql.Date (el formato Date no es compatible, se requiere formato long)
            Calendar calFecha = Calendar.getInstance();
            calFecha.setTime(fecha);
            
            //Se devuelve la fecha convertida al formato de la base de datos
            return new java.sql.Date(calFecha.getTime().getTime());
    }
}
