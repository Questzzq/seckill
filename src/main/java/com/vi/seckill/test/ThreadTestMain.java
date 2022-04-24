package com.vi.seckill.test;

/**
 * @author Eric Tseng
 * @description ThreadTestMain
 * @since 2022/4/24 21:41
 */
public class ThreadTestMain {
    public static void main(String[] args) {
        ThreadTestMain threadTestMain = new ThreadTestMain();
//        threadTestMain.testThreadLocal();
        threadTestMain.testInheritableThreadLocal();
    }

    void testThreadLocal() {
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set("not ok");
        new Thread(() -> {
            System.out.println(threadLocal.get());
        }).start();
    }

    void testInheritableThreadLocal() {
        InheritableThreadLocal<String> itl = new InheritableThreadLocal<>();
        itl.set("father");
        new Thread(() -> {
            System.out.println("subThread:" + itl.get());
            itl.set("son");
            System.out.println(itl.get());
        }).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("thread:" + itl.get());
    }
}
