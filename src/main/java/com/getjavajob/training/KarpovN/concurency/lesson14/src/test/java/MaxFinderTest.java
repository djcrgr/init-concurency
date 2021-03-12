import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.assertEquals;

public class MaxFinderTest {

    @Test
    public void getMax() {
        long[] arr = {3, 4, 5, 1123, 567, 78, 90, 5, 35434, 1, 34, 56, 111, 2, 56, 789, 87645, 343535};
        Long[] newArr = new Long[arr.length];
        int i = 0;
        for (long temp : arr) {
            newArr[i++] = temp;
        }
        MaxFinder maxFinder = new MaxFinder(0, arr.length, newArr);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(maxFinder);
        assertEquals(343535L, (long) maxFinder.getMax());
    }
}