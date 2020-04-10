package projecteuler;

import java.util.*;

public class Temporary {

    public static void main(String[] args) {
        System.out.println(wordPattern("abc", "dog cat hund"));
    }

    public static boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        List<String> words = new ArrayList<>();

        //following part can be replaced with split(" ") method
        String tmp = "";
        for (int i = 0; i < str.length(); i++) {
            tmp = "";
            int a = i;
            while (str.charAt(a) != ' ') {
                tmp += str.charAt(a);
                if (a >= str.length() - 1)
                    break;
                a++;
            }
            words.add(tmp);
            i = a;
        }

        if (words.size() != pattern.length())
            return false;

        for (int i = 0; i < pattern.length(); i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                if (map.containsValue(words.get(i)))
                    return false;
                else
                    map.put(pattern.charAt(i), words.get(i));
            } else {
                if (!map.get(pattern.charAt(i)).equals(words.get(i)))
                    return false;
            }
        }
        return true;
    }
}
