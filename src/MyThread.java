class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("создаю поток с номером - " + Thread.currentThread().getName() ); //
        try {
            while(!isInterrupted()) { //вернет true, если выполнение потока было остановлено
                Thread.sleep(2500); // усыпляем на 2,5 секунды
                System.out.println("Я " + Thread.currentThread().getName() + ". Всем привет!");
                // Thread.getCurrentThread.getName() - для получения имени теущего потока
            }
        } catch (InterruptedException ignored) {

        } finally{
            System.out.printf("%s завершен\n", getName());
        }
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }
}



