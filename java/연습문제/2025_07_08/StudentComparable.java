package Exam2025_07_08;

import java.util.*;

class Student implements Comparable<Student>{
    int hakbun;
    String name;
    int korean, english, math;

    Student(int hakbun, String name, int korean, int english, int math) {
        this.hakbun = hakbun;
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    double getAverage() {
        return (korean + english + math) / 3.0;
    }
    
    public int getTotal(){
        return korean+math+english;
    }

    @Override
    public String toString(){
        return String.format("%s %s %d ", hakbun, name, getTotal());
    }

    @Override
    public int compareTo(Student o) {
        return getTotal()-o.getTotal();
    }
}

public class StudentComparable{
    public static void main(String[] args) throws Exception {
        Student[] students = {
            new Student(100, "홍길동", 90, 77, 88),
            new Student(101, "이순신", 88, 94, 90),
            new Student(102, "타이거", 78, 88, 99),
            new Student(103, "라이온", 85, 90, 100)
        };

        Arrays.sort(students);
        Arrays.sort(students, Collections.reverseOrder());

        for(Student r : students){
            System.out.println(r);
        }


    }
}
