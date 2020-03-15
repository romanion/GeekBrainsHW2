package hw5;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    static class MyThread extends Thread{
        public float[] arr;
        public final int start;
        public final int finish;

        MyThread(float[] arr, int start, int finish){
            this.arr = arr;
            this.start = start;
            this.finish = finish;
        }

        @Override
        public void run() {
            arr = estimate(arr, start, finish);
        }
    }

    public static void parallelEstimationMethod() throws InterruptedException {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        MyThread thread1 = new MyThread(a1, 0, h);
        MyThread thread2 = new MyThread(a2, 0, h);

        thread1.setName("First");
        thread2.setName("Second");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(thread1.arr, 0, arr, 0, h);
        System.arraycopy(thread2.arr, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);

    }

    public static void simpleEstimationMethod(){
        final int size = 10000000;
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        estimate(arr, 0, size);
        System.out.println(System.currentTimeMillis() - a);

    }

    public static float[] estimate(float[] arr, int start, int finish){
        if(!Thread.currentThread().getName().equalsIgnoreCase("Second")){
            for (int i = start; i < finish; i++){
                arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        } else {
            for (int i = start; i < finish; i++){
                arr[i] = (float)(arr[i]
                        * Math.sin(0.2f + (i + finish) / 5)
                        * Math.cos(0.2f + (i + finish) / 5)
                        * Math.cos(0.4f + (i + finish) / 2));
            }
        }

        return arr;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
    simpleEstimationMethod();
    parallelEstimationMethod();

    }
}
