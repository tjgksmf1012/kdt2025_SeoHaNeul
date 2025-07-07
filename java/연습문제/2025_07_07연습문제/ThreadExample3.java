package Exam2025_07_07;

public class ThreadExample3 {
    public static void main(String[] args) {
        Thread t = new MovieThread3();
        t.setDaemon(true);
        t.start();
        try{Thread.sleep(5000);}catch(InterruptedException e){}
    }
}

class MovieThread3 extends Thread{
    @Override
    public void run(){
        while(true){
            System.out.println("동영상을 재생합니다.");
            try{Thread.sleep(1000);} catch(InterruptedException e){}
        }
    }
}
