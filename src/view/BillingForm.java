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
           
            java.awt.Image bgImage = new javax.swing.ImageIcon(getClass().getResource("/view/images/bg7.jpg")).getImage();
            
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        });
        
        
        
        
        
        
        initComponents();
        loadSessionData();
        
        
        // applied custom pdf button to 5th column
        tblBills.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tblBills.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new javax.swing.JCheckBox()));
        
        
        
        
        
        txtApptNo.setText("A");
        
        
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
        lblWelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGenerate.setText("Generate & Print Bill");
        btnGenerate.addActionListener(this::btnGenerateActionPerformed);

        btnBack.setText("Back to Dashboard");
        btnBack.addActionListener(this::btnBackActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Appointment No:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Bill No:");

        tblBills.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Bill No", "Appt No", "Patient Name", "Total Cost", "Action"
            }
        ));
        jScrollPane1.setViewportView(tblBills);

        tblPending.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Appt No", "Patient Name", "Time", "Expected Total (LKR)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblPending);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Pending Payments (Today)");

        txtAsciiLogo.setEditable(false);
        txtAsciiLogo.setColumns(20);
        txtAsciiLogo.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtAsciiLogo.setRows(5);
        txtAsciiLogo.setOpaque(false);
        jScrollPane3.setViewportView(txtAsciiLogo);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Paid Payments");

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblWelcome.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(270, 270, 270)
                        .addComponent(lblWelcome)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(259, 259, 259))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(406, 406, 406)
                            .addComponent(jLabel4))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(336, 336, 336)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(307, 307, 307)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addGap(50, 50, 50)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtApptNo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblWelcome))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBillNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApptNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(19, Short.MAX_VALUE))
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
            "Bill Generated Successfully!\n\n" +
            "• PDF Invoice saved locally.\n" +
            "• Secure email sent (if patient has a registered email address).\n" +
            "• Use the 'View Bill' button in the table to open the PDF.",
            "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        txtApptNo.setText("");
        //txtBillNo.setText("");
        txtApptNo.setText("A");
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
                    rs.getString("appt_time"),
                    rs.getDouble("expected_total")
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
                    rs.getDouble("total_cost"),
                    "View Bill"
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
    
        
    private void loadSessionData() {
        String currentUser = pattern.UserSession.getInstance().getLoggedInUser();
        if (currentUser != null) {
            lblWelcome.setText("Staff: " + currentUser);
        } else {
            lblWelcome.setText("User: Guest");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // INNER CLASS FOR RENDERING THE PDF BUTTON
    class ButtonRenderer extends javax.swing.JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("View Bill");
            return this;
        }
    }

    // INNER CLASS FOR HANDLING PDF BUTTON CLICKS
    class ButtonEditor extends javax.swing.DefaultCellEditor {
        protected javax.swing.JButton button;
        private javax.swing.JTable table;
        private boolean isPushed;

        public ButtonEditor(javax.swing.JCheckBox checkBox) {
            super(checkBox);
            button = new javax.swing.JButton();
            button.setOpaque(true);
            button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }
        @Override
        public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.table = table;
            button.setText("View Bill");
            isPushed = true;
            return button;
        }
        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // get the Bill No from the first column of the clicked row
                String billNo = table.getValueAt(table.getSelectedRow(), 0).toString();
                openPDF(billNo);
            }
            isPushed = false;
            return "View Bill";
        }
        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
        
        // command to open the PDF in Chrome or Adobe
        private void openPDF(String billNo) {
            try {
                java.io.File pdfFile = new java.io.File("bills/Bill_" + billNo + ".pdf");
                if (pdfFile.exists()) {
                    java.awt.Desktop.getDesktop().open(pdfFile);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(button, "PDF not found on server!", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println("Error opening PDF: " + ex.getMessage());
            }
        }
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
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JTable tblBills;
    private javax.swing.JTable tblPending;
    private javax.swing.JTextField txtApptNo;
    private javax.swing.JTextArea txtAsciiLogo;
    private javax.swing.JTextField txtBillNo;
    // End of variables declaration//GEN-END:variables
}
