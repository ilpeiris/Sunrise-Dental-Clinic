/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author ilpeiris
 */
public class BillingForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BillingForm.class.getName());

    /**
     * Creates new form BillingForm
     */
    public BillingForm() {
        
        // BG
        setContentPane(new javax.swing.JPanel() {
           
            java.awt.Image bgImage = new javax.swing.ImageIcon(getClass().getResource("/view/images/wbg.jpg")).getImage();
            
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        });
        
        initComponents();
        
        
        
        
        
// --- ASCII LOGO UI ---
        String asciiArt = 
        "                                                  :                                                  \n" +
        "                                                  :                                                  \n" +
        "                                           -     -:-     -                                           \n" +
        "                                   -       --    ---    --       :                                   \n" +
        "                                    :-      --   ---   --      --                                    \n" +
        "                                     ---     --  ---  ---    ---                                     \n" +
        "                                      ----  :::::::::::::  ---                                       \n" +
        "                               ---      ---------------------      ---                               \n" +
        "                                  ---- -----*#+-------=++----- ----                                  \n" +
        "                                    - ---###=--*##++++-:-+++--- -                                    \n" +
        "                          ------     ---##:.............:.:++---     ------                          \n" +
        "                               --------=#+............:+*=.=*---------                               \n" +
        "                                    ====#*..............:..+*===-                                    \n" +
        "                                    ====##-...............-*+====                                    \n" +
        "                           -------= ==+++*#:.............:**+++== --------                           \n" +
        "                                 +++++++++*#.............**+++++++++                                 \n" +
        "                             +++++++++++*+*#-...:###:...-**+++++++++++++                             \n" +
        "                          *++*** ############...####*...**########## ****+*                          \n" +
        "                  ********** ################-.=#   *=.:**############## **********                  \n" +
        "                     ###########          ####+##   *+=**##          ###########                     \n" +
        "                                              #       *                                              \n" +
        "                %%%%%%   %%%%     %%% %%%%     %%%  %%%%%%%%    %%%   %%%%%%   %%%%%%%%              \n" +
        "               %%%%%%%%  %%%%    %%%% %%%%%    %%%  %%%%%%%%%% %%%%  %%%%%%%%  %%%%%%%%              \n" +
        "              %%%%%      %%%%    %%%% %%%%%%   %%%  %%%   %%%% %%%% %%%%%      %%%                   \n" +
        "                %%%%%%%  %%%%    %%%% %%%%%%%% %%%  %%%%%%%%%  %%%%  %%%%%%%%  %%%%%%%               \n" +
        "                   %%%%% %%%%    %%%% %%%% %%%%%%%  %%%%%%%%   %%%%      %%%%% %%%                   \n" +
        "              %%%%%%%%%%  %%%%%%%%%%  %%%%   %%%%%  %%%  %%%%  %%%% %%%%%%%%%  %%%%%%%%              \n" +
        "               %%%%%%%     %%%%%%%    %%%%    %%%%  %%%   %%%% %%%%  %%%%%%%   %%%%%%%%              \n" +
        "                                                                                                     \n" +
        "              *********  **** **  ****  *  **  **     * *  *  ** **** ** * *  *********              \n" +
        "                         ***  *** * **  * **** ***    ***  **  * **** ** ***                         \n" +
        "                                                                                                     \n";
        
        txtAsciiLogo.setText(asciiArt);
        txtAsciiLogo.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 5));
        
        
        
        
        
        
        // --- Global Window Settings ---
        //// Centers & Disables resizing the window on the screen
        setTitle("Sunrise Dental Clinic - Billing & Receipts");
        setLocationRelativeTo(null); 
        setResizable(false);   
        
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/view/images/logo.png")).getImage());
        
        
        generateNextBillNo();
        loadBillTable();
        loadPendingTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtApptNo = new javax.swing.JTextField();
        txtBillNo = new javax.swing.JTextField();
        btnGenerate = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBills = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPending = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAsciiLogo = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGenerate.setText("Generate & Print Bill");
        btnGenerate.addActionListener(this::btnGenerateActionPerformed);

        btnBack.setText("Back to Dashboard");
        btnBack.addActionListener(this::btnBackActionPerformed);

        jLabel1.setText("Appointment No:");

        jLabel2.setText("Bill No:");

        tblBills.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Bill No", "Appt No", "Patient Name", "Total Cost"
            }
        ));
        jScrollPane1.setViewportView(tblBills);

        tblPending.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Appt No", "Patient Name", "Time"
            }
        ));
        jScrollPane2.setViewportView(tblPending);

        jLabel3.setText("Pending Payments (Today)");

        txtAsciiLogo.setEditable(false);
        txtAsciiLogo.setColumns(20);
        txtAsciiLogo.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtAsciiLogo.setRows(5);
        txtAsciiLogo.setOpaque(false);
        jScrollPane3.setViewportView(txtAsciiLogo);

        jLabel4.setText("Paid Payments");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(206, 206, 206))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(236, 236, 236))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApptNo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(173, 173, 173))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(220, 220, 220))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(293, 293, 293))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtApptNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnBack)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        // TODO add your handling code here:
        
        
        
        String apptNo = txtApptNo.getText().trim();
    String billNo = txtBillNo.getText().trim();

    if (apptNo.isEmpty() || billNo.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Appointment Number and Bill Number are required!", 
            "Validation Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    
    service.BillingService billService = new service.BillingService();
    boolean success = billService.processBilling(apptNo, billNo);

    if (success) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Bill Generated & Printed Successfully!\nPlease check the system console for the receipt.");
        txtApptNo.setText("");
        //txtBillNo.setText("");
        
        generateNextBillNo();
        loadBillTable();
        loadPendingTable();
        
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Error generating bill. Please verify the Appointment Number is correct.", 
            "Database Error", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
        
        
    }//GEN-LAST:event_btnGenerateActionPerformed

    
    private void loadPendingTable() {
        try {
            dao.AppointmentDAO apptDao = new dao.AppointmentDAO();
            java.sql.ResultSet rs = apptDao.getPendingAppointmentsToday();
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblPending.getModel();
            model.setRowCount(0); 
            
            while (rs != null && rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("appointment_no"),
                    rs.getString("name"),
                    rs.getString("appt_time")
                });
            }
        } catch (Exception e) { 
            System.out.println(e.getMessage()); 
        }
    }
    
    private void loadBillTable() {
        try {
            dao.BillDAO dao = new dao.BillDAO();
            java.sql.ResultSet rs = dao.getAllBills();
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblBills.getModel();
            model.setRowCount(0); 
            
            while (rs != null && rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("bill_no"),
                    rs.getString("appointment_no"),
                    rs.getString("name"),
                    rs.getDouble("total_cost")
                });
            }
        } catch (Exception e) {
            System.out.println("Table Load Error: " + e.getMessage());
        }
    }
    
    
    
    
    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        
        DashboardForm dashboard = new DashboardForm();
        dashboard.setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_btnBackActionPerformed

    
    
    private void generateNextBillNo() {
        dao.BillDAO dao = new dao.BillDAO();
        txtBillNo.setText(dao.getAutoBillNo());
        txtBillNo.setEditable(false); 
    }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BillingForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblBills;
    private javax.swing.JTable tblPending;
    private javax.swing.JTextField txtApptNo;
    private javax.swing.JTextArea txtAsciiLogo;
    private javax.swing.JTextField txtBillNo;
    // End of variables declaration//GEN-END:variables
}
