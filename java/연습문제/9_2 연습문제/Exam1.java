package ch09.p460_p462;

public class Exam1 {
    public static void main(String[] args) {
        Anonymous anonymous = new Anonymous();
        anonymous.field.start();
        anonymous.method1();
        anonymous.method2(
            new Worker(){
                @Override
                public void start(){
                    System.out.println("테스트를 합니다.");
                }
            }
        );
    }
}

class Worker{
    public void start(){
        System.out.println("쉬고 있습니다.");
    }
}

class Anonymous{
    Worker field = new Worker(){
        @Override
        public void start(){
            System.out.println("디자인을 합니다.");
        }
    };
    void method1(){
        Worker localVar = new Worker(){
            @Override
            public void start(){
                System.out.println("개발을 합니다");
            }
        };
        localVar.start();
    }
    void method2(Worker worker){
        worker.start();
    }
}