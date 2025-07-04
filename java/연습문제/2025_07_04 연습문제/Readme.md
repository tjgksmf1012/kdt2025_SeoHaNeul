1번 연습문제
```
package Exam2025_07_04;

import java.util.Scanner;
import java.util.Vector;

public class Exam1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Vector<Integer> v = new Vector<Integer>();

        System.out.println("정수입력(-1이면 입력 끝) >> ");
        while (true){
            int n = s.nextInt();
            if(n==-1) break;
            else v.add(n);
        }

        if (v.size() == 0) {
            System.out.println("입력된 수가 없습니다.");
        } else {
            int min = v.get(0);
            for (int i = 1; i < v.size(); i++) {
                if (v.get(i) < min) min = v.get(i);
            }
            System.out.println("제일 작은수 " + min);
        }
    }
}
```

2번 연습문제
```
package Exam2025_07_04;

import java.util.*;

public class Exam2 {
    private Vector<Integer> v = new Vector<Integer>();

    public void read() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("정수 입력(0이면 입력 끝) >> ");
        while (true) {
            int num = scanner.nextInt();
            if (num == 0) break;
            v.add(num);
        }
    }

    public void changeToZero() {
        for (int i = 0; i < v.size(); i++) {if (v.get(i) < 0) v.set(i, 0);}
    }

    public void showAll() {
        for (int i = 0; i < v.size(); i++) {System.out.print(v.get(i) + " ");}
        System.out.println();
    }

    public int add() {
        int sum = 0;
        for (int i = 0; i < v.size(); i++) {if (v.get(i) > 0) sum += v.get(i);}
        return sum;
    }

    public static void main(String[] args) {
        Exam2 sp = new Exam2();
        sp.read();
        sp.changeToZero();
        System.out.print("음수를 0으로 바꾸면 ");
        sp.showAll();
        System.out.println("양수들의 합은 " + sp.add());
    }
}
```
