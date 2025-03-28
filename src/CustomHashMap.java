public class CustomHashMap<K, V> {

    private final LinkedNodes<K, V>[] BUCKETS;
    private final int BUCKET_AMOUNT = 8;
    private int elementsCount;

    public CustomHashMap() {
        BUCKETS = new LinkedNodes[BUCKET_AMOUNT];
        elementsCount = 0;
        initBuckets();
    }

    public void put(K key, V value) {
        if (key == null) return;

        int bucketIndex = calculateIndex(key);

        if(BUCKETS[bucketIndex].put(key, value) == null) {
            elementsCount++;
        }
    }

    public V get(K key) {
        if (key == null) return null;

        int bucketIndex = calculateIndex(key);
        return BUCKETS[bucketIndex].get(key);
    }

    public void remove(K key) {
        if (key == null) return;

        int bucketIndex = calculateIndex(key);

        if (BUCKETS[bucketIndex].remove(key)) {
            elementsCount--;
        }
    }

    public CustomArrayList<V> values() {
        CustomArrayList<V> values = new CustomArrayList<>();
        for (int i = 0; i < BUCKET_AMOUNT; i++) {
            values.addAll(BUCKETS[i].values());
        }
        return values;
    }

    public CustomArrayList<K> keys() {
        CustomArrayList<K> values = new CustomArrayList<>();
        for (int i = 0; i < BUCKET_AMOUNT; i++) {
            values.addAll(BUCKETS[i].keys());
        }
        return values;
    }

    public int size() {
        return elementsCount;
    }

    private void initBuckets() {
        for (int i = 0; i < BUCKET_AMOUNT; i++) {
            BUCKETS[i] = new LinkedNodes<>();
        }
    }

    private int calculateIndex(K key) {
        return Math.abs(key.hashCode() % BUCKET_AMOUNT);
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (int i = 0; i < BUCKET_AMOUNT; i++) {
            builder.append("_____Bucket: ")
                    .append(i)
                    .append("_____\n")
                    .append(BUCKETS[i].toString());
        }
        return builder.toString();
    }
}
