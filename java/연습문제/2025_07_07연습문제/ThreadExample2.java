package Exam2025_07_07;

public class ThreadExample2 {
    public static void main(String[] args) {
        Thread t = new MovieThread2();
        t.start();

        try{Thread.sleep(1000);}catch(InterruptedException e){}
        t.interrupt();
    }
}

class MovieThread2 extends Thread{
    @Override
    public void run(){
        while(true){
            if(Thread.interrupted()) break;
        }
    }
}