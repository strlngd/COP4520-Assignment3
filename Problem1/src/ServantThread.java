import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ServantThread implements Runnable {

    int id;
    ConcurrentLinkedList presents;
    ArrayList<Integer> unorderedList;
    AtomicInteger listIndex;
    AtomicInteger finishedCounter;

    Random rand = new Random();

    public ServantThread(int id, ConcurrentLinkedList presents, ArrayList<Integer> unorderedList, AtomicInteger listIndex, AtomicInteger finishedCounter){
        this.id = id;
        this.presents = presents;
        this.unorderedList = unorderedList;
        this.listIndex = listIndex;
        this.finishedCounter = finishedCounter;
    }

    @Override
    public void run() {
        while(finishedCounter.get() < unorderedList.size()){

            ServantTask task = ServantTask.values()[rand.nextInt(ServantTask.values().length)];
            switch(task){
                case ADD_PRESENT:
                    addPresent();
                    break;
                case WRITE_THANK_YOU:
                    writeThankYou();
                    break;
                case CHECK_PRESENT:
                    checkPresent();
                    break;
                default:
                    break;
            }
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("Servant " + id + " finished with all work.");
    }

    private void addPresent(){
        int index = listIndex.getAndIncrement();
        if(index >= unorderedList.size()){
            return;
        }
        presents.add(unorderedList.get(index));
//        if(presents.add(unorderedList.get(index)))
//            System.out.println("Servant " + this.id + " added present " + index + " to the chain.");
    }

    private void writeThankYou(){
        Node node = presents.pop();
        if(node == null) return;
        finishedCounter.incrementAndGet();
//        System.out.println("Servant " + this.id + " wrote thank you letter to guest " + node.key + ".");
    }

    private void checkPresent(){
        int checkNumber = rand.nextInt(unorderedList.size());
        String result = presents.contains(checkNumber) ? "found" : "did not find";
//        System.out.println("Servant " + this.id + " looked for guest " + checkNumber + "'s present and " + result + " it.");
    }

    public enum ServantTask {
        ADD_PRESENT,
        WRITE_THANK_YOU,
        CHECK_PRESENT
    }
}
