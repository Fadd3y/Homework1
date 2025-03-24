public class LinkedNodes<K, V> {
    private Node<K, V> head;
    private int elementsCount;

    public LinkedNodes() {
        elementsCount = 0;
    }

    public V put(K key, V value) {
        if (key == null) throw new NullPointerException();

        Node<K, V> node = head;
        int hash = key.hashCode();

        if (head == null) {
            head = new Node<>(key, value);
            elementsCount++;
            return null;
        }

        while(node != null) {
            if (hash == node.getHash() && node.getKey().equals(key)) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
            node = node.getNext();
        }

        insertFirst(new Node<>(key, value));
        elementsCount++;
        return null;
    }

    public V get(K key) {
        if (key == null) throw new NullPointerException();

        Node<K, V> node = head;
        int hash = key.hashCode();

        while (node != null) {
            if (hash == node.getHash() && node.getKey().equals(key)) {
                return node.getValue();
            } else {
                node = node.getNext();
            }
        }
        return null;
    }

    public boolean remove(K key) {
        if (key == null) throw new NullPointerException();

        if (elementsCount == 0) return false;

        Node<K, V> prevNode = head;
        Node<K, V> node = head;
        int hash = key.hashCode();

        while (node != null) {
            if (hash == node.getHash() && node.getKey().equals(key)) {
                if (node == head) {
                    head = node.getNext();
                    elementsCount--;
                    return true;
                }

                prevNode.setNext(node.getNext());
                elementsCount--;
                return true;
            } else {
                prevNode = node;
                node = node.getNext();
            }
        }
        return false;
    }

    public CustomArrayList<V> values() {
        CustomArrayList<V> values = new CustomArrayList<>();
        Node<K, V> node = head;
        while (node != null) {
            values.add(node.getValue());
            node = node.getNext();
        }
        return values;
    }

    public CustomArrayList<K> keys() {
        CustomArrayList<K> keys = new CustomArrayList<>();
        Node<K, V> node = head;
        while (node != null) {
            keys.add(node.getKey());
            node = node.getNext();
        }
        return keys;
    }

    private void insertFirst(Node<K, V> node) {
        var temp = head;
        head = node;
        head.setNext(temp);
    }

    @Override
    public String toString() {
        int index = 0;
        StringBuilder builder = new StringBuilder();
        Node<K, V> node = head;
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
            return key.toString() + " - " + (value == null ? "null" : value.toString());
        }
    }
}
