연습문제 1번
```
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
```

연습문제 2번
```
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
```

연습문제 3번
```
package ch09.p460_p462;

class CheckBox {
    OnSelectListener listener;

    void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    void select() {
        listener.onSelect();
    }

    static interface OnSelectListener {
        void onSelect();
    }
}

public class Exam3{
    public static void main(String[] args) {
        CheckBox checkBox = new CheckBox();
        checkBox.setOnSelectListener(
            new CheckBox.OnSelectListener() {
                @Override
                public void onSelect() {
                    System.out.println("배경을 변경합니다.");
                }
            }
        );
        checkBox.select();
    }
}
```
