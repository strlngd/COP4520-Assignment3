import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Program {

    public static void main(String[] args) {
        ConcurrentLinkedList presents = new ConcurrentLinkedList();
        ArrayList<Integer> unorderedList = GenerateUnorderedList(500_000);
        AtomicInteger listIndex = new AtomicInteger(0);
        AtomicInteger finishedCounter = new AtomicInteger(0);

        System.out.println("Starting servant threads...");
        for(int i = 1; i <= 4; i++){
            ServantThread st = new ServantThread(i, presents, unorderedList, listIndex, finishedCounter);
            Thread t = new Thread(st);
            t.start();
        }
    }

    public static ArrayList<Integer> GenerateUnorderedList(int count){
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();

        System.out.println("Generating Unordered List...");
        for(int i = 0; i < count; i++){
            list.add(i);
        }

        System.out.println("Shuffling Unordered List...");
        for(int i = 0; i < count; i++){
            int swapIndex = rand.nextInt(count);
            int temp = list.get(i);
            list.set(i, list.get(swapIndex));
            list.set(swapIndex, temp);
        }
        return list;
    }
}
