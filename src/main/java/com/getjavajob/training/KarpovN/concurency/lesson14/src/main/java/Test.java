public class Test {
    /*public static void main(String[] args) {
        TaskWorker taskWorker = new TaskWorker();
        ThreadPool threadPool = new ThreadPool(2, taskWorker);
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(taskWorker));
        }
        for (Thread thread : list) {
            threadPool.execute(thread);
        }
    }

    private static class TaskWorker implements Runnable {

        @Override
        public void run() {
            System.out.println("running " + Thread.currentThread().getName());
        }
    }*/
}
