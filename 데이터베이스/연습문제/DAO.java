```
package javadb.sec02;

import java.sql.*;
import java.util.*;

class Dept {
    private int deptno;
    private String deptname;
    private String loc;

    public Dept(int deptno, String deptname, String loc) {
        this.deptno = deptno;
        this.deptname = deptname;
        this.loc = loc;
    }

    public int getDeptno() { return deptno; }
    public String getDeptname() { return deptname; }
    public String getLoc() { return loc; }

    public void setLoc(String loc) { this.loc = loc; }

    @Override
    public String toString() {
        return String.format("학과번호: %d, 이름: %s, 호실: %s", deptno, deptname, loc);
    }
}

interface DeptDAO {
    List<Dept> getAll();
    boolean insert(Dept dept);
    boolean update(Dept dept);
    boolean delete(int deptno);
}

class DeptDAOImpl implements DeptDAO {
    private Connection connect() throws Exception {
        String url = "jdbc:mysql://localhost/school_db?serverTimezone=Asia/Seoul";
        String user = "root";
        String pass = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    @Override
    public List<Dept> getAll() {
        List<Dept> list = new ArrayList<>();
        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM dept");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Dept d = new Dept(rs.getInt("deptno"), rs.getString("deptname"), rs.getString("loc"));
                list.add(d);
            }
        } catch (Exception e) {
            System.out.println("조회 실패: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean insert(Dept dept) {
        String sql = "INSERT INTO dept(deptno, deptname, loc) VALUES (?, ?, ?)";
        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dept.getDeptno());
            ps.setString(2, dept.getDeptname());
            ps.setString(3, dept.getLoc());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("추가 실패: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Dept dept) {
        String sql = "UPDATE dept SET loc = ? WHERE deptno = ?";
        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dept.getLoc());
            ps.setInt(2, dept.getDeptno());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("수정 실패: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int deptno) {
        String sql = "DELETE FROM dept WHERE deptno = ?";
        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, deptno);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("삭제 실패: " + e.getMessage());
            return false;
        }
    }
}

public class DeptApp {
    static Scanner sc = new Scanner(System.in);
    static DeptDAO dao = new DeptDAOImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- 학과 정보 관리 시스템 ---");
            System.out.println("1. 모든 학과 보기");
            System.out.println("2. 학과 추가");
            System.out.println("3. 학과 삭제");
            System.out.println("4. 호실 수정");
            System.out.println("0. 종료");
            System.out.print("선택 >> ");
            int menu = sc.nextInt(); sc.nextLine();

            switch (menu) {
                case 1: showAll(); break;
                case 2: insert(); break;
                case 3: delete(); break;
                case 4: update(); break;
                case 0: System.out.println("종료합니다."); return;
                default: System.out.println("잘못된 입력입니다.");
            }
        }
    }

    static void showAll() {
        List<Dept> list = dao.getAll();
        if (list.isEmpty()) {
            System.out.println("등록된 학과가 없습니다.");
        } else {
            list.forEach(System.out::println);
        }
    }

    static void insert() {
        System.out.print("학과 번호: ");
        int no = sc.nextInt(); sc.nextLine();
        System.out.print("학과명: ");
        String name = sc.nextLine();
        System.out.print("호실: ");
        String loc = sc.nextLine();
        Dept dept = new Dept(no, name, loc);
        if (dao.insert(dept)) System.out.println("학과 추가 성공");
        else System.out.println("학과 추가 실패");
    }

    static void delete() {
        System.out.print("삭제할 학과 번호: ");
        int no = sc.nextInt(); sc.nextLine();
        if (dao.delete(no)) System.out.println("삭제 성공");
        else System.out.println("삭제 실패");
    }

    static void update() {
        System.out.print("수정할 학과 번호: ");
        int no = sc.nextInt(); sc.nextLine();
        System.out.print("새 호실: ");
        String loc = sc.nextLine();
        Dept dept = new Dept(no, "", loc);
        if (dao.update(dept)) System.out.println("수정 성공");
        else System.out.println("수정 실패");
    }
}

```
