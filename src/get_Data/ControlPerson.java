package get_Data;

import helper.ConectaBanco;
import helper.ModeloTabela;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class ControlPerson {

    ConectaBanco conecta = new ConectaBanco();

    public void insert(ModelPerson mod) {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));

        try {
            conecta.conexao();
            PreparedStatement pst = conecta.conn.prepareStatement("INSERT INTO person (id, first_name, last_name, phone_number, office, profile_facebook, day_lost, email, location, register_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, mod.getId());
            pst.setString(2, mod.getFirst_name());
            pst.setString(3, mod.getLast_name());
            pst.setString(4, mod.getDob());
            pst.setString(5, mod.getOffice());
            pst.setString(6, mod.getFacebook());
            pst.setString(7, mod.getday_lost());
            pst.setString(8, mod.getemail());
            pst.setString(9, mod.getlocation());
            pst.setString(10, date);
            pst.executeUpdate();
            System.out.println("Dice do(a): " + mod.getFirst_name() + " registered");
            conecta.desconecta();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void update(ModelPerson mod, int id) {
        conecta.conexao();
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE person SET first_name= ?, last_name= ?, phone_number= ?, office= ?, profile_facebook= ?, day_lost= ?, email=?, location=?, register_date=? WHERE id=?");
            pst.setString(1, mod.getFirst_name());
            pst.setString(2, mod.getLast_name());
            pst.setString(3, mod.getDob());
            pst.setString(4, mod.getOffice());
            pst.setString(5, mod.getFacebook());
            pst.setString(6, mod.getday_lost());
            pst.setString(7, mod.getemail());
            pst.setString(8, mod.getlocation());
            pst.setInt(9, id);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating data \\ n ERROR: " + ex);
        }
        conecta.desconecta();
    }

    public void delete(int id) {
        conecta.conexao();
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("DELETE FROM person WHERE id= '" + id + "'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully deleted!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting apartment. Try again!");
        } finally {
            conecta.desconecta();
        }
    }

    public void preencherTabela(String SQL, JTable tabela) {
        String id = null;

        conecta.conexao();
        ArrayList dados = new ArrayList();
        String[] Colunas = new String[]{"", "ID", "Name", "Phone", "JOB", "Facebook", "DayLost", "Gmail", "Location"};
        conecta.executaSQL(SQL);
        try {
            conecta.rs.first();
            do {
                dados.add(new Object[]{
                    "",
                    conecta.rs.getString("id"),
                    conecta.rs.getString("first_name") + " " + conecta.rs.getString("last_name"),
                    conecta.rs.getString("phone_number"),
                     conecta.rs.getString("office"),
                    conecta.rs.getString("profile_facebook"),
                    conecta.rs.getString("day_lost"),
                    conecta.rs.getString("email"),
                    conecta.rs.getString("location")});
            } while (conecta.rs.next());
        } catch (SQLException ex) {
             
        } finally {
            conecta.desconecta();
        }

        ModeloTabela modelo = new ModeloTabela(dados, Colunas);
        tabela.setModel((TableModel) modelo);
        tabela.getColumnModel().getColumn(0).setCellRenderer(new ControlPerson.ImageRenderer());
        tabela.getColumnModel().getColumn(1).setMaxWidth(0);
        tabela.getColumnModel().getColumn(1).setMinWidth(0);
        tabela.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tabela.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    class ImageRenderer implements TableCellRenderer {

        public JLabel lbl = new JLabel();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            try {
                Object text = table.getValueAt(row, 1);
                File image = new File("C:\\photos\\person." + text + ".1.jpg");
                String path = image.getAbsolutePath();
                ImageIcon i = new ImageIcon(new ImageIcon(String.valueOf(path)).getImage().getScaledInstance(lbl.getWidth() + 50, lbl.getHeight() + 50, Image.SCALE_SMOOTH));
                lbl.setIcon(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lbl;
        }
    }

    public void editar(ModelPerson mod, int id) {
        conecta.conexao();
        try {
            PreparedStatement pst = conecta.conn.prepareStatement("UPDATE apto SET apto= ? WHERE id=? ");
            pst.setString(1, mod.getFirst_name());
            pst.setInt(2, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully changed");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error changing apartment. Try again!");
        } finally {
            conecta.desconecta();
        }
    }

}
