package Logica;

//Clases importadas
import java.util.Calendar; //Calendario para facilitar el manejo de fechas
import java.util.Date;  //Clase para crear objetos tipo fecha

public class Empleado {

    //Atributos de empleado
    private String nombreCompleto;      
    private Date fechaNacimiento;       
    private int documentoId;            //Número de cédula
    private int salarioBase;            //Salario al ingresar a la empresa
    private Date fechaIngreso;          
    private int salarioReal;            //Salario real calculado segun la antiguedad
    private int edad;                   
    private String antiguedadLaboral;   //Antiguedad en años

    //Constructores
    
    //Constructor para ingresar o modificar un empleado
    public Empleado(String nombreCompleto, Date fechaNacimiento, int documentoId, int salarioBase, Date fechaIngreso) {
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.documentoId = documentoId;
        this.salarioBase = salarioBase;
        this.fechaIngreso = fechaIngreso;
    }  

    //Constructor para ingresar empleado en JTable
    public Empleado(String nombreCompleto, Date fechaNacimiento, int documentoId, int salarioBase, Date fechaIngreso, int salarioReal, int edad, String antiguedadLaboral) {
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.documentoId = documentoId;
        this.salarioBase = salarioBase;
        this.fechaIngreso = fechaIngreso;
        this.salarioReal = salarioReal;
        this.edad = edad;
        this.antiguedadLaboral = antiguedadLaboral;
    }

    //Getter y setter
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(int documentoId) {
        this.documentoId = documentoId;
    }

    public int getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(int salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getSalarioReal() {
        return salarioReal;
    }

    public void setSalarioReal(int salarioReal) {
        this.salarioReal = salarioReal;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAntiguedadLaboral() {
        return antiguedadLaboral;
    }

    public void setAntiguedadLaboral(String antiguedadLaboral) {
        this.antiguedadLaboral = antiguedadLaboral;
    }

    //Se calcula la antiguedad del empleado ingresando como parametro la fecha de ingreso
    public static String CalcularAntiguedad(Date fechaIngreso) {
        Date fechaActual = new Date();
        
        //Se calcula la diferencia en años entre la fecha de ingreso y la fecha actual
        int diferencia = CalcularDistanciaEntreFechas(fechaIngreso, fechaActual);
        switch (diferencia) {
            case 0:
                //Si la diferencia es 0 se devuelve el valor:
                return "Menos de un año";
            case 1:
                //Si la diferencia es 1 se devuelve el valor:
                return String.valueOf(diferencia + " año");
            default:
                //Si la diferencia es otra se devuelve el valor:
                return String.valueOf(diferencia + " años");
        }
    }

    //Se calcula la diferencia en años entre las dos fechas ingresadas
    public static int CalcularDistanciaEntreFechas(Date fechaInicio, Date fechaFinal) {
        //Se definen dos objetos Calendar para asi calcular la diferencia entre las dos fechas
        Calendar calInicio = Calendar.getInstance();
        Calendar calFinal = Calendar.getInstance();
        
        //Se establecen las fechas de los calendarios segun los parametros ingresados
        calInicio.setTime(fechaInicio);
        calFinal.setTime(fechaFinal);
        
        //Se obtiene la diferencia entre las dos fechas solo teniendo en cuenta los años
        int diferencia = calFinal.get(Calendar.YEAR) - calInicio.get(Calendar.YEAR);
        
        //Se compara el día del año de cada fecha para saber con certeza cuanta distancia hay entre estas
        if (calInicio.get(Calendar.DAY_OF_YEAR) > calFinal.get(Calendar.DAY_OF_YEAR)) {
            //Si todavía no se cumple la fecha exacta para sumar otro año, este se resta
            diferencia--;
        }
        //Se devuelve la diferencia en años
        return diferencia;

    }

    public static int CalcularSalarioReal(int salarioBase, Date fechaIngreso) {
        Date fechaActual = new Date();
        int añosAntiguedad = CalcularDistanciaEntreFechas(fechaIngreso, fechaActual);
        if (añosAntiguedad <= 3) {      //Si la antiguedad es 3 años o menos se devuelve el salario base
            return salarioBase;
        } 
        else {        //Si la antiguedad es mayor se calcula el salario real (aumenta un 5% del salario base por año luego del tercer año)
            int aumento = (salarioBase * 5) / 100;
            int salarioReal = salarioBase + (aumento * (añosAntiguedad - 3));
            return salarioReal;
        }

    }

}
