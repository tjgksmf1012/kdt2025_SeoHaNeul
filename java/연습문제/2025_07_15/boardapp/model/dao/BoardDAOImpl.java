package boardapp.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import boardapp.model.dto.Board;

public class BoardDAOImpl implements BoardDAO {
    private Connection conn;
    
    public void incrementCount(int seq) throws Exception {
        String sql = "UPDATE boardtbl SET cnt = cnt + 1 WHERE seq = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, seq);
            ps.executeUpdate();
        }
    }

    public BoardDAOImpl() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/boardapp?serverTimezone=UTC",
            "root",          // MySQL 계정
            "ilike1012!"  // 비밀번호
        );
    }

    @Override
    public List<Board> selectBoardList() throws Exception {
        List<Board> list = new ArrayList<>();
        String sql = "SELECT seq, title, writer, content, write_date, cnt "
                   + "FROM boardtbl ORDER BY seq DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Board b = new Board(
                    rs.getInt("seq"),
                    rs.getString("title"),
                    rs.getString("writer"),
                    rs.getString("content"),
                    rs.getTimestamp("write_date"),
                    rs.getInt("cnt")
                );
                list.add(b);
            }
        }
        return list;
    }

    @Override
    public void insertBoard(Board board) throws Exception {
        // AUTO_INCREMENT 사용 시 seq, write_date, cnt는 생략
        String sql = "INSERT INTO boardtbl(title, writer, content) VALUES(?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getWriter());
            ps.setString(3, board.getContent());
            ps.executeUpdate();
        }
    }

    @Override
    public Board selectBoard(int seq) throws Exception {
        String sql = "SELECT seq, title, writer, content, write_date, cnt "
                   + "FROM boardtbl WHERE seq = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, seq);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Board(
                        rs.getInt("seq"),
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getString("content"),
                        rs.getTimestamp("write_date"),
                        rs.getInt("cnt")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void updateBoard(Board board) throws Exception {
        String sql = "UPDATE boardtbl SET title=?, writer=?, content=? WHERE seq=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getWriter());
            ps.setString(3, board.getContent());
            ps.setInt(4, board.getSeq());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteBoard(int seq) throws Exception {
        String sql = "DELETE FROM boardtbl WHERE seq=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, seq);
            ps.executeUpdate();
        }
    }
}
