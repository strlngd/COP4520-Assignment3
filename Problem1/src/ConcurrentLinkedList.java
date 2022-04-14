public class ConcurrentLinkedList {
    private Node _head;

    public ConcurrentLinkedList(){
        _head = new Node(Integer.MAX_VALUE);
        _head.next = new Node(Integer.MAX_VALUE);
    }

    public boolean add(int item) {
        _head.lock();
        Node pred = _head;
        try {
            Node current = pred.next;
            current.lock();
            try{
                while(current.key < item) {
                    pred.unlock();
                    pred = current;
                    current = current.next;
                    current.lock();
                }
                if(current.key == item){
                    return false;
                }
                Node node = new Node(item);
                node.next = current;
                pred.next = node;
                return true;
            } finally {
                current.unlock();
            }
        } finally {
            pred.unlock();
        }
    }

    public boolean remove(int item) {
        Node pred = null, current = null;
        _head.lock();
        try {
            pred = _head;
            current = pred.next;
            current.lock();
            try {
                while(current.key < item){
                    pred.unlock();
                    pred = current;
                    current = current.next;
                    current.lock();
                }
                if(current.key == item){
                    pred.next = current.next;
                    return true;
                }
                return false;
            } finally {
                current.unlock();
            }
        } finally {
            pred.unlock();
        }
    }

    public boolean contains(int item){
        Node pred = null, current = null;
        _head.lock();
        try {
            pred = _head;
            current = pred.next;
            current.lock();
            try {
                while(current.key < item){
                    pred.unlock();
                    pred = current;
                    current = current.next;
                    current.lock();
                }
                return current.key == item;
            } finally {
                current.unlock();
            }
        } finally {
            pred.unlock();
        }
    }

    public Node pop(){
        Node next = null;
        _head.lock();
        try {
            next = _head.next;
            next.lock();

            if(next.key == Integer.MAX_VALUE) // if linked list is empty
                return null;

            _head.next = next.next;
            return next;

        } finally {
            next.unlock();
            _head.unlock();
        }
    }
}
