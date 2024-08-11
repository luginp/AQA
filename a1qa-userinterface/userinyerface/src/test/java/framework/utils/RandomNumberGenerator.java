package framework.utils;

import java.util.*;

public class RandomNumberGenerator {
    private static Random random = new Random();

    public static int createRandomNumber(int length) {
        return random.nextInt(length);
    }

    public static Integer[] randomNumbers(int randomRange, int max) {
        Integer[] arr = new Integer[randomRange];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(max);
        }
        Collections.shuffle(Arrays.asList(arr));
        return arr;
    }
}
