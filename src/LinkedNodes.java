public class LinkedNodes<K, V> {
    private final Node<K, V> DUMMY_HEAD;
    private Node<K, V> tail;
    private int elementsCount;

    public LinkedNodes() {
        DUMMY_HEAD = new Node<>();
        elementsCount = 0;
    }

    public V put(K key, V value) {
        Node<K, V> node;

        if (elementsCount == 0) {
            node = new Node<>(key, value);
            DUMMY_HEAD.setNext(node);
            tail = node;
            elementsCount++;
            return null;
        }

        node = findNodeByKey(key);
        if (node != null) {
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        } else {
            node = new Node<>(key, value);
            tail.setNext(node);
            tail = node;
            elementsCount++;
            return null;
        }
    }

    public V get(K key) {
        Node<K, V> node = findNodeByKey(key);
        return node == null ? null : node.getValue();
    }

    public boolean remove(K key) {
        if (elementsCount == 0) return false;

        Node<K, V> prevNode = DUMMY_HEAD;
        Node<K, V> node = DUMMY_HEAD.getNext();
        int hash = key.hashCode();

        while (node != null) {
            if (hash == node.getHash() && node.getKey().equals(key)) {
                prevNode.setNext(node.getNext());
                elementsCount--;

                if (node == tail) {
                    tail = prevNode == DUMMY_HEAD ? null : node;
                }
                return true;
            } else {
                prevNode = node;
                node = node.getNext();
            }
        }
        return false;
    }

    private Node<K, V> findNodeByKey(K key) {
        int hash = key.hashCode();
        Node<K, V> node = DUMMY_HEAD.getNext();
        while (node != null) {
            if (hash == node.getHash() && node.getKey().equals(key)) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public CustomArrayList<V> values() {
        CustomArrayList<V> values = new CustomArrayList<>();
        Node<K, V> node = DUMMY_HEAD.getNext();
        while (node != null) {
            values.add(node.getValue());
            node = node.getNext();
        }
        return values;
    }

    public CustomArrayList<K> keys() {
        CustomArrayList<K> values = new CustomArrayList<>();
        Node<K, V> node = DUMMY_HEAD.getNext();
        while (node != null) {
            values.add(node.getKey());
            node = node.getNext();
        }
        return values;
    }

    @Override
    public String toString() {
        int index = 0;
        StringBuilder builder = new StringBuilder();
        Node<K, V> node = DUMMY_HEAD.getNext();
        while (node != null) {
            builder.append(index)
                    .append(": ")
                    .append(node)
                    .append("\n");
            node = node.getNext();
            index++;
        }
        return builder.toString();
    }

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;
        private final int hash;

        public Node() {
            this.key = null;
            this.hash = 0;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = key.hashCode();
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        @Override
        public String toString() {
            return key.toString() + " - " + value.toString();
        }
    }
}
