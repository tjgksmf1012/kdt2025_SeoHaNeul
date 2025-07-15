package boardapp.model.dao;

import java.util.List;
import boardapp.model.dto.Board;

public interface BoardDAO {
    List<Board> selectBoardList() throws Exception;
    void insertBoard(Board board) throws Exception;
    Board selectBoard(int seq) throws Exception;
    void updateBoard(Board board) throws Exception;
    void deleteBoard(int seq) throws Exception;
    void incrementCount(int seq) throws Exception;
}