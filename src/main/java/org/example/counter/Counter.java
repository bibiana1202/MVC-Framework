package org.example.counter;

// 쓰레드
public class Counter implements Runnable{
    private int count =0 ; // 상태를 가지도록 해봄
    public void increment(){   
        count++;
    }
    public void decrement(){
        count--;
    }
    public int getValue(){
        return count;
    }

    @Override
    public void run() {
        // 간단하게 동기화하는 방법으로 해결 가능
        synchronized (this){
            this.increment();
            System.out.println("Value for Thread After Increment " + Thread.currentThread().getName()+" "+ this.getValue());
            this.decrement();
            System.out.println("Value for Thread at last " + Thread.currentThread().getName()+" "+ this.getValue());    
        }
        
    }
}
