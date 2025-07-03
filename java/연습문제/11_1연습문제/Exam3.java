package Exam_11_1;

import java.util.HashMap;

public class Exam3 {
    public static void main(String[] args) {
        HashMap<Student, String> hashMap = new HashMap<Student, String>();

        hashMap.put(new Student("1"), "95");

        String score = hashMap.get(new Student("1"));
        System.out.println("1번 학생의 총점 : " + score);
    }
}

class Student{
    private String studentNum;

    public Student(String studentNum){
        this.studentNum = studentNum;
    }
    public String getStudentNum(){return studentNum;}

    @Override
    public boolean equals(Object o){
        if(o instanceof Student){
            Student student = (Student) o;
            if(studentNum.equals(student.studentNum)){return true;}
        }
        return false;
    }

    @Override
    public int hashCode(){return studentNum.hashCode();}
}