public class NodeG<T> {

    private T value;

    private Integer key;

    public NodeG next;

    public NodeG previous;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
