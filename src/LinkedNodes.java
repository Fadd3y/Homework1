public class LinkedNodes<K, V>{
    private final Node<K, V> DUMMY_HEAD;
    private Node<K, V> tail;
    private int elementsCount;

    public LinkedNodes() {
        DUMMY_HEAD = new Node<>();
        elementsCount = 0;
    }

    public void put(K key, V value) {
        Node<K, V> node;

        if (elementsCount == 0) {
            node = new Node<>(key, value);
            DUMMY_HEAD.setNext(node);
            tail = node;
            elementsCount++;
            return;
        }

        node = findNodeByKey(key);
        if (node != null) {
            node.setValue(value);
        } else {
            node = new Node<>(key, value);
            tail.setNext(node);
            tail = node;
            elementsCount++;
        }
    }

    public V get(K key) {
        Node<K, V> node = findNodeByKey(key);
        return node == null ? null : node.getValue();
    }

    public void remove(K key) {
        if (elementsCount == 0) return;

        Node<K, V> prevNode = DUMMY_HEAD;
        Node<K, V> node = DUMMY_HEAD.getNext();
        while(node != null) {
            if (node.getKey().equals(key)) {
                prevNode.setNext(node.getNext());
                elementsCount--;
                return;
            } else {
                prevNode = node;
                node = node.getNext();
            }
        }
    }

    private Node<K, V> findNodeByKey(K key) {
        int index = 0;
        Node<K, V> node = DUMMY_HEAD.getNext();
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node;
            }
            node = node.getNext();
            index++;
        }
        return null;
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
        private K key;
        private V value;
        private Node next;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
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

        @Override
        public String toString() {
            return key.toString() + " - " + value.toString();
        }
    }
}
