package datastructures.list;

import java.lang.reflect.Array;

public class ArrayList<T> implements List<T> {

    private final Class<T> type;
    private T[] data;

    public ArrayList(Class<T> type) {
        this.type = type;
        data = getArrayOfLength(0);
    }

    private T[] getArrayOfLength(int length) {
        return (T[]) Array.newInstance(type, length);
    }

    @Override
    public void add(T item) {
        copyOldAddNew(getArrayOfLength(data.length + 1), item);
    }

    private void copyOldAddNew(T[] newData, T item) {
        for (int i = 0; i < data.length; i++)
            newData[i] = data[i];
        newData[newData.length - 1] = item;
        data = newData;
    }

    @Override
    public T get(int index) {
        return data[index];
    }

    @Override
    public boolean remove(T item) {
        for (int i = 0; i < data.length; i++) {
            if (item.equals(data[i]))
                return moveDownDataFrom(i);
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        return moveDownDataFrom(index);
    }

    private boolean moveDownDataFrom(int index) {
        if (index >= data.length || index < 0)
            return false;

        for (int i = index; i < data.length - 1; i++)
            data[i] = data[i+1];

        T[] newData = getArrayOfLength(data.length - 1);

        for (int i = 0; i < data.length - 1; i++)
            newData[i] = data[i];

        data = newData;

        return true;
    }

    @Override
    public boolean contains(T item) {
        boolean contains = false;
        for (T datum : data) {
            if (datum.equals(item)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public boolean isEmpty() {
        return data.length == 0;
    }

    @Override
    public int size() {
        return data.length;
    }
}
