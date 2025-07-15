package boardapp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import boardapp.model.dto.Board;

public class InsertDialog extends JDialog {
    private BoardApp boardApp;
    private JPanel pCenter, pTitle, pContent, pWriter, pSouth;
    private JTextField txtTitle, txtWriter;
    private JTextArea txtContent;
    private JButton btnOk, btnCancel;
    
    public InsertDialog(BoardApp boardApp) {
        super(boardApp, "게시물 작성", true);
        this.boardApp = boardApp;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 270);
        setLocationRelativeTo(boardApp);
        getContentPane().add(getPCenter(), BorderLayout.CENTER);
        getContentPane().add(getPSouth(), BorderLayout.SOUTH);
    }

    private JPanel getPCenter() {
        if (pCenter == null) {
            pCenter = new JPanel();
            pCenter.setBorder(new EmptyBorder(10,10,10,10));
            pCenter.add(getPTitle());
            pCenter.add(getPWriter());
            pCenter.add(getPContent());
        }
        return pCenter;
    }

    private JPanel getPTitle() {
        if (pTitle == null) {
            pTitle = new JPanel(new BorderLayout());
            JLabel lbl = new JLabel("제목");
            lbl.setPreferredSize(new Dimension(70, 30));
            lbl.setHorizontalAlignment(JLabel.CENTER);
            pTitle.add(lbl, BorderLayout.WEST);
            txtTitle = new JTextField();
            txtTitle.setPreferredSize(new Dimension(300, 30));
            pTitle.add(txtTitle, BorderLayout.CENTER);
        }
        return pTitle;
    }

    private JPanel getPWriter() {
        if (pWriter == null) {
            pWriter = new JPanel(new BorderLayout());
            JLabel lbl = new JLabel("글쓴이");
            lbl.setPreferredSize(new Dimension(70, 30));
            lbl.setHorizontalAlignment(JLabel.CENTER);
            pWriter.add(lbl, BorderLayout.WEST);
            txtWriter = new JTextField();
            txtWriter.setPreferredSize(new Dimension(300, 30));
            pWriter.add(txtWriter, BorderLayout.CENTER);
        }
        return pWriter;
    }

    private JPanel getPContent() {
        if (pContent == null) {
            pContent = new JPanel(new BorderLayout());
            JLabel lbl = new JLabel("내용");
            lbl.setPreferredSize(new Dimension(70, 30));
            lbl.setHorizontalAlignment(JLabel.CENTER);
            pContent.add(lbl, BorderLayout.WEST);
            txtContent = new JTextArea();
            txtContent.setBorder(new EtchedBorder());
            txtContent.setPreferredSize(new Dimension(300, 100));
            pContent.add(txtContent, BorderLayout.CENTER);
        }
        return pContent;
    }

    private JPanel getPSouth() {
        if (pSouth == null) {
            pSouth = new JPanel();
            pSouth.setBackground(Color.WHITE);
            pSouth.add(getBtnOk());
            pSouth.add(getBtnCancel());
        }
        return pSouth;
    }

    private JButton getBtnOk() {
        if (btnOk == null) {
            btnOk = new JButton("저장");
            btnOk.addActionListener((ActionEvent e) -> {
                String title = txtTitle.getText().trim();
                String writer = txtWriter.getText().trim();
                String content = txtContent.getText().trim();
                if (title.isEmpty() || writer.isEmpty() || content.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요", "경고", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Board b = new Board();
                b.setTitle(title);
                b.setWriter(writer);
                b.setContent(content);
                boardApp.insertBoard(b);
                dispose();
            });
        }
        return btnOk;
    }

    private JButton getBtnCancel() {
        if (btnCancel == null) {
            btnCancel = new JButton("취소");
            btnCancel.addActionListener(e -> dispose());
        }
        return btnCancel;
    }
}