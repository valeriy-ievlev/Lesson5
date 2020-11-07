import java.util.Arrays;

public class Lesson5 {
    public static void main(String[] args) {
        final int size = 10000000;
        final int h = size / 2;
        method1(size);
        method2(size, h);
    }

    public static void method1(int size) {
        long a = System.currentTimeMillis();
        float[] arr = new float[size];
        for (int j = 0; j < size; j++){
            arr[j] = 1;
        }
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void method2(int size, int h) {
        long a = System.currentTimeMillis();
        float[] arr = new float[size];
        for (int j = 0; j < size; j++){
            arr[j] = 1;
        }
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread first = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        first.start();
        second.start();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);
    }
}
