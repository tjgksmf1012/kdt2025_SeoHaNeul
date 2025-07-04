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
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i) < 0) v.set(i, 0);  // i번째 원소가 음수면 0으로
        }
    }

    public void showAll() {
        for (int i = 0; i < v.size(); i++) {
            System.out.print(v.get(i) + " ");
        }
        System.out.println();
    }

    public int add() {
        int sum = 0;
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i) > 0) sum += v.get(i); // 양수만 더함
        }
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
