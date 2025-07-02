package ch09.p460_p462;

public class Exam2 {
    public static void main(String[] args) {
        Anonymous anonymous = new Anonymous();
        anonymous.field.run();
        anonymous.method1();
        anonymous.method2(
            new Vehicle() {
                @Override
                public void run(){
                    System.out.println("트럭이 달립니다.");
                }
            }
        );
    }
}

interface Vehicle{
    public void run();
}

class Anonymous{
    Vehicle field = new Vehicle() {
        @Override
        public void run(){
            System.out.println("자전거가 달립니다.");
        }
    };
    void method1(){
        Vehicle localVar = new Vehicle() {
            @Override
            public void run(){
                System.out.println("승용차가 달립니다.");
            }
        };
        localVar.run();
    }
    void method2(Vehicle v){
        v.run();
    }
}