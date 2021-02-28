package Presentacion;

//Clases importadas
import java.awt.Toolkit;
import javax.swing.ImageIcon;   //Para definir el icono de la aplicación
import javax.swing.JOptionPane; //Para utilizar ventanas emergentes

public class FrmPrincipal extends javax.swing.JFrame {

    public FrmPrincipal() {       
        Inicializar();   
    }
    
    //Metodo que inicializa la ventana con sus parametros correspondientes
    private void Inicializar(){
        initComponents();   //Se inicializan los componentes graficos        
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icon.png")).getImage());   //Se establece el icono de la aplicacion
        setLocationRelativeTo(null);    //Posiciona la ventana en el centro de la pantalla
        this.setTitle("Gestión de empleados");  //Se establece el titulo de la ventana
    }
    //Metodo que ejecuta la accion de salir del programa
    private void Salir(){
        Toolkit.getDefaultToolkit().beep();
        
        //Ventana emergente para confirmar la salida
        int confirmar_salida = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmar_salida == JOptionPane.OK_OPTION)
            System.exit(0);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInicio = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        panelTexto = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblCopyright = new javax.swing.JLabel();
        panelDecoracion = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestión de empleados");
        setBackground(new java.awt.Color(101, 171, 158));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelInicio.setBackground(new java.awt.Color(220, 247, 242));
        panelInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnIniciar.setBackground(new java.awt.Color(220, 247, 242));
        btnIniciar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(35, 59, 57));
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(220, 247, 242));
        btnSalir.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(35, 59, 57));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        panelTexto.setBackground(new java.awt.Color(157, 224, 220));
        panelTexto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(35, 59, 57)));
        panelTexto.setForeground(new java.awt.Color(255, 255, 255));

        lblTitulo.setBackground(new java.awt.Color(220, 247, 242));
        lblTitulo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(35, 59, 57));
        lblTitulo.setText("Bienvenido a la aplicación de gestion de empleados");
        lblTitulo.setToolTipText("");

        lblCopyright.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lblCopyright.setForeground(new java.awt.Color(35, 59, 57));
        lblCopyright.setText("© agusmf1999, 2021");

        javax.swing.GroupLayout panelTextoLayout = new javax.swing.GroupLayout(panelTexto);
        panelTexto.setLayout(panelTextoLayout);
        panelTextoLayout.setHorizontalGroup(
            panelTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTextoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTextoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCopyright)
                .addGap(4, 4, 4))
        );
        panelTextoLayout.setVerticalGroup(
            panelTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTextoLayout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(lblCopyright)
                .addGap(3, 3, 3))
        );

        panelDecoracion.setBackground(new java.awt.Color(101, 171, 158));

        javax.swing.GroupLayout panelDecoracionLayout = new javax.swing.GroupLayout(panelDecoracion);
        panelDecoracion.setLayout(panelDecoracionLayout);
        panelDecoracionLayout.setHorizontalGroup(
            panelDecoracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );
        panelDecoracionLayout.setVerticalGroup(
            panelDecoracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addComponent(panelDecoracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInicioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelInicioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicioLayout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addComponent(panelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
            .addComponent(panelDecoracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Salir();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Salir();
    }//GEN-LAST:event_formWindowClosing

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        //Se abre la siguiente ventana y se cierra la actual
        new FrmGestionEmpleados().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnIniciarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelDecoracion;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelTexto;
    // End of variables declaration//GEN-END:variables
}
