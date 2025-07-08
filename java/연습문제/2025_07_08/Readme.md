Comparable 방식
```
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
```
Comparator 방식
```
package Exam2025_07_08;

import java.util.*;

class Student {
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

    public int getTotal() {
        return korean + math + english;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d ", hakbun, name, getTotal());
    }
}

public class StudentComparator {
    public static void main(String[] args) {
        Student[] students = {
            new Student(100, "홍길동", 90, 77, 88),
            new Student(101, "이순신", 88, 94, 90),
            new Student(102, "타이거", 78, 88, 99),
            new Student(103, "라이온", 85, 90, 100)
        };

        Comparator<Student> totalAsc = new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return a.getTotal() - b.getTotal();
            }
        };

        Comparator<Student> totalDesc = new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return b.getTotal() - a.getTotal();
            }
        };

        Arrays.sort(students, totalAsc);
        for(Student r : students){
            System.out.println(r);
        }

        System.out.println();

        Arrays.sort(students, totalDesc);
        for(Student r : students){
            System.out.println(r);
        }
    }
}
```
SeoulPeople
```
package Exam2025_07_08;

import java.io.*;
import java.util.*;

public class SeoulPeopleExam {
    ArrayList<People> peoples = new ArrayList<>();

    public SeoulPeopleExam() throws Exception {
BufferedReader br = new BufferedReader(
    new InputStreamReader(
        new FileInputStream("C:\\Users\\cyci\\Documents\\seoul_people.csv"), "UTF-8")
);
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] t = line.split(",");
            String atdrc_nm = t[0].trim();
            int tot_popltn_co = Integer.parseInt(t[1].trim());
            int tot_hshld_co = Integer.parseInt(t[2].trim());
            int male_popltn_co = Integer.parseInt(t[3].trim());
            int female_popltn_co = Integer.parseInt(t[4].trim());
            double hshld_popltn_avrg_co = Double.parseDouble(t[5].trim());
            String atdrc_code_se = t[6].trim();
            peoples.add(new People(atdrc_nm, tot_popltn_co, tot_hshld_co, male_popltn_co, female_popltn_co, hshld_popltn_avrg_co, atdrc_code_se));
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        SeoulPeopleExam people = new SeoulPeopleExam();

        People maxMale = people.peoples.stream().max(Comparator.comparingInt(p -> p.male_popltn_co)).get();
        People minMale = people.peoples.stream().min(Comparator.comparingInt(p -> p.male_popltn_co)).get();
        People maxFemale = people.peoples.stream().max(Comparator.comparingInt(p -> p.female_popltn_co)).get();
        People minFemale = people.peoples.stream().min(Comparator.comparingInt(p -> p.female_popltn_co)).get();
        People maxHousehold = people.peoples.stream().max(Comparator.comparingDouble(p -> p.hshld_popltn_avrg_co)).get();
        People minHousehold = people.peoples.stream().min(Comparator.comparingDouble(p -> p.hshld_popltn_avrg_co)).get();
        double avgPeople = people.peoples.stream().mapToInt(p -> p.tot_popltn_co).average().orElse(0);

        System.out.println("남자 인구 가장 많은 구: " + maxMale.atdrc_nm + " (" + maxMale.male_popltn_co + ")");
        System.out.println("여자 인구 가장 많은 구: " + maxFemale.atdrc_nm + " (" + maxFemale.female_popltn_co + ")");
        System.out.println("남자 인구 가장 적은 구: " + minMale.atdrc_nm + " (" + minMale.male_popltn_co + ")");
        System.out.println("여자 인구 가장 적은 구: " + minFemale.atdrc_nm + " (" + minFemale.female_popltn_co + ")");
        System.out.println("세대당 인구 가장 많은 구: " + maxHousehold.atdrc_nm + " (" + maxHousehold.hshld_popltn_avrg_co + ")");
        System.out.println("세대당 인구 가장 적은 구: " + minHousehold.atdrc_nm + " (" + minHousehold.hshld_popltn_avrg_co + ")");
        System.out.printf("평균 구 인구수: %.2f명\n", avgPeople);
    }
}

class People {
    String atdrc_nm;
    int tot_popltn_co, tot_hshld_co, male_popltn_co, female_popltn_co;
    double hshld_popltn_avrg_co;
    String atdrc_code_se;

    People(String atdrc_nm, int tot_popltn_co, int tot_hshld_co, int male_popltn_co, int female_popltn_co, double hshld_popltn_avrg_co, String atdrc_code_se) {
        this.atdrc_nm = atdrc_nm;
        this.tot_popltn_co = tot_popltn_co;
        this.tot_hshld_co = tot_hshld_co;
        this.male_popltn_co = male_popltn_co;
        this.female_popltn_co = female_popltn_co;
        this.hshld_popltn_avrg_co = hshld_popltn_avrg_co;
        this.atdrc_code_se = atdrc_code_se;
    }
}
```
