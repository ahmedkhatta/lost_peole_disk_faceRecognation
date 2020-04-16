package recognizer_and_connected_screen;

import helper.ConectaBanco;
import get_Data.ControlPerson;
import get_Data.ModelPerson;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class RecognizeFace extends javax.swing.JDialog {

    ModelPerson mod = new ModelPerson();
    ControlPerson cod = new ControlPerson();

    private RecognizeFace.DaemonThread myThread = null;

    //JavaCV 1.5.1
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C:\\photos\\haarcascade_frontalface_alt.xml");
    LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();

    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();

    //Vars
    String root, firstNamePerson, lastNamePerson, officePerson, dobPerson, telefone;
    //Social Info
    String facebook ;
    int idPerson;

    //Utils
    ConectaBanco conecta = new ConectaBanco();

    public RecognizeFace(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        recognizer.read("C:\\photos\\classifierLBPH.yml");
        recognizer.setThreshold(80);
        startCamera();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        label_name = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        label_phone = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        label_office = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDaylost = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        sendMessage_btn = new keeptoo.KButton();
        label_photo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txt_id_label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jPanel5 = new javax.swing.JPanel();
        social_link = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        txtlocation = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(95, 106, 117));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Hi!");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(132, 242, 145)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setForeground(new java.awt.Color(82, 82, 82));
        jLabel3.setText("Full Name:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel4.setForeground(new java.awt.Color(82, 82, 82));
        jLabel4.setText("Phone:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        kGradientPanel1.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel1.setkFillBackground(false);
        kGradientPanel1.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        kGradientPanel1.add(label_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 30));

        jPanel4.add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 190, 30));

        kGradientPanel2.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel2.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel2.setkFillBackground(false);
        kGradientPanel2.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        kGradientPanel2.add(label_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 30));

        jPanel4.add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 190, 30));

        kGradientPanel3.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setkEndColor(new java.awt.Color(57, 114, 227));
        kGradientPanel3.setkFillBackground(false);
        kGradientPanel3.setkStartColor(new java.awt.Color(122, 227, 192));
        kGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        kGradientPanel3.add(label_office, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 180, 30));

        jPanel4.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 190, 30));

        jLabel5.setForeground(new java.awt.Color(82, 82, 82));
        jLabel5.setText("Day Lost :");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));

        txtDaylost.setText(" ");
        txtDaylost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDaylostActionPerformed(evt);
            }
        });
        jPanel4.add(txtDaylost, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 180, 30));

        jLabel6.setForeground(new java.awt.Color(82, 82, 82));
        jLabel6.setText("Job :");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 400, 140));

        sendMessage_btn.setText("Send Message");
        sendMessage_btn.setFocusable(false);
        sendMessage_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sendMessage_btn.setkAllowTab(false);
        sendMessage_btn.setkEndColor(new java.awt.Color(118, 195, 118));
        sendMessage_btn.setkHoverEndColor(new java.awt.Color(22, 92, 22));
        sendMessage_btn.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        sendMessage_btn.setkHoverStartColor(new java.awt.Color(80, 142, 80));
        sendMessage_btn.setkPressedColor(new java.awt.Color(28, 72, 28));
        sendMessage_btn.setkStartColor(new java.awt.Color(87, 167, 87));
        sendMessage_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessage_btnActionPerformed(evt);
            }
        });
        jPanel1.add(sendMessage_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, 390, 30));
        sendMessage_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        label_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 360, 390));

        jLabel13.setBackground(new java.awt.Color(51, 153, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 153, 51));
        jLabel13.setText("Recognize Face");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(101, 199, 113));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setBackground(new java.awt.Color(90, 68, 193));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("ID Face");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 40));

        txt_id_label.setBackground(new java.awt.Color(132, 242, 145));
        txt_id_label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_id_label.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txt_id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 40, 40));

        jButton1.setBackground(new java.awt.Color(213, 83, 83));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Filled_Circle_15px_1.png"))); // NOI18N
        jButton1.setToolTipText("Close");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 30, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 410, 40));

        kGradientPanel4.setkEndColor(new java.awt.Color(64, 79, 225));
        kGradientPanel4.setkStartColor(new java.awt.Color(110, 208, 137));
        kGradientPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        kGradientPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 390, 90));

        social_link.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        social_link.setForeground(new java.awt.Color(255, 255, 255));
        social_link.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        kGradientPanel4.add(social_link, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 320, 50));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Your social network");
        kGradientPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Facebook_30px.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        kGradientPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 50, -1));

        jPanel1.add(kGradientPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 390, 70));

        kGradientPanel5.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel5.setkFillBackground(false);
        kGradientPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtlocation.setText(" ");
        kGradientPanel5.add(txtlocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 370, 30));

        jLabel2.setForeground(new java.awt.Color(82, 82, 82));
        jLabel2.setText("Gmail :");
        kGradientPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, -1, 20));

        txtGmail.setText(" ");
        kGradientPanel5.add(txtGmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 370, 30));

        jLabel7.setForeground(new java.awt.Color(82, 82, 82));
        jLabel7.setText("Location :");
        kGradientPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(158, 158, 159));
        jLabel11.setText("Personal Information");
        kGradientPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        jPanel1.add(kGradientPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 460));

        setSize(new java.awt.Dimension(820, 460));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                URI site = new URI("http://www.facebook.com/" + facebook);
                desktop.browse(site);
            } catch (IOException | URISyntaxException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        stopCamera();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sendMessage_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessage_btnActionPerformed
        new SendWhatsapp(this, true, firstNamePerson, telefone).setVisible(true);
    }//GEN-LAST:event_sendMessage_btnActionPerformed

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        social_link.setText(facebook);
    }//GEN-LAST:event_jButton6MouseEntered

    private void txtDaylostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDaylostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDaylostActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RecognizeFace dialog = new RecognizeFace(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_office;
    private javax.swing.JLabel label_phone;
    private javax.swing.JLabel label_photo;
    private keeptoo.KButton sendMessage_btn;
    private javax.swing.JLabel social_link;
    private javax.swing.JTextField txtDaylost;
    private javax.swing.JTextField txtGmail;
    private javax.swing.JLabel txt_id_label;
    private javax.swing.JTextField txtlocation;
    // End of variables declaration//GEN-END:variables

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo.getGraphics();

                            Mat imageGray = new Mat();
                            cvtColor(cameraImage, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFace = new RectVector();
                            cascade.detectMultiScale(imageGray, detectedFace, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFace.size(); i++) {
                                Rect dadosFace = detectedFace.get(i);
                                rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                Mat faceCapturada = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = rotulo.get(0);
                                String nome;
                                nome = firstNamePerson;

                                if (prediction == -1) {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 0, 255, 3), 3, 0, 0);
                                    idPerson = 0;
                                    label_name.setText("NO data");
                                    txt_id_label.setText("");
                                    label_office.setText("");
                                    label_phone.setText("");
                                    sendMessage_btn.setText("Send Message");
                                    facebook = "";
                                    txtDaylost.setText("") ;
                                   txtGmail.setText("") ;
                                   txtlocation.setText("");
                                
                                           
                                } else {
                                    rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                    System.out.println(confidence.get(0));
                                    idPerson = prediction;
 
                                    rec();
                                }
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;

                            try {
                                if (g.drawImage(buff, 0, 0, 360, 390, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    private void rec() {
        //Retrieve data from database
        new Thread() {
            @Override
            public void run() {
                conecta.conexao();
                try {
                    conecta.executaSQL("SELECT * FROM person WHERE id = " + String.valueOf(idPerson));
                    while (conecta.rs.next()) {
                        firstNamePerson = conecta.rs.getString("first_name");
                        jLabel10.setText("Hi, " + firstNamePerson + " " + conecta.rs.getString("last_name"));
                        label_name.setText(conecta.rs.getString("first_name") + " " + conecta.rs.getString("last_name"));
                        label_office.setText(conecta.rs.getString("office"));
                     
                       label_phone.setText(conecta.rs.getString("phone_number"));
                        sendMessage_btn.setText("Send Message to " + conecta.rs.getString("phone_number"));
                        txt_id_label.setText(conecta.rs.getString("id"));

 
                        facebook = conecta.rs.getString("profile_facebook");
                       
                        txtGmail.setText(conecta.rs.getString("email")); 
                       txtDaylost.setText(conecta.rs.getString("day_lost")); 
                       
                        txtlocation.setText(conecta.rs.getString("location")); 
                        Array ident = conecta.rs.getArray("first_name");
                        String[] person = (String[]) ident.getArray();

                        for (String person1 : person) {
                            System.out.println(person1);
                        }

                    }
                } catch (Exception e) {
                }
                conecta.desconecta();
            }
        }.start();
    }

    public void stopCamera() {
        try {
            myThread.runnable = false;
            webSource.release();
            dispose();
        } catch (Exception e) {
        }
    }

    public void startCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);
                myThread = new RecognizeFace.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }
}
