package datastructures;

import java.util.LinkedList;

public class HashTable<K, V> {

    private LinkedList<Item<K, V>>[] data;

    private static class Item<K, V> {
        private final K key;
        private V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTable() {
        data = new LinkedList[100];
        for (int i = 0; i < data.length; i++) data[i] = new LinkedList<>();
    }

    public boolean put(K key, V value){
        Item<K, V> item = new Item<>(key, value);
        int itemIndex = index(item.key);
        if (!contains(key)) data[itemIndex].add(item);
        else return false;
        return true;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        int itemIndex = index(key);
        for (Item<K, V> item : data[itemIndex]) {
            if (item.key.equals(key)) return item.value;
        }
        return null;
    }

    public boolean updateValue(K key, V value) {
        int itemIndex = index(key);
        for (Item<K, V> item : data[itemIndex]) {
            if (item.key.equals(key)) {
                item.value = value;
                return true;
            }
        }
        return false;
    }

    private int index(K item) {
        return item.hashCode() % data.length;
    }
}

class HashTableMain {

    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("Test", 122);
        table.put("Hello", 374);
        System.out.println(table.contains("Test"));
        System.out.println(table.contains("Food"));
        System.out.println(table.get("Test"));
        System.out.println(table.get("Hello"));
        System.out.println(table.put("Test", 563));
        System.out.println(table.updateValue("Test", 687));
        System.out.println(table.get("Test"));
        System.out.println(table.updateValue("Hello", 34));
    }
}

