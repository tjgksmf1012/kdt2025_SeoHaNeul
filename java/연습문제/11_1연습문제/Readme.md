3번 연습문제
```
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
```

4번 연습문제
```
package Exam_11_1;

public class Exam4 {
    public static void main(String[] args) {
        Member member = new Member("blue", "이파란");
        System.out.println(member);
    }
}

class Member{
    private String id;
    private String name;

    public Member(String id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString(){
        return id + ": " + name;
    }
}
```

6번 연습문제
```
package Exam_11_1;

public class Exam6 {
    public static void main(String[] args) {
        byte[] bytes = {73,32,108,111,118,101,32,121,111,117};
        String str = new String(bytes);
        System.out.println(str);
    }
}
```

7번 연습문제
```
package Exam_11_1;

public class Exam7 {
    public static void main(String[] args) {
        String str = "모든 프로그램은 자바 언어로 개발될 수 있다.";
        int index = str.indexOf("자바");
        if(index == -1){System.out.println("자바 문자열이 포합되어 있지 않습니다.");}
        else {
            System.out.println("자바 문자열이 포함되어 있습니다.");
            str = str.replace("자바", "Java");
            System.out.println("-->" + str);
        }
    }
}
```
