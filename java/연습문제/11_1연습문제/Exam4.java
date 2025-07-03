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