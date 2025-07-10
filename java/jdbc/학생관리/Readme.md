```
package javadb;

import java.sql.*;
import java.util.Scanner;

public class StudentDB {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println("1. 모든 학생 정보 보기");
            System.out.println("2. 학생 추가하기");
            System.out.println("3. 학생 삭제하기");
            System.out.println("4. 학생 정보 수정하기...(전화번호 수정)");
            System.out.println("5. 전화번호로 학생정보 조회");
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
                case 5: searchPhone(); break;
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
             ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {
            System.out.println("[모든 학생 정보]");
            while (rs.next()) {
                System.out.printf("%d, %s, %s, %s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getNString(4));
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    static void addDept() {
        try (Connection con = connect()) {
            System.out.print("학생 번호: "); int stdno = sc.nextInt(); sc.nextLine();
            System.out.print("학생 이름: "); String stdname = sc.nextLine();
            System.out.print("휴대폰: "); String phone = sc.nextLine();
            System.out.print("이메일: "); String email = sc.nextLine();

            String sql = "INSERT INTO student VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, stdno);
                pstmt.setString(2, stdname);
                pstmt.setString(3, phone);
                pstmt.setString(4, email);
                pstmt.executeUpdate();
                System.out.println("학생이 추가되었습니다.");
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    static void deleteDept() {
        try (Connection con = connect()) {
            System.out.print("삭제할 학생 번호: ");
            int stdno = sc.nextInt(); sc.nextLine();
            String sql = "DELETE FROM student WHERE stdno = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, stdno);
                int cnt = pstmt.executeUpdate();
                System.out.println(cnt > 0 ? "삭제 완료!" : "해당 학생 없음.");
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    static void updateDeptLoc() {
        try (Connection con = connect()) {
            System.out.print("전화번호를 수정할 학생 번호: ");
            int stdno = sc.nextInt(); sc.nextLine();
            System.out.print("새 전화번호: ");
            String phone = sc.nextLine();

            String sql = "UPDATE student SET phone = ? WHERE stdno = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, phone);
                pstmt.setInt(2, stdno);
                int cnt = pstmt.executeUpdate();
                System.out.println(cnt > 0 ? "전화번호 수정 완료!" : "해당 학생 없음.");
            }
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }
    
    static void searchPhone() {
        try (Connection con = connect()) {
            System.out.print("조회할 전화번호 입력: ");
            String phone = sc.nextLine();

            String sql = "SELECT * FROM student WHERE phone = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, phone);
                try (ResultSet rs = pstmt.executeQuery()) {
                    boolean found = false;
                    while (rs.next()) {
                        int stdno = rs.getInt("stdno");
                        String stdname = rs.getString("stdname");
                        String email = rs.getString("email");
                        System.out.printf("학생번호: %d, 이름: %s, 전화번호: %s, 이메일: %s\n",
                                          stdno, stdname, phone, email);
                        found = true;
                    }
                    if (!found) System.out.println("해당 전화번호의 학생이 존재하지 않습니다.");
                }
            }
        } catch (Exception e) {
            System.out.println("오류: " + e.getMessage());
        }
    }

}
```
