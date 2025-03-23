public class CustomArrayList <T> {

    private Object[] storage;
    private int elementsCount;

    public CustomArrayList() {
        this.storage = new Object[10];
    }

    public CustomArrayList(int initialSize) {
        if (initialSize > 0) {
            this.storage = new Object[initialSize];
        } else {
            this.storage = new Object[10];
        }
    }

    public void add(T object) {
        if (storage.length == elementsCount) enlargeStorage();

        storage[elementsCount] = object;
        elementsCount++;
    }

    public void add(int index, T object) {
        if (index > elementsCount || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (storage.length == elementsCount) enlargeStorage();

        moveRight(index);
        storage[index] = object;
        elementsCount++;
    }

    public void addAll(CustomArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndexInBounds(index);

        return (T) storage[index];
    }

    public void set(int index, T object) {
        checkIndexInBounds(index);

        storage[index] = object;
    }

    public void remove(int index) {
        checkIndexInBounds(index);

        moveLeft(index + 1);
        elementsCount--;
    }

    @SuppressWarnings("unchecked")
    public boolean contains(T object) {
        for (int i = 0; i < elementsCount; i++) {
            if (object == null && storage[i] == null) {
                return true;
            } else if (object != null && object.equals((T) storage[i])) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public int indexOf(T object) {
        for (int i = 0; i < elementsCount; i++) {
            if (object == null && storage[i] == null) {
                return i;
            } else if (object != null && object.equals((T) storage[i])) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return elementsCount;
    }

    private void enlargeStorage() {
        int newStorageLength = (int) (storage.length * 1.5);
        Object[] newStorage = new Object[newStorageLength];

        for (int i = 0; i < elementsCount; i++) {
            newStorage[i] = storage[i];
        }

        this.storage = newStorage;
    }

    private void moveRight(int from) {
        for (int i = elementsCount - 1; i >= from ; i--) {
            storage[i + 1] = storage[i];
        }
        storage[from] = null;
    }

    private void moveLeft(int from) {
        for (int i = from; i <= elementsCount - 1; i++) {
            storage[i - 1] = storage[i];
        }
        storage[elementsCount - 1] = null;
    }

    private void checkIndexInBounds(int index) {
        if (index > elementsCount - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < elementsCount; i++) {
            if (storage[i] != null) {
                stringBuilder
                        .append(storage[i].toString());
            } else {
                stringBuilder.append("null");
            }
            stringBuilder.append(", ");
        }
        if (this.size() > 0) {
            stringBuilder.delete(stringBuilder.lastIndexOf(", "), stringBuilder.length());
        }
        return stringBuilder.append("]").toString();
    }
}