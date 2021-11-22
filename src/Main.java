import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("Group"); // главная группа потоков
        Thread myThread1 = new MyThread(threadGroup, "Поток 1"); // добавляем потоки в группы
        Thread myThread2 = new MyThread(threadGroup, "Поток 2");
        Thread myThread3 = new MyThread(threadGroup, "Поток 3");
        Thread myThread4 = new MyThread(threadGroup, "Поток 4");

        myThread1.start(); //запуск потока
        myThread2.start();
        myThread3.start();
        myThread4.start();
        try {
            Thread.sleep(15000); //усыпляем поток на 15 секунд
        } catch (InterruptedException ignored) {
        }
        System.out.println("Завершаю все потоки");
        threadGroup.interrupt(); //остановка потока


        // Задача 2
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //создание пула потоков
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            System.out.println("""
                    Введите '1' чтобы запустить несколько потоков
                    Введите '2' чтобы запустить самый быстрый поток из нескольких
                    Введите '0' чтобы выйти.""");
            int input = scanner.nextInt();
            switch (input) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    Callable<Integer> myCallable1 = new MyCallable();
                    Callable<Integer> myCallable2 = new MyCallable();
                    Callable<Integer> myCallable3 = new MyCallable();
                    Callable<Integer> myCallable4 = new MyCallable();
                    List<Callable<Integer>> taskList = new ArrayList<>();
                    taskList.add(myCallable1);
                    taskList.add(myCallable2);
                    taskList.add(myCallable3);
                    taskList.add(myCallable4);
                    // запуск нескольких Callable-объектов
                    List<Future<Integer>> taskResults = executor.invokeAll(taskList);
                    for (Future<Integer> taskResult : taskResults
                    ) {
                        System.out.println("Количество итераций: " + taskResult.get());
                    }
                    //отмена всех запущеных задач
                    executor.shutdown();
                }
                case 2 -> {
                    Callable<Integer> myCallable1 = new MyCallable();
                    Callable<Integer> myCallable2 = new MyCallable();
                    Callable<Integer> myCallable3 = new MyCallable();
                    Callable<Integer> myCallable4 = new MyCallable();
                    List<Callable<Integer>> taskList = new ArrayList<>();
                    taskList.add(myCallable1);
                    taskList.add(myCallable2);
                    taskList.add(myCallable3);
                    taskList.add(myCallable4);

                    int taskResult = executor.invokeAny(taskList);
                    //отмена всех запущеных задач
                    executor.shutdown();
                    System.out.println(taskResult);
                }
            }
        }
    }
}
