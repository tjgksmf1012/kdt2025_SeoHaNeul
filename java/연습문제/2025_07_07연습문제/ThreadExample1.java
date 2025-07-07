package Exam2025_07_07;

public class ThreadExample1 {
    public static void main(String[] args) {
        Thread t1 = new MovieThread1();
        t1.start();;

        Thread t2 = new Thread(new MusicRunnable());
        t2.start();
    }
}

class MovieThread1 extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {
            System.out.println("동영상을 재생합니다.");
            try {Thread.sleep(1000);} catch (InterruptedException e) {}
        }
    }
}

class MusicRunnable implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {
            System.out.println("음악을 재생합니다.");
            try {Thread.sleep(1000);} catch (InterruptedException e) {}
        }
    }
}