import java.util.HashMap;
import java.util.Map;

public class Cache {
    private int limit;

    public Node head;

    public Node tail;

    private Map<Integer, Node> cache;

    public Cache() {
        this.limit = 10;//default
        cache = new HashMap<>();
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    public String getValue(int key) {
        String value = "";
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            rearrange(node);
            value = node.getValue();
        }
        else {

            value = "";//get value from database
            insert(key, value);
        }
        return value;
    }

    private void insert(int key, String value) {
        if(cache.size() == limit) {
            delete(head.getKey());
        }

        Node node = new Node();
        node.setKey(key);
        node.setValue(value);
        if(head == null)  {
            head = node;
            tail = node;
        }
        tail.next = node;
        node.previous = tail;
        tail = node;
    }

    private void delete(int key) {
        Node toBeDeleted = cache.get(key);
        head = toBeDeleted.next;
        cache.remove(key);
    }

    //previous to node.next
    // tail to node
    private void rearrange(Node node) {

        Node previous = node.previous;
        Node next = node.next;
        previous.next = node.next;
        next.previous = previous;

        tail.next = node;
        node.previous = tail;
        tail = node;
    }



}
