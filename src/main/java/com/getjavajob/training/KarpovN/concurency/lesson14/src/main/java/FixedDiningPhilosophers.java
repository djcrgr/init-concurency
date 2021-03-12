public class FixedDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int size = 5;
        ThreadPool executorService = new ThreadPool(5);
        Fork[] forks = new Fork[size];
        for (int i = 0; i < size; i++) {
            forks[i] = new Fork();
        }
        for (int i = 0; i < size; i++) {
            if (i < (size - 1)) {
                executorService.execute(new Philosopher(
                        forks[i], forks[i + 1], i));
            } else {
                executorService.execute(new Philosopher(
                        forks[0], forks[i], i));
            }
        }
    }

}
