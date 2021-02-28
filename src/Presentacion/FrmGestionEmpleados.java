package Presentacion;

import Logica.Empleado; //Clase para el manejo de los empleados
import Logica.Empleados;    //Clase para el manejo de la coleccion de empleados
import Persistencia.ConexionBD; //Clase para el manejo de información y de la conexion con la base de datos
import java.text.ParseException;    //Clase para el manejo de errores al convertir datos
import java.text.SimpleDateFormat;  //Clase para definir el formato de fechas
import java.util.Calendar;  //Calendario para facilitar el manejo de fechas
import java.util.Date;  //Clase para crear objetos tipo fecha
import javax.swing.ImageIcon;   //Para definir el icono de la aplicación
import javax.swing.JOptionPane; //Para utilizar ventanas emergentes
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;  //Permite seleccionar solo una fila en una tabla
import javax.swing.table.DefaultTableModel; //Modelo de la tabla
import java.awt.Toolkit;

public class FrmGestionEmpleados extends javax.swing.JFrame {

    public FrmGestionEmpleados() {
        Inicializar();        
    }
    //Metodo que inicializa la ventana con sus parametros correspondientes
    private void Inicializar(){
        initComponents();   //Se inicializan los componentes graficos        
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icon.png")).getImage());   //Se establece el icono de la aplicacion
        setLocationRelativeTo(null);    //Posiciona la ventana en el centro de la pantalla
        this.setTitle("Gestión de empleados");  //Se establece el titulo de la ventana
        
        //Se establece como fecha maxima de ingreso la fecha actual
        Date fechaActual = new Date();
        calFechaIngreso.setMaxSelectableDate(fechaActual);
        
        DeshabilitarEdicion();
        PrepararTabla();    
    }
    
    //Metodo que ejecuta la accion de salir del programa
    private void Salir(){
        Toolkit.getDefaultToolkit().beep();
        
        //Ventana emergente para confirmar la salida
        int confirmarSalida = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmarSalida == JOptionPane.OK_OPTION)
            System.exit(0);
    }
    
    //Metodo que habilita la edicion de las casillas de texto y toma los datos del empleado seleccionado en la tabla
    private void HabilitarEdicion() {
        
        //Se habilitan las casillas de texto
        txtNombreCompleto.setEnabled(true);
        txtSalarioBase.setEnabled(true);
        calFechaNac.setEnabled(true);
        calFechaIngreso.setEnabled(true);
        btnGuardarCambios.setEnabled(true);
        btnCancelar.setEnabled(true);
        
        //Se obtienen los datos del empleado seleccionado en la tabla
        txtNombreCompleto.setText((String) tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 0));
        lblCedulaText.setText((String) tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 1));
        txtSalarioBase.setText((String) tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 6));        
        try {
            //Se tranforma el formato de la fecha de String a Date
            Date fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse((String) tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 2));
            calFechaNac.setDate(fechaNac);
            Date fechaIngreso = new SimpleDateFormat("dd/MM/yyyy").parse((String) tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 4));
            calFechaIngreso.setDate(fechaIngreso);
        } 
        //Si hay un error en la transformacion de la fecha
        catch (ParseException ex) {
            //Mensaje de error
            JOptionPane.showConfirmDialog(null, "Error al transformar fecha", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            DeshabilitarEdicion();
        }

    }
    
    //Metodo que deshabilita la edicion de las casillas de texto y las deja vacias
    private void DeshabilitarEdicion() {
        //Deselecciona el empleado de la tabla
        tablaEmpleados.clearSelection();
        
        //Se desactivan las casillas de texto
        txtNombreCompleto.setEnabled(false);
        txtSalarioBase.setEnabled(false);
        calFechaNac.setEnabled(false);
        calFechaIngreso.setEnabled(false);
        btnGuardarCambios.setEnabled(false);
        btnCancelar.setEnabled(false);
        
        //Se borran los datos de las casillas de texto
        txtNombreCompleto.setText("");
        lblCedulaText.setText("");
        txtSalarioBase.setText("");
        
        //Se restablecen las fechas
        Calendar cal = Calendar.getInstance();
        cal.set(2021, 0, 01);
        calFechaIngreso.setDate(cal.getTime());
        cal.set(2004, 0, 01);
        calFechaNac.setDate(cal.getTime());

    }

    //Metodo que obtiene los datos de los empleados y los coloca en una tabla
    private void PrepararTabla() {
        //Se inicializa el modelo de la tabla
        DefaultTableModel PreTabla;
        //Se ejecuta el metodo buscar empleados para obtener la información de la base de datos
        ConexionBD.BuscarEmpleados();
        try {
            //Se definen las columnas de la tabla
            String titulos[] = {"Nombre completo", "Cédula de identidad", "Fecha de nacimiento", "Edad", "Fecha de ingreso", "Antigüedad", "Salario base", "Salario real"};
            PreTabla = new DefaultTableModel(null, titulos) {
                //Se define que las celdas no sean editables
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            //Se establece la cantidad de empleados a ingresar en la tabla
            int numeroEmpleados = Empleados.ArrayEmpleados.size();
            
            //Se establece el formato de la fecha a ingresar en la tabla
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            
            //Bucle for en el cual se ingresan los empleados en la tabla desde la coleccion de empleados
            for (int i = 0; i < numeroEmpleados; i++) {
                String fila_Tabla[] = {Empleados.ArrayEmpleados.get(i).getNombreCompleto(), String.valueOf(Empleados.ArrayEmpleados.get(i).getDocumentoId()), formatoFecha.format(Empleados.ArrayEmpleados.get(i).getFechaNacimiento()), String.valueOf(Empleados.ArrayEmpleados.get(i).getEdad()), formatoFecha.format(Empleados.ArrayEmpleados.get(i).getFechaIngreso()), String.valueOf(Empleados.ArrayEmpleados.get(i).getAntiguedadLaboral()), String.valueOf(Empleados.ArrayEmpleados.get(i).getSalarioBase()), String.valueOf(Empleados.ArrayEmpleados.get(i).getSalarioReal())};
                PreTabla.addRow(fila_Tabla);    //Se añade un empleado en la tabla
            }
            
            //Se define el modelo de la tabla
            tablaEmpleados.setModel(PreTabla);
            //Se define que no se puedan reordenar las columnas
            tablaEmpleados.getTableHeader().setReorderingAllowed(false);
            //Se define que solo se pueda seleccionar una fila a la vez
            tablaEmpleados.setSelectionMode(SINGLE_SELECTION);
        } 
        //Si por alguna razon falla el codigo, se crea la tabla pero sin datos
        catch (Exception e) {
            String titulos[] = {"Nombre completo", "Cédula de identidad", "Fecha de nacimiento", "Edad", "Fecha de ingreso", "Antigüedad", "Salario base", "Salario real"};
            PreTabla = new DefaultTableModel(null, titulos);
            tablaEmpleados.setModel(PreTabla);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListarEmpleados = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        btnIngresarEmpleado = new javax.swing.JButton();
        scrlEmpleados = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        panelFormulario = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        lblFechaNac = new javax.swing.JLabel();
        calFechaNac = new com.toedter.calendar.JDateChooser();
        lblCedula = new javax.swing.JLabel();
        lblSalarioBase = new javax.swing.JLabel();
        txtSalarioBase = new javax.swing.JTextField();
        calFechaIngreso = new com.toedter.calendar.JDateChooser();
        lblFechaIngreso = new javax.swing.JLabel();
        btnGuardarCambios = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnRemoverEmpleado = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        lblTituloModDatos = new javax.swing.JLabel();
        lblCedulaText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(220, 247, 242));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelListarEmpleados.setBackground(new java.awt.Color(220, 247, 242));
        panelListarEmpleados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAtras.setBackground(new java.awt.Color(220, 247, 242));
        btnAtras.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(35, 59, 57));
        btnAtras.setText("Volver");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnIngresarEmpleado.setBackground(new java.awt.Color(220, 247, 242));
        btnIngresarEmpleado.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        btnIngresarEmpleado.setForeground(new java.awt.Color(35, 59, 57));
        btnIngresarEmpleado.setText("Ingresar empleado");
        btnIngresarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarEmpleadoActionPerformed(evt);
            }
        });

        scrlEmpleados.setBackground(new java.awt.Color(220, 247, 242));
        scrlEmpleados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrlEmpleados.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tablaEmpleados.setBackground(new java.awt.Color(220, 247, 242));
        tablaEmpleados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaEmpleados.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        tablaEmpleados.setForeground(new java.awt.Color(35, 59, 57));
        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        )
    );
    tablaEmpleados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    tablaEmpleados.setAutoscrolls(false);
    tablaEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    tablaEmpleados.setDragEnabled(true);
    tablaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            tablaEmpleadosMousePressed(evt);
        }
    });
    scrlEmpleados.setViewportView(tablaEmpleados);

    panelFormulario.setBackground(new java.awt.Color(157, 224, 220));
    panelFormulario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    lblNombre.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    lblNombre.setForeground(new java.awt.Color(35, 59, 57));
    lblNombre.setText("Nombre completo:");

    txtNombreCompleto.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    txtNombreCompleto.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            txtNombreCompletoKeyTyped(evt);
        }
    });

    lblFechaNac.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    lblFechaNac.setForeground(new java.awt.Color(35, 59, 57));
    lblFechaNac.setText("Fecha de nacimiento:");

    calFechaNac.setToolTipText("");
    calFechaNac.setDate(new java.util.Date(1072926000000L));
    calFechaNac.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    calFechaNac.setMaxSelectableDate(new java.util.Date(1104462065000L));
    calFechaNac.setMinSelectableDate(new java.util.Date(-788903935000L));

    lblCedula.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    lblCedula.setForeground(new java.awt.Color(35, 59, 57));
    lblCedula.setText("Cedula de identidad:");

    lblSalarioBase.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    lblSalarioBase.setForeground(new java.awt.Color(35, 59, 57));
    lblSalarioBase.setText("Salario base:");

    txtSalarioBase.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    txtSalarioBase.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            txtSalarioBaseKeyTyped(evt);
        }
    });

    calFechaIngreso.setDate(new java.util.Date(1609473665000L));
    calFechaIngreso.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

    lblFechaIngreso.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    lblFechaIngreso.setForeground(new java.awt.Color(35, 59, 57));
    lblFechaIngreso.setText("Fecha de ingreso:");

    btnGuardarCambios.setBackground(new java.awt.Color(220, 247, 242));
    btnGuardarCambios.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    btnGuardarCambios.setForeground(new java.awt.Color(35, 59, 57));
    btnGuardarCambios.setText("Guardar cambios");
    btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnGuardarCambiosActionPerformed(evt);
        }
    });

    btnCancelar.setBackground(new java.awt.Color(220, 247, 242));
    btnCancelar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    btnCancelar.setForeground(new java.awt.Color(35, 59, 57));
    btnCancelar.setText("Cancelar");
    btnCancelar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCancelarActionPerformed(evt);
        }
    });

    btnRemoverEmpleado.setBackground(new java.awt.Color(220, 247, 242));
    btnRemoverEmpleado.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    btnRemoverEmpleado.setForeground(new java.awt.Color(35, 59, 57));
    btnRemoverEmpleado.setText("Remover empleado");
    btnRemoverEmpleado.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRemoverEmpleadoActionPerformed(evt);
        }
    });

    lblError.setBackground(new java.awt.Color(255, 255, 255));
    lblError.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    lblError.setForeground(new java.awt.Color(255, 38, 0));
    lblError.setText(" ");

    lblTituloModDatos.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
    lblTituloModDatos.setForeground(new java.awt.Color(35, 59, 57));
    lblTituloModDatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblTituloModDatos.setText("Modificar datos de empleado");

    lblCedulaText.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
    lblCedulaText.setForeground(new java.awt.Color(35, 59, 57));

    javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
    panelFormulario.setLayout(panelFormularioLayout);
    panelFormularioLayout.setHorizontalGroup(
        panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelFormularioLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblTituloModDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFormularioLayout.createSequentialGroup()
                    .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFormularioLayout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(45, 45, 45)
                            .addComponent(btnGuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelFormularioLayout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addComponent(btnRemoverEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 2, Short.MAX_VALUE))
                .addGroup(panelFormularioLayout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCedula)
                        .addComponent(lblFechaNac)
                        .addComponent(lblSalarioBase)
                        .addComponent(lblNombre)
                        .addComponent(lblFechaIngreso))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(calFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSalarioBase, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(calFechaNac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombreCompleto, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelFormularioLayout.createSequentialGroup()
                            .addComponent(lblCedulaText)
                            .addGap(0, 0, Short.MAX_VALUE)))))
            .addContainerGap())
    );
    panelFormularioLayout.setVerticalGroup(
        panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelFormularioLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblTituloModDatos)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCedulaText)
                .addComponent(lblCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNombre))
            .addGap(18, 18, 18)
            .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(calFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblFechaNac))
            .addGap(18, 18, 18)
            .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSalarioBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSalarioBase))
            .addGap(18, 18, 18)
            .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblFechaIngreso)
                .addComponent(calFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarCambios))
            .addGap(18, 18, 18)
            .addComponent(btnRemoverEmpleado)
            .addGap(7, 7, 7)
            .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    javax.swing.GroupLayout panelListarEmpleadosLayout = new javax.swing.GroupLayout(panelListarEmpleados);
    panelListarEmpleados.setLayout(panelListarEmpleadosLayout);
    panelListarEmpleadosLayout.setHorizontalGroup(
        panelListarEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelListarEmpleadosLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(scrlEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelListarEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListarEmpleadosLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(panelListarEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnIngresarEmpleado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap())
    );
    panelListarEmpleadosLayout.setVerticalGroup(
        panelListarEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelListarEmpleadosLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panelListarEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(scrlEmpleados)
                .addGroup(panelListarEmpleadosLayout.createSequentialGroup()
                    .addComponent(btnIngresarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(panelListarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 2, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(panelListarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 11, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Salir();
    }//GEN-LAST:event_formWindowClosing

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        //Se abre la siguiente ventana y se cierra la actual
        new FrmPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnIngresarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarEmpleadoActionPerformed
        //Se abre la siguiente ventana y se cierra la actual 
        new FrmIngresarEmpleado().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnIngresarEmpleadoActionPerformed

    private void btnRemoverEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverEmpleadoActionPerformed
        //Se reinicia el texto del label utilizado para mostrar mensajes de error
        lblError.setText("");
        
        try {
            //Se obtiene la cédula del empleado seleccionado desde la tabla
            int cedula = Integer.parseInt((String) tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 1));
            
            //Mensaje de confirmación
            int confirmarAccion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea remover\n el empleado de la base de datos?", "Confirmar accion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            //Si se confirma la accion, se procede a remover el empleado de la base de datos. Solo se hace una baja a nivel logico con un atributo booleano
            if (confirmarAccion == JOptionPane.OK_OPTION) {                
                ConexionBD.RemoverEmpleado(cedula);
                DeshabilitarEdicion();
                Toolkit.getDefaultToolkit().beep();
                
                //Mensaje que indica que la operacion fue exitosa
                JOptionPane.showConfirmDialog(null, "Datos removidos correctamente", "Operacion exitosa", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                PrepararTabla();
            }
        }
        //Si se presiona el boton pero no se ha seleccionado ningun empleado en la tabla
        catch (ArrayIndexOutOfBoundsException ex) {
            lblError.setText("*No ha seleccionado ningun empleado");    //Mensaje de error
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_btnRemoverEmpleadoActionPerformed

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        //Se reinicia el texto del label utilizado para mostrar mensajes de error
        lblError.setText("");
        
        try{
            //Si la casilla del nombre o la del salario base estan vacias
            if (txtNombreCompleto.getText().equals("") || txtSalarioBase.getText().equals("")) {
                lblError.setText("*Ingrese datos validos: no puede ingresar campos vacios");    //Mensaje de error
                Toolkit.getDefaultToolkit().beep();
            }
            //Si el sueldo base es menor a 5000
            else if (Integer.parseInt(txtSalarioBase.getText())<5000) {
                lblError.setText("<html><body>*Ingrese datos validos: no puede ingresar<br> un sueldo menor a 5000</html></body>");    //Mensaje de error
                Toolkit.getDefaultToolkit().beep();
            }
            //Si la fecha de nacimiento o la fecha de ingreso estan fuera del rango permitido de fechas
            else if (calFechaNac.getDate().before(calFechaNac.getMinSelectableDate()) || calFechaNac.getDate().after(calFechaNac.getMaxSelectableDate()) || calFechaIngreso.getDate().before(calFechaIngreso.getMinSelectableDate()) || calFechaIngreso.getDate().after(calFechaIngreso.getMaxSelectableDate())) {
                lblError.setText("*Ingrese datos validos: fecha incorrecta");   //Mensaje de error
                Toolkit.getDefaultToolkit().beep();
            }
            //Si la diferencia entre la fecha de ingreso y la fecha de nacimiento no es de 18 años, siendo la fecha de ingreso la mayor
            else if (Empleado.CalcularDistanciaEntreFechas(calFechaNac.getDate(), calFechaIngreso.getDate()) < 18) {
                lblError.setText("<html><body>*Ingrese datos validos:<br> la fecha de ingreso debe ser al menos<br> 18 años mayor a la de nacimiento</html></body>");   //Mensaje de error
                Toolkit.getDefaultToolkit().beep();
            }
            //Si se elimina un empleado a nivel de base de datos y justo se estaba editando en el programa. Se comprueba que el empleado sigue existiendo
            else if (ConexionBD.EmpleadoExiste(Integer.parseInt(lblCedulaText.getText())) == false) {
                lblError.setText("<html><body>*Error: el empleado ya no existe en el sistema</html></body>");   //Mensaje de error
                DeshabilitarEdicion();
                PrepararTabla();
                Toolkit.getDefaultToolkit().beep();
            } 
            //Si nada de lo anterior sucede, se procede a guardar los cambios
            else {        
                Toolkit.getDefaultToolkit().beep();
                
                //Mensaje de confirmación
                int confirmarAccion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar\n los datos del empleado?", "Confirmar accion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                //Si se confirma la accion, se procede a actualizar los datos del empleado en la base de datos
                if (confirmarAccion == JOptionPane.OK_OPTION) {
                    //Se crea un objeto Empleado con los datos actualizados y se actualiza en la base de datos mediante el metodo ModificarEmpleado
                    Empleado empleadoModificacion = new Empleado(txtNombreCompleto.getText(), calFechaNac.getDate(),Integer.parseInt(lblCedulaText.getText()), Integer.parseInt(txtSalarioBase.getText()), calFechaIngreso.getDate());
                    ConexionBD.ModificarEmpleado(empleadoModificacion);
                    Toolkit.getDefaultToolkit().beep();
                    
                    //Mensaje que indica que la operación fue exitosa
                    JOptionPane.showConfirmDialog(null, "Datos modificados correctamente", "Operacion exitosa", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    DeshabilitarEdicion();
                    PrepararTabla();
                }
            }
        }
        //Si las casillas de fecha quedan vacias
        catch (NullPointerException ex) {
            lblError.setText("*Ingrese datos validos: fecha incorrecta");   //Mensaje de error
            Toolkit.getDefaultToolkit().beep();
        }
        

    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Se cancela la accion y se vacian las casillas
        DeshabilitarEdicion();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaEmpleadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadosMousePressed
        //Se reinicia el texto del label utilizado para mostrar mensajes de error
        lblError.setText("");
        
        //Si al cliquear la tabla se selecciona un empleado, se habilitan las casillas de texto para la edicion de la informacion
        if (!tablaEmpleados.getValueAt(tablaEmpleados.getSelectedRow(), 0).equals("")) {
            HabilitarEdicion();

        }
    }//GEN-LAST:event_tablaEmpleadosMousePressed

    private void txtNombreCompletoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCompletoKeyTyped
        //Se reinicia el texto del label utilizado para mostrar mensajes de error
        lblError.setText("");
        
        //Si el caracter ingresado es un número
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();  //No permite escribir
            lblError.setText("*No se permiten números en el nombre");   //Mensaje de error
            Toolkit.getDefaultToolkit().beep();
        } 
        //Si se llega a 40 caracteres, no se permiten mas
        else if (txtNombreCompleto.getText().length() == 40) {
            evt.consume();  //No permite escribir
        }
    }//GEN-LAST:event_txtNombreCompletoKeyTyped

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnIngresarEmpleado;
    private javax.swing.JButton btnRemoverEmpleado;
    private com.toedter.calendar.JDateChooser calFechaIngreso;
    private com.toedter.calendar.JDateChooser calFechaNac;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCedulaText;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFechaIngreso;
    private javax.swing.JLabel lblFechaNac;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSalarioBase;
    private javax.swing.JLabel lblTituloModDatos;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JPanel panelListarEmpleados;
    private javax.swing.JScrollPane scrlEmpleados;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtSalarioBase;
    // End of variables declaration//GEN-END:variables
}
