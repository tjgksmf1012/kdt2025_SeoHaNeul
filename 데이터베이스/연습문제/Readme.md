```
package javadb.sec02;

import java.sql.*;
import java.util.*;

// DTO 클래스
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

    public void setDeptno(int deptno) { this.deptno = deptno; }
    public void setDeptname(String deptname) { this.deptname = deptname; }
    public void setLoc(String loc) { this.loc = loc; }

    @Override
    public String toString() {
        return String.format("학과번호: %d, 학과명: %s, 호실: %s", deptno, deptname, loc);
    }
}

// DAO 클래스
class DeptDAO {
    private Connection connect() throws Exception {
        String url = "jdbc:mysql://localhost/school_db?serverTimezone=Asia/Seoul";
        String user = "root";
        String pass = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    public List<Dept> getAll() {
        List<Dept> list = new ArrayList<>();
        String sql = "SELECT * FROM dept";
        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Dept(rs.getInt("deptno"), rs.getString("deptname"), rs.getString("loc")));
            }
        } catch (Exception e) {
            System.out.println("조회 실패: " + e.getMessage());
        }
        return list;
    }

    public boolean insert(Dept dept) {
        String sql = "INSERT INTO dept (deptno, deptname, loc) VALUES (?, ?, ?)";
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

// Main 클래스
public class DeptDemoDAO {
    static Scanner sc = new Scanner(System.in);
    static DeptDAO dao = new DeptDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. 전체 조회 | 2. 추가 | 3. 삭제 | 4. 호실 수정 | 0. 종료");
            System.out.print("메뉴 선택: ");
            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1 -> list();
                case 2 -> insert();
                case 3 -> delete();
                case 4 -> update();
                case 0 -> {
                    System.out.println("종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }

    static void list() {
        List<Dept> list = dao.getAll();
        if (list.isEmpty()) {
            System.out.println("학과 정보가 없습니다.");
        } else {
            list.forEach(System.out::println);
        }
    }

    static void insert() {
        System.out.print("학과번호: "); int no = Integer.parseInt(sc.nextLine());
        System.out.print("학과명: "); String name = sc.nextLine();
        System.out.print("호실: "); String loc = sc.nextLine();
        if (dao.insert(new Dept(no, name, loc))) {
            System.out.println("학과가 추가되었습니다.");
        }
    }

    static void delete() {
        System.out.print("삭제할 학과번호: ");
        int no = Integer.parseInt(sc.nextLine());
        if (dao.delete(no)) {
            System.out.println("삭제 완료!");
        } else {
            System.out.println("해당 학과가 존재하지 않습니다.");
        }
    }

    static void update() {
        System.out.print("수정할 학과번호: ");
        int no = Integer.parseInt(sc.nextLine());
        System.out.print("새 호실: ");
        String loc = sc.nextLine();
        if (dao.update(new Dept(no, null, loc))) {
            System.out.println("호실 수정 완료!");
        }
    }
}
```
