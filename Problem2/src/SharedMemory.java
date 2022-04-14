import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedMemory {
    public ArrayList<ArrayList<Integer>> data;

    public SharedMemory() {
        this.data = new ArrayList<>();
    }
}
