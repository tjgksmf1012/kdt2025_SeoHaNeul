package Exam2025_07_07;

import java.io.*;
import java.util.*;

class Student {
    String hakbun;
    String name;
    int korean, english, math;

    Student(String hakbun, String name, int korean, int english, int math) {
        this.hakbun = hakbun;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    double getAverage() {
        return (korean + english + math) / 3.0;
    }
}

public class StudentExample {
    public static void main(String[] args) throws Exception {
        List<Student> students = new ArrayList<>();

        String[] lines = {
            "100, 홍길동,90,77,88",
            "101, 이순신,88,94,90",
            "102, 타이거,78,88,99",
            "103, 라이온,85,90,100"
        };

        try (PrintWriter writer = new PrintWriter("C:\\Temp\\student.txt")) {
            for (String line : lines) {
                writer.println(line);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Temp\\student.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String hakbun = tokens[0].trim();
                String name = tokens[1].trim();
                int kor = Integer.parseInt(tokens[2].trim());
                int eng = Integer.parseInt(tokens[3].trim());
                int math = Integer.parseInt(tokens[4].trim());

                students.add(new Student(hakbun, name, kor, eng, math));
            }
        }

        double totalKor = 0;
        Student topStudent = null;
        double highestAvg = 0;

        for (Student student : students) {
            System.out.println(student.hakbun + " " + student.name);

            totalKor += student.korean;

            double avg = student.getAverage();
            if (avg > highestAvg) {
                highestAvg = avg;
                topStudent = student;
            }
        }

        double korAvg = totalKor / students.size();

        System.out.println();
        System.out.println("최고 평균을 받은 학생: " + topStudent.name + " (" + String.format("%.2f", topStudent.getAverage()) + ")");
        System.out.println("국어 점수 전체 평균: " + String.format("%.2f", korAvg));
    }
}
