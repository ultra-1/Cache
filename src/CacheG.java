import java.util.HashMap;
import java.util.Map;

public class CacheG<T> {

    private int limit;

    public NodeG<T> head;

    public NodeG<T> tail;

    private Map<Integer, NodeG> cache;

    public CacheG() {
        this.limit = 10;//default
        cache = new HashMap<>();
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    public T getValue(int key) {
        T value = null;
        if(cache.containsKey(key)) {
            NodeG node = cache.get(key);
            rearrange(node);
            value = (T) node.getValue();
        }
        else {

            value = null;//get value from database
            insert(key, value);
        }
        return value;
    }




    private void insert(int key, T value) {
        if(cache.size() == limit) {
            delete(head.getKey());
        }

        NodeG<T> node = new NodeG<>();
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
        NodeG toBeDeleted = cache.get(key);
        head = toBeDeleted.next;
        cache.remove(key);
    }

    //previous to node.next
    // tail to node
    private <T> void  rearrange(NodeG node) {
        NodeG previous = node.previous;
        NodeG next = node.next;
        previous.next = node.next;
        next.previous = previous;

        tail.next = node;
        node.previous = tail;
        tail = node;
    }
}
