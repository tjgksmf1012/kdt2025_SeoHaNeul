package boardapp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class BoardMain extends JFrame {
    private JTable jTable;
    private JPanel pSouth;
    private JButton btnInsert;
    
    public BoardMain() {
        setTitle("게시판 리스트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new JScrollPane(getJTable()), BorderLayout.CENTER);
        getContentPane().add(getPSouth(), BorderLayout.SOUTH);
        setSize(600, 450);
        setLocationRelativeTo(null);
    }

    protected JTable getJTable() {
        if (jTable == null) {
            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"번호","제목","글쓴이","날짜","조회수"}, 
                0
            ) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            jTable = new JTable(model);
            
            jTable.getColumn("번호").setPreferredWidth(20);
            jTable.getColumn("제목").setPreferredWidth(250);
            jTable.getColumn("글쓴이").setPreferredWidth(50);
            jTable.getColumn("날짜").setPreferredWidth(80);
            jTable.getColumn("조회수").setPreferredWidth(40);
            
            CenterTableCellRenderer ctcr = new CenterTableCellRenderer();
            for (int i = 0; i < jTable.getColumnCount(); i++) {
                jTable.getColumnModel().getColumn(i).setCellRenderer(ctcr);
            }
            
            jTable.setDefaultEditor(Object.class, null);
        }
        return jTable;
    }

    private JPanel getPSouth() {
        if (pSouth == null) {
            pSouth = new JPanel();
            pSouth.add(getBtnInsert());
        }
        return pSouth;
    }

    protected JButton getBtnInsert() {
        if (btnInsert == null) {
            btnInsert = new JButton("추가");
        }
        return btnInsert;
    }

    public class CenterTableCellRenderer extends JLabel implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            setFont(new Font(null, Font.PLAIN, 12));
            setHorizontalAlignment(JLabel.CENTER);
            setOpaque(true);
            setBackground(isSelected ? Color.YELLOW : Color.WHITE);
            return this;
        }
    }
}
