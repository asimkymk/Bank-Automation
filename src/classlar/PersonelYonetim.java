/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classlar;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asimk
 */
public class PersonelYonetim extends javax.swing.JFrame {

    /**
     * Creates new form PersonelYonetim
     */
    Yonetici y1;
    public PersonelYonetim(Yonetici c1) {
        initComponents();
        tblKredi.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblKredi.getTableHeader().setOpaque(false);
        tblKredi.getTableHeader().setDefaultRenderer(new HeaderColor());
        tblKredi.getTableHeader().setForeground(new Color(255, 255, 255));
        tblKredi.setRowHeight(25);
        this.y1 = c1;
        DefaultTableModel m = (DefaultTableModel) tblKredi.getModel();
        m.setRowCount(0);
        try {
            ArrayList<Object[]> hesaplar2 = y1.calisanListele();
            for (int i = 0; i < hesaplar2.size(); i++) {
                m.addRow(hesaplar2.get(i));
            }
        } catch (Exception e) {
        }
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Kullanıcı Düzenleme");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKredi = new javax.swing.JTable();
        btnYoneticiKendiProfil = new javax.swing.JButton();
        btnYoneticiKendiProfil1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(20, 26, 54));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblKredi.setBackground(java.awt.Color.white);
        tblKredi.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        tblKredi.setForeground(new java.awt.Color(20, 26, 54));
        tblKredi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Çalışan ID", "Çalışan İsim", "Çalışan Tipi", "Çalışan Maaş"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKredi.setFocusable(false);
        tblKredi.setGridColor(java.awt.Color.white);
        tblKredi.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblKredi.setOpaque(false);
        tblKredi.setRowHeight(25);
        tblKredi.setSelectionBackground(new java.awt.Color(20, 11, 32));
        tblKredi.setSelectionForeground(java.awt.Color.white);
        tblKredi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKredi.getTableHeader().setReorderingAllowed(false);
        tblKredi.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                tblKrediAncestorRemoved(evt);
            }
        });
        jScrollPane1.setViewportView(tblKredi);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 860, 300));

        btnYoneticiKendiProfil.setBackground(java.awt.Color.white);
        btnYoneticiKendiProfil.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnYoneticiKendiProfil.setForeground(java.awt.Color.blue);
        btnYoneticiKendiProfil.setText("MAAŞ GÜNCELLE");
        btnYoneticiKendiProfil.setOpaque(false);
        btnYoneticiKendiProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYoneticiKendiProfilActionPerformed(evt);
            }
        });
        jPanel1.add(btnYoneticiKendiProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, 180, 50));

        btnYoneticiKendiProfil1.setBackground(java.awt.Color.white);
        btnYoneticiKendiProfil1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnYoneticiKendiProfil1.setForeground(java.awt.Color.blue);
        btnYoneticiKendiProfil1.setText("İŞTEN ÇIKAR");
        btnYoneticiKendiProfil1.setOpaque(false);
        btnYoneticiKendiProfil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYoneticiKendiProfil1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnYoneticiKendiProfil1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 180, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblKrediAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblKrediAncestorRemoved
        // TODO add your handling code here:

    }//GEN-LAST:event_tblKrediAncestorRemoved

    private void btnYoneticiKendiProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYoneticiKendiProfilActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel m = (DefaultTableModel) tblKredi.getModel();
            int row = tblKredi.getSelectedRow();
            String id = m.getValueAt(row, 0).toString();
            String pt = m.getValueAt(row, 2).toString();
            String isim = m.getValueAt(row, 1).toString();
            String msj = "Yeni maaşı giriniz : ";
            String input = JOptionPane.showInputDialog(null, msj);
            if (!input.isEmpty()) {
                float artirma = Float.valueOf(input);
                if(y1.maasAyarla(id,artirma,pt)){
                    JOptionPane.showMessageDialog(null, "Çalışanın maaşı başarıyla güncellendi.");
                    m.setRowCount(0);
                    try {
                        ArrayList<Object[]> hesaplar2 = y1.calisanListele();
                        for (int i = 0; i < hesaplar2.size(); i++) {
                            m.addRow(hesaplar2.get(i));
                        }
                    } catch (Exception e) {
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,
                    "Maaş güncellenmede bir hata oluştu.",
                    "İşlem alınamadı",
                    JOptionPane.ERROR_MESSAGE);
                }
                

            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null,
                    "Hatalı veri girişi yapıldı. Başvuru tamamlanamadı.",
                    "Başvuru Yapılamadı",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                    "Eksik veya hatalı giriş yapıldı. Lütfen limiti kontrol edin.",
                    "Başvuru Yapılamadı",
                    JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_btnYoneticiKendiProfilActionPerformed

    private void btnYoneticiKendiProfil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYoneticiKendiProfil1ActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel m = (DefaultTableModel) tblKredi.getModel();
        int row = tblKredi.getSelectedRow();
        String id = m.getValueAt(row, 0).toString();
        String pt = m.getValueAt(row, 2).toString();
        if(pt.equals("Yönetici")){
            JOptionPane.showMessageDialog(null,
                    "Yöneticiyi işten çıkartamazsınız.",
                    "İşlem alınamadı",
                    JOptionPane.ERROR_MESSAGE);
        }
        else{
            if (y1.istenCikar(id, pt)) {
                JOptionPane.showMessageDialog(null, "Çalışan başarıyla işten çıkarıldı.");
                m.setRowCount(0);
                try {
                    ArrayList<Object[]> hesaplar2 = y1.calisanListele();
                    for (int i = 0; i < hesaplar2.size(); i++) {
                        m.addRow(hesaplar2.get(i));
                    }
                } catch (Exception e) {
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "İşten çıkarmada bir sorun oluit",
                        "İşlem alınamadı",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        

        
    }//GEN-LAST:event_btnYoneticiKendiProfil1ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PersonelYonetim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonelYonetim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonelYonetim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonelYonetim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnYoneticiKendiProfil;
    private javax.swing.JButton btnYoneticiKendiProfil1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKredi;
    // End of variables declaration//GEN-END:variables
}
