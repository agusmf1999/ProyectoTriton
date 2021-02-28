package Presentacion;

import Logica.Empleado; //Clase para el manejo de los empleados
import Persistencia.ConexionBD; //Clase para el manejo de información y de la conexion con la base de datos
import java.awt.Toolkit;
import java.util.Calendar;  //Calendario para facilitar el manejo de fechas
import java.util.Date;  //Clase para crear objetos tipo fecha
import javax.swing.ImageIcon;   //Para definir el icono de la aplicación
import javax.swing.JOptionPane; //Para utilizar ventanas emergentes

public class FrmIngresarEmpleado extends javax.swing.JFrame {

    public FrmIngresarEmpleado() {
        Inicializar();
    }
    
    //Metodo que inicializa la ventana con sus parametros correspondientes
    private void Inicializar(){
        initComponents();   //Se inicializan los componentes graficos        
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icon.png")).getImage());   //Se establece el icono de la aplicacion
        setLocationRelativeTo(null);    //Posiciona la ventana en el centro de la pantalla
        this.setTitle("Ingresar empleado"); //Se establece el titulo de la ventana
        //Se establece como fecha maxima de ingreso la fecha actual
        Date fechaActual = new Date();
        calFechaIngreso.setMaxSelectableDate(fechaActual);
    }
    
    //Se vacian las casillas de texto y se restablecen las fechas
    private void VaciarCasillas() {
        txtNombreCompleto.setText("");
        txtCedula.setText("");
        txtSalarioBase.setText("");
        Calendar cal = Calendar.getInstance();
        cal.set(2021, 0, 01);
        calFechaIngreso.setDate(cal.getTime());
        cal.set(2004, 0, 01);
        calFechaNac.setDate(cal.getTime());
    }

    private void Salir(){
        Toolkit.getDefaultToolkit().beep();
        
        //Ventana emergente para confirmar la salida
        int confirmarSalida = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmarSalida == JOptionPane.OK_OPTION)
            System.exit(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelIngresarEmpleado = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        txtNombreCompleto = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtSalarioBase = new javax.swing.JTextField();
        calFechaNac = new com.toedter.calendar.JDateChooser();
        calFechaIngreso = new com.toedter.calendar.JDateChooser();
        lblNombre = new javax.swing.JLabel();
        lblFechaNac = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblSalarioBase = new javax.swing.JLabel();
        lblFechaIngreso = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        btnBorrarDatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelIngresarEmpleado.setBackground(new java.awt.Color(157, 224, 220));
        panelIngresarEmpleado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnVolver.setBackground(new java.awt.Color(220, 247, 242));
        btnVolver.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(35, 59, 57));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        txtNombreCompleto.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        txtNombreCompleto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreCompletoKeyTyped(evt);
            }
        });

        txtCedula.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtSalarioBase.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        txtSalarioBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSalarioBaseKeyTyped(evt);
            }
        });

        calFechaNac.setToolTipText("");
        calFechaNac.setDate(new java.util.Date(1072926000000L));
        calFechaNac.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        calFechaNac.setMaxSelectableDate(new java.util.Date(1104462065000L));
        calFechaNac.setMinSelectableDate(new java.util.Date(-788903935000L));

        calFechaIngreso.setDate(new java.util.Date(1609470000000L));
        calFechaIngreso.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        calFechaIngreso.setMaxSelectableDate(new java.util.Date(253370779289000L));
        calFechaIngreso.setMinSelectableDate(new java.util.Date(-788903935000L));

        lblNombre.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(35, 59, 57));
        lblNombre.setText("Nombre completo:");

        lblFechaNac.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lblFechaNac.setForeground(new java.awt.Color(35, 59, 57));
        lblFechaNac.setText("Fecha de nacimiento:");

        lblCedula.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lblCedula.setForeground(new java.awt.Color(35, 59, 57));
        lblCedula.setText("Cedula de identidad:");

        lblSalarioBase.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lblSalarioBase.setForeground(new java.awt.Color(35, 59, 57));
        lblSalarioBase.setText("Salario base:");

        lblFechaIngreso.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lblFechaIngreso.setForeground(new java.awt.Color(35, 59, 57));
        lblFechaIngreso.setText("Fecha de ingreso:");

        btnIngresar.setBackground(new java.awt.Color(220, 247, 242));
        btnIngresar.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(35, 59, 57));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(35, 59, 57));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ingresar empleado al sistema");

        lblError.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 38, 0));
        lblError.setText(" ");

        btnBorrarDatos.setBackground(new java.awt.Color(220, 247, 242));
        btnBorrarDatos.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        btnBorrarDatos.setForeground(new java.awt.Color(35, 59, 57));
        btnBorrarDatos.setText("Borrar datos");
        btnBorrarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelIngresarEmpleadoLayout = new javax.swing.GroupLayout(panelIngresarEmpleado);
        panelIngresarEmpleado.setLayout(panelIngresarEmpleadoLayout);
        panelIngresarEmpleadoLayout.setHorizontalGroup(
            panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIngresarEmpleadoLayout.createSequentialGroup()
                .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelIngresarEmpleadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelIngresarEmpleadoLayout.createSequentialGroup()
                        .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelIngresarEmpleadoLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblFechaIngreso)
                                            .addComponent(lblSalarioBase)
                                            .addComponent(lblFechaNac))
                                        .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(lblCedula))
                                .addGap(18, 18, 18)
                                .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSalarioBase)
                                    .addComponent(calFechaIngreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addComponent(calFechaNac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
                            .addGroup(panelIngresarEmpleadoLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBorrarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelIngresarEmpleadoLayout.setVerticalGroup(
            panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIngresarEmpleadoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCedula))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaNac))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSalarioBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSalarioBase))
                .addGap(18, 18, 18)
                .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaIngreso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelIngresarEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresar)
                    .addComponent(btnVolver)
                    .addComponent(btnBorrarDatos))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelIngresarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelIngresarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        //Si las casillas de texto estan vacias
        if (txtNombreCompleto.getText().equals("") && txtCedula.getText().equals("") && txtSalarioBase.getText().equals("")) {
            //Se cierra la ventana actual y se vuelve a la ventana anterior
            this.dispose();
            new FrmGestionEmpleados().setVisible(true);
        } 
        //Si hay datos en las casillas de texto, se pide una confirmacion
        else {
            Toolkit.getDefaultToolkit().beep();
            
            //Mensaje de confirmacion
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea volver?\n Los datos no guardados se perderan", "Confirmar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            //Si se confirma la accion
            if (confirmar == JOptionPane.OK_OPTION) {
                //Se cierra la ventana actual y se vuelve a la ventana anterior
                this.dispose();
                new FrmGestionEmpleados().setVisible(true);
            }
        }

    }//GEN-LAST:event_btnVolverActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Salir();
    }//GEN-LAST:event_formWindowClosing

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        lblError.setText("");
        try {
            //Si las casilla de texto estan vacias
            if (txtNombreCompleto.getText().equals("") || txtCedula.getText().equals("") || txtSalarioBase.getText().equals("")) {
                lblError.setText("<html><body>*Ingrese datos validos: <br> no puede ingresar campos vacios</html></body>"); //Mensaje de error
                Toolkit.getDefaultToolkit().beep();            
            }
            //Si el sueldo base es menor a 5000
            else if (Integer.parseInt(txtSalarioBase.getText())<5000) {
                lblError.setText("<html><body>*Ingrese datos validos: no puede ingresar<br> un sueldo menor a 5000</html></body>");    //Mensaje de error
                Toolkit.getDefaultToolkit().beep();
            }
            //Si el empleado ya existe en la base de datos
            else if (ConexionBD.EmpleadoExiste(Integer.parseInt(txtCedula.getText())) == true) {
                lblError.setText("<html><body>*Error:<br> ya existe un usuario con ese n° de cédula</html></body>");    //Mensaje de error
            } 
            //Si la cedula no tiene exactamente 8 digitos
            else if (txtCedula.getText().length() != 8) {
                lblError.setText("<html><body>*Ingrese datos validos: <br> n° de cédula debe tener 8 digitos</html></body>");   //Mensaje de error
                Toolkit.getDefaultToolkit().beep();
            } 
            //Si la fecha de nacimiento o la fecha de ingreso estan fuera del rango permitido de fechas
            else if (calFechaNac.getDate().before(calFechaNac.getMinSelectableDate()) || calFechaNac.getDate().after(calFechaNac.getMaxSelectableDate()) || calFechaIngreso.getDate().before(calFechaIngreso.getMinSelectableDate()) || calFechaIngreso.getDate().after(calFechaIngreso.getMaxSelectableDate())) {
                lblError.setText("*Ingrese datos validos: fecha incorrecta");   //Mensaje de error
                Toolkit.getDefaultToolkit().beep();
            } 
            //Si la diferencia entre la fecha de ingreso y la fecha de nacimiento no es al menos de 18 años, siendo la fecha de ingreso la mayor
            else if (Empleado.CalcularDistanciaEntreFechas(calFechaNac.getDate(), calFechaIngreso.getDate()) < 18) {
                lblError.setText("<html><body>*Ingrese datos validos:<br> la fecha de ingreso debe ser al menos<br> 18 años mayor a la de nacimiento</html></body>");   //Mensaje de error
                Toolkit.getDefaultToolkit().beep();
            } 
            //Si nada de lo anterior sucede, se procede a ingresar al empleado
            else {
                //Se crea un objeto Empleado para ingresar los datos mediante el metodo IngresarEmpleado
                Empleado empleadoIngreso = new Empleado(txtNombreCompleto.getText(), calFechaNac.getDate(), Integer.parseInt(txtCedula.getText()), Integer.parseInt(txtSalarioBase.getText()), calFechaIngreso.getDate());
                ConexionBD.IngresarEmpleado(empleadoIngreso);
                Toolkit.getDefaultToolkit().beep();
                
                //Mensaje de que la operacion fue exitosa
                JOptionPane.showConfirmDialog(null, "Datos ingresados correctamente", "Operacion exitosa", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                VaciarCasillas();
            }
        } 
        //Si las casillas de fecha quedan vacias
        catch (NullPointerException ex) {
            lblError.setText("*Ingrese datos validos: fecha incorrecta");   //Mensaje de error
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtNombreCompletoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCompletoKeyTyped
        //Se reinicia el texto del label utilizado para mostrar mensajes de error
        lblError.setText("");
        
        //Si el caracter ingresado es un número
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();  //No permite escribir
            lblError.setText("*No se permiten números en el nombre");   //Mensaje de error
            Toolkit.getDefaultToolkit().beep();
        } 
        //El nombre no puede superar los 40 caracteres
        else if (txtNombreCompleto.getText().length() == 40) {
            evt.consume();  //No permite escribir
        }
    }//GEN-LAST:event_txtNombreCompletoKeyTyped

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        //Se reinicia el texto del label utilizado para mostrar mensajes de error
        lblError.setText("");
        
        //Si el caracter ingresado es una letra
        if (Character.isLetter(evt.getKeyChar())) {
            evt.consume();  //No permite escribir
            lblError.setText("<html><body>*No se permiten letras en el número de<br> cédula</html></body>");    //Mensaje de error
            Toolkit.getDefaultToolkit().beep();
        } 
        //La cédula no puede superar los 8 digitos
        else if (txtCedula.getText().length() == 8) {
            evt.consume();  //No permite escribir
        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtSalarioBaseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalarioBaseKeyTyped
        //Se reinicia el texto del label utilizado para mostrar mensajes de error
        lblError.setText("");
        
        //Si el caracter ingresado es una letra
        if (Character.isLetter(evt.getKeyChar())) {
            evt.consume();  //No permite escribir
            lblError.setText("*No se permiten letras en el salario base");  //Mensaje de error
            Toolkit.getDefaultToolkit().beep();
        } 
        //El salario base no puede superar los 8 digitos
        else if (txtSalarioBase.getText().length() == 8) {
            evt.consume();  //No permite escribir
        }
    }//GEN-LAST:event_txtSalarioBaseKeyTyped

    private void btnBorrarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarDatosActionPerformed
        //Si las casillas de texto estan vacías
        if (txtNombreCompleto.getText().equals("") && txtCedula.getText().equals("") && txtSalarioBase.getText().equals("")) {
            lblError.setText("No hay datos que borrar");    //Mensaje de error
            Toolkit.getDefaultToolkit().beep();
        } 
        //De forma contraria se procede a borrar los datos de estas pidiendo antes una confirmación
        else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar los datos?", "Confirmar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmar == JOptionPane.OK_OPTION) {
                Toolkit.getDefaultToolkit().beep();
                VaciarCasillas();
            }
        }
    }//GEN-LAST:event_btnBorrarDatosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrarDatos;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnVolver;
    private com.toedter.calendar.JDateChooser calFechaIngreso;
    private com.toedter.calendar.JDateChooser calFechaNac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFechaIngreso;
    private javax.swing.JLabel lblFechaNac;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSalarioBase;
    private javax.swing.JPanel panelIngresarEmpleado;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtSalarioBase;
    // End of variables declaration//GEN-END:variables
}
