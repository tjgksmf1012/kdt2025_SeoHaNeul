package boardapp.ui;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import boardapp.model.dao.BoardDAO;
import boardapp.model.dao.BoardDAOImpl;
import boardapp.model.dto.Board;

public class BoardApp extends BoardMain {
    private BoardDAO dao;

    public BoardApp() {
        super();
        // 1) DAO 연결
        try {
            dao = new BoardDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadData();

        getBtnInsert().addActionListener(e -> {
            InsertDialog dlg = new InsertDialog(this);
            dlg.setVisible(true);
        });

        getJTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 &&
                    SwingUtilities.isLeftMouseButton(e)) {
                    int row = getJTable().getSelectedRow();
                    if (row == -1) return;
                    int seq = (int) getJTable().getValueAt(row, 0);
                    ViewDialog vdlg = new ViewDialog(BoardApp.this, seq);
                    vdlg.setVisible(true);
                }
            }
        });

        setVisible(true);
    }

    public void loadData() {
        try {
            List<Board> list = dao.selectBoardList();
            DefaultTableModel model = (DefaultTableModel) getJTable().getModel();
            model.setRowCount(0);
            for (Board b : list) {
                model.addRow(new Object[]{
                    b.getSeq(),
                    b.getTitle(),
                    b.getWriter(),
                    b.getWriteDate(),
                    b.getCnt()
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertBoard(Board board) {
        try {
            dao.insertBoard(board);
            loadData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateBoard(Board board) {
        try {
            dao.updateBoard(board);
            loadData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BoardApp());
    }
}
