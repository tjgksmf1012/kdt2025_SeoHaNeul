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

