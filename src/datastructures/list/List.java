package datastructures.list;

public interface List<T> {

    void add(T item);
    T get(int index);

    /**
     * removes only the first item found
     * @param item item to remove
     * @return wether removed or not
     */
    boolean remove(T item);
    boolean removeAt(int index);

    /**
     * equals check
     * @param item item to check wether contained
     * @return contains item or not
     */
    boolean contains(T item);
    boolean isEmpty();
    int size();
}
