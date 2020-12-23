package datastructures;

import java.util.ArrayList;

public class StringBuilder {

    private ArrayList<String> data;

    public StringBuilder() {
        data = new ArrayList<>();
    }

    public <T> StringBuilder append(T item) {
        data.add(String.valueOf(item));
        return this;
    }

    public String toString() {
        // since Java 10
        var wrapper = new Object() {String toString = "";};
        data.forEach(item -> wrapper.toString += item);
        return wrapper.toString;
    }

    // start inclusive; end exclusive
    public String substring(int start, int end) {
        StringBuilder builder = new StringBuilder();
        int currIndex = 0;
        for (String string : data) {
            for (int j = 0; j < string.length(); j++) {
                if (currIndex >= end) return builder.toString();
                if (currIndex >= start) builder.append(string.charAt(j));
                currIndex++;
            }
        }
        return "";
    }

    public int length() {
        return toString().length();
    }
}

class StringBuilderMain {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("Hallo");
        builder.append(2);
        builder.append("hallo" + true);
        builder.append("  1").append("Hallo").append(2.11);
        System.out.println(builder.toString());
        System.out.println(builder.substring(3, 8));
    }
}
