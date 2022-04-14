import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) throws InterruptedException {

        SharedMemory memory = new SharedMemory();

        for(int i = 0; i < 8; i++){
            ArrayList<Integer> newData = new ArrayList<>();
            for(int j = 0; j < 60; j++){
                newData.add(0);
            }
            memory.data.add(newData);
            SensorThread st = new SensorThread(i, memory);
            Thread t = new Thread(st);
            t.start();
        }

        System.out.println("Report generates every 6 seconds to simulate 1 hour interval.\n");
        Thread.sleep(1000);
        int hours = 1;

        while(true){
            try {
                Thread.sleep(6000); // 100ms per minute in scaled time... 1 hour
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArrayList<ArrayList<Integer>> data = new ArrayList<>(memory.data); // copy data
            int[] highest = new int[5];
            Arrays.fill(highest, Integer.MIN_VALUE);
            int[] lowest = new int[5];
            Arrays.fill(lowest, Integer.MAX_VALUE);
            int intervalDiff = 0;

            for(int i = 0; i < 60; i++){
                for(ArrayList<Integer> row : data){
                    int val = row.get(i);
                    if(val > highest[0]) {
                        highest[0] = val;
                        Arrays.sort(highest);
                    }
                    if(val < lowest[4]) {
                        lowest[4] = val;
                        Arrays.sort(lowest);
                    }
                    if(i >= 9){
                        int diff = Math.abs(row.get(i) - row.get(i - 9));
                        if(diff > intervalDiff){
                            intervalDiff = diff;
                        }
                    }
                }
            }

            System.out.println("===== Report For Hour " + hours + " =====");
            System.out.println("Highest temperatures recorded: " + joinArray(highest));
            System.out.println("Lowest temperatures recorded: " + joinArray(lowest));
            System.out.println("Largest difference in 10 minute interval: " + intervalDiff);

            System.out.println();
            hours++;

        }

    }

    public static String joinArray(int[] numbers){
        return Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}
