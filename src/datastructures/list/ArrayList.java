package datastructures.list;

import java.lang.reflect.Array;
import java.util.function.Consumer;

public class ArrayList<T> {

    private T[] data;
    private final int startCapacity = 2;
    private final int increaseRate = 2;
    private int currIndex;
    private Class<T> type;

    public ArrayList(Class<T> type) {
        currIndex = 0;
        data = (T[]) Array.newInstance(type, startCapacity);
        this.type = type;
    }

    public void add(T item) {
        if (currIndex >= data.length) resizeArray();
        data[currIndex] = item;
        currIndex++;
    }

    public T get(int index) {
        if (index < currIndex) return data[index];
        else return null;
    }

    public void removeAt(int index) {
        if (index < currIndex) {
            for (int i = index; i < data.length - 1; i++) data[i] = data[i+1];
            data[data.length-1] = null;
            currIndex--;
        }
    }

    private void resizeArray() {
        T[] new_data = (T[]) Array.newInstance(type, data.length * increaseRate);
        for (int i = 0; i < data.length; i++) new_data[i] = data[i];
        data = new_data;
    }

    public int size() {
        return currIndex;
    }

    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < currIndex; i++) action.accept(data[i]);
    }

    // debugging purpose
    public int getArrayLength() {
        return data.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (int i = 0; i < currIndex; i++) builder.append(data[i]).append(", ");
        return builder.substring(0, builder.length() - 2) + "}";
    }
}

class Main {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(String.class);
        list.add("This");
        list.add("is");
        list.add("a");
        list.removeAt(1);
        list.add("test");
        list.add("arrayList");
        System.out.println(list);
        System.out.println(list.getArrayLength());
        System.out.println(list.size());
        list.forEach(System.out::println);
    }
}
