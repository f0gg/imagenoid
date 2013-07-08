package com.f0gg.imagenoid.swing;

import com.f0gg.imagenoid.utils.ImageResize;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author f0gg
 * @see http://github.com/f0gg
 */
public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnChoseImage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("imagenoid");

        btnChoseImage.setText("Choose XHDPI Image");
        btnChoseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChoseImageActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(btnChoseImage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(btnChoseImage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChoseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoseImageActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new ImageFileFilter(new String[] { "png","jpg" }, "PNG and JPG images"));
        fc.setDialogTitle("Select image file");
        fc.setMultiSelectionEnabled(false);
        fc.showOpenDialog(this);        
        if(fc.getSelectedFile()!=null) {
            try {
                String path = fc.getSelectedFile().getPath().replaceAll(fc.getSelectedFile().getName(), "");
                ImageResize ir = new ImageResize();
                
                // create dir
                String sufix = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
                String completePath = path + "imagenoid_" + sufix;
                File dir = new File(completePath);
                if(!dir.mkdir()) {
                    throw new RuntimeException("Unable to create destination folder");
                }
                
                BufferedImage originalImage = ImageIO.read(fc.getSelectedFile());
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                
                // ldpi
                BufferedImage resizeImagePng = ir.resizeImage(originalImage, type, ir.getSizeLdpi(originalImage));
                ImageIO.write(resizeImagePng, "jpg", new File(completePath + "/ldpi.jpg")); 

                // mdpi
                resizeImagePng = ir.resizeImage(originalImage, type, ir.getSizeMdpi(originalImage));
                ImageIO.write(resizeImagePng, "jpg", new File(completePath + "/mdpi.jpg")); 

                // hdpi
                resizeImagePng = ir.resizeImage(originalImage, type, ir.getSizeHdpi(originalImage));
                ImageIO.write(resizeImagePng, "jpg", new File(completePath + "/hdpi.jpg"));                 
                
                JOptionPane.showMessageDialog(this, "Done!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnChoseImageActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoseImage;
    // End of variables declaration//GEN-END:variables
}
