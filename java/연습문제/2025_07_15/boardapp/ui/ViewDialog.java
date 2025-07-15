package boardapp.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import boardapp.model.dao.BoardDAO;
import boardapp.model.dao.BoardDAOImpl;
import boardapp.model.dto.Board;

public class ViewDialog extends JDialog {
    private final BoardApp parent;
    private final int seq;
    private BoardDAO dao;

    private JTextField txtTitle;
    private JTextField txtWriter;
    private JTextArea  txtContent;
    private JButton    btnUpdate;
    private JButton    btnDelete;
    private JButton    btnClose;

    public ViewDialog(BoardApp parent, int seq) {
        super(parent, "게시물 보기", true);
        this.parent = parent;
        this.seq    = seq;

        try {
            dao = new BoardDAOImpl();
            
            dao.incrementCount(seq);
            parent.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        loadData();

        pack();
        setSize(450, 300);
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        ((JComponent)getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        center.add(new JLabel("제목"), gbc);

        txtTitle = new JTextField(20);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        center.add(txtTitle, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        center.add(new JLabel("글쓴이"), gbc);

        txtWriter = new JTextField(20);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        center.add(txtWriter, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        center.add(new JLabel("내용"), gbc);

        txtContent = new JTextArea();
        txtContent.setBorder(new EtchedBorder());
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        txtContent.setRows(8);
        txtContent.setColumns(20);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        center.add(txtContent, gbc);

        add(center, BorderLayout.CENTER);

        JPanel south = new JPanel();
        btnUpdate = new JButton("수정");
        btnDelete = new JButton("삭제");
        btnClose  = new JButton("닫기");
        south.add(btnUpdate);
        south.add(btnDelete);
        south.add(btnClose);
        add(south, BorderLayout.SOUTH);

        btnClose.addActionListener(e -> dispose());

        btnDelete.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(
                this, "정말 삭제하시겠습니까?", "삭제 확인",
                JOptionPane.YES_NO_OPTION
            );
            if (res == JOptionPane.YES_OPTION) {
                try {
                    dao.deleteBoard(seq);
                    parent.loadData();
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "삭제 중 오류 발생", "에러", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                Board b = new Board();
                b.setSeq(seq);
                b.setTitle(txtTitle.getText().trim());
                b.setWriter(txtWriter.getText().trim());
                b.setContent(txtContent.getText().trim());
                dao.updateBoard(b);
                parent.loadData();
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "수정 중 오류 발생", "에러", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void loadData() {
        try {
            Board b = dao.selectBoard(seq);
            txtTitle.setText(b.getTitle());
            txtWriter.setText(b.getWriter());
            txtContent.setText(b.getContent());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터 로드 중 오류 발생", "에러", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }
}
