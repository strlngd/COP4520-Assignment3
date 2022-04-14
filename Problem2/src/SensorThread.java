import java.util.Random;

public class SensorThread implements Runnable {
    private final int refreshInterval = 60;
    int id;
    SharedMemory memory;

    public SensorThread(int id, SharedMemory memory) {
        this.id = id;
        this.memory = memory;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int refreshCounter = 0;
        while(true){
            int temperature = rand.nextInt(171) - 100; // random number from -100 to 70

            memory.data.get(id).set(refreshCounter++, temperature);

            if(refreshCounter >= refreshInterval)
                refreshCounter = 0;

            try {
                Thread.sleep(100); // 100ms = 1 minute in scaled time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
