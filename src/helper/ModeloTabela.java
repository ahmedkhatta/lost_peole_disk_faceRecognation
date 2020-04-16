package helper;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel {

    private String path;

    private ArrayList lines = null;
    private String[] columns = null;

    public ModeloTabela(ArrayList lin, String[] col) {
        setLinhas(lin);
        setColunas(col);
    }

    public ModeloTabela() {
        throw new UnsupportedOperationException("Not supported yet.");  
    }

    public ArrayList getLinhas() {
        return lines;
    }

    public void setLinhas(ArrayList dados) {
        lines = dados;
    }

    public String[] getColunas() {
        return columns;
    }

    public void setColunas(String[] nomes) {
        columns = nomes;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public String getColumnName(int numCol) {
        return columns[numCol];
    }

    @Override
    public Object getValueAt(int numLin, int numCol) {
        Object[] linha = (Object[]) getLinhas().get(numLin);
        return linha[numCol];
    }

}
