package boardapp.model.dto;

import java.util.Date;

public class Board {
    private int seq;
    private String title;
    private String writer;
    private String content;
    private Date writeDate;
    private int cnt;

    public Board() {}

    public Board(int seq, String title, String writer, String content, Date writeDate, int cnt) {
        this.seq = seq;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.writeDate = writeDate;
        this.cnt = cnt;
    }

    public int getSeq() { return seq; }
    public void setSeq(int seq) { this.seq = seq; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getWriteDate() { return writeDate; }
    public void setWriteDate(Date writeDate) { this.writeDate = writeDate; }

    public int getCnt() { return cnt; }
    public void setCnt(int cnt) { this.cnt = cnt; }
}