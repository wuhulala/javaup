package com.wuhulala.javase.concurrent.lock;

/**
 * 功能说明: 测试不可重入锁<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/6/16<br>
 */
public class SimpleLockTest {

    private Lock lock = new Lock();

    public static void main(String[] args) throws InterruptedException {
        SimpleLockTest lock = new SimpleLockTest();
        lock.print(0);
    }
    ///////////////////////////// 方法区 ////////////////////////////////////

    public void print(int count) throws InterruptedException {
        System.out.println(count);
        if(count > 2){
            return;
        }
        lock.lock();
        print(++ count);
        lock.unlock();
    }

    class Lock{
        private boolean isLocked = false;
        public synchronized void lock()
                throws InterruptedException{
            while(isLocked){
                wait();
            }
            isLocked = true;
        }

        public synchronized void unlock(){
            isLocked = false;
            notify();
        }
    }
}
