```
package javadb;

import java.sql.*;
import java.util.Scanner;

public class MenuDemo {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println("1. 모든 학과 정보 보기");
            System.out.println("2. 학과 추가하기");
            System.out.println("3. 학과 삭제하기");
            System.out.println("4. 학과 정보 수정하기...(호실만 수정)");
            System.out.println("0. 종료하기...");
            System.out.println("------------------------------------------------------");
            System.out.print("메뉴 선택: ");
            int menu = sc.nextInt();
            sc.nextLine(); // 엔터 처리

            switch (menu) {
                case 1: showAll(); break;
                case 2: addDept(); break;
                case 3: deleteDept(); break;
                case 4: updateDeptLoc(); break;
                case 0: System.out.println("종료합니다."); return;
                default: System.out.println("잘못 선택했습니다!");
            }
        }
    }

    static Connection connect() throws Exception {
        String url = "jdbc:mysql://localhost/school_db?serverTimezone=Asia/Seoul";
        String user = "root";
        String pass = "ilike1012!";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    static void showAll() {
        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM dept")) {
            System.out.println("[모든 학과 정보]");
            while (rs.next()) {
                System.out.printf("%d, %s, %s\n", rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    static void addDept() {
        try (Connection con = connect()) {
            System.out.print("학과 번호: "); int deptno = sc.nextInt(); sc.nextLine();
            System.out.print("학과 이름: "); String deptname = sc.nextLine();
            System.out.print("호실: "); String loc = sc.nextLine();

            String sql = "INSERT INTO dept VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, deptno);
                pstmt.setString(2, deptname);
                pstmt.setString(3, loc);
                pstmt.executeUpdate();
                System.out.println("학과가 추가되었습니다.");
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    static void deleteDept() {
        try (Connection con = connect()) {
            System.out.print("삭제할 학과 번호: ");
            int deptno = sc.nextInt(); sc.nextLine();
            String sql = "DELETE FROM dept WHERE deptno = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, deptno);
                int cnt = pstmt.executeUpdate();
                System.out.println(cnt > 0 ? "삭제 완료!" : "해당 학과 없음.");
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    static void updateDeptLoc() {
        try (Connection con = connect()) {
            System.out.print("수정할 학과 번호: ");
            int deptno = sc.nextInt(); sc.nextLine();
            System.out.print("새 호실: ");
            String loc = sc.nextLine();

            String sql = "UPDATE dept SET loc = ? WHERE deptno = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, loc);
                pstmt.setInt(2, deptno);
                int cnt = pstmt.executeUpdate();
                System.out.println(cnt > 0 ? "호실 수정 완료!" : "해당 학과 없음.");
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
}
```
