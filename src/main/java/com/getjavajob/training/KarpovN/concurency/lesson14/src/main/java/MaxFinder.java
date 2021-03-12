import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import static java.util.Arrays.asList;

public class MaxFinder extends RecursiveAction {
    private Long max = Long.MIN_VALUE;
    private int low;
    private int high;
    private Long[] array;

    public MaxFinder(int low, int high, Long[] array) {
        this.low = low;
        this.high = high;
        this.array = array;
    }

    public Long getMax() {
        return max;
    }

    @Override
    protected void compute() {
        if (low - high <= 2) {
            List<Long> longList = asList(array);
            max = Collections.max(longList) > max ? Collections.max(longList) : max;
        } else {
            int mid = (low + high) >>> 1;
            MaxFinder left = new MaxFinder(low, mid, array);
            MaxFinder right = new MaxFinder(mid, high, array);
        }
    }
}
