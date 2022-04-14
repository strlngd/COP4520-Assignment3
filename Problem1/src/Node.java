import java.util.concurrent.locks.ReentrantLock;

public class Node {
    private final ReentrantLock _lock = new ReentrantLock();
    int key;
    Node next;

    public Node(int key){
        this.key = key;
    }

    public void lock(){
        _lock.lock();
    }

    public void unlock(){
        _lock.unlock();
    }
}
