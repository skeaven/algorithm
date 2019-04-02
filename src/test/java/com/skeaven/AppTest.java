package com.skeaven;

import org.junit.Test;

import java.util.Random;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void shouldAnswerWithTrue2() {
        System.out.println("增加最小值：" + test1(300, 500, 50, 10000));
        System.out.println("增加最大值：" + test2(300, 500, 50, 10000));

        test3(1L);
        test3(1);

    }

    private static long test1(int min, int max, int var, int times) {
        long total = 0;
        long res = 0;
        Random random = new Random(System.currentTimeMillis());
        int update = min + var;
        //方案1
        for (int i = 0; i < times; i++) {
            int attack = update + random.nextInt(max - update);
            if (random.nextDouble() < 0.2) {
                total += attack * 2 * 1.45;
            } else {
                total += attack;
            }

            if (random.nextDouble() < 0.4) {
                res += attack * 0.4;
            }

        }
        System.out.println(res);
        return total;
    }

    private static long test2(int min, int max, int var, int times) {
        long total = 0;
        long res = 0;
        Random random = new Random(System.currentTimeMillis());
        int update = max + var;
        //方案1
        for (int i = 0; i < times; i++) {
            int attack = min + random.nextInt(update - min);
            if (random.nextDouble() < 0.2) {
                total += attack * 2 * 1.45;
            } else {
                total += attack;
            }
            if (random.nextDouble() < 0.4) {
                res += attack * 0.4;
            }
        }
        System.out.println(res);
        return total;
    }

    public static <T> void test3(T t) {
        System.out.println(t.getClass().getName());
    }


}
