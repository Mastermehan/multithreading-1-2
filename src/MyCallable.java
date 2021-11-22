import java.util.Random;
import java.util.concurrent.Callable;

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call()  {
        int count = 0;
        for (int i = 0; i < new Random().nextInt(100); i++) {
            count = i;
            System.out.println("Я поток " + Thread.currentThread().getName() + ". Вывожу № " + i);
        }
        return count + 1;
    }
}
