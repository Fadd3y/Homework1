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
        BUCKETS[bucketIndex].put(key, value);
        elementsCount++;
    }

    public V get(K key) {
        if (key == null) return null;

        int bucketIndex = calculateIndex(key);
        return BUCKETS[bucketIndex].get(key);
    }

    public void remove(K key) {
        if (key == null) return;

        int bucketIndex = calculateIndex(key);
        BUCKETS[bucketIndex].remove(key);
        elementsCount--;
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
