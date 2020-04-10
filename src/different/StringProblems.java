package different;

import java.util.HashSet;
import java.util.Set;

public class StringProblems {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix("abaabcccdttttf"));
        System.out.println(firstRecurringCharacter("abaggh"));
    }

    public static int longestCommonPrefix(String s) {
        int longest = 0, index = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int _index = i, size = 0;
            while (s.charAt(_index) == s.charAt(_index + 1)) {
                _index++;
                size++;

                if (_index >= s.length() - 1)
                    break;
            }
            if (size > longest) {
                longest = size;
                index = i;
            }
            i = _index;
        }
        return index;
    }

    public static char firstRecurringCharacter(String s) {
        Set<Character> characters = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (characters.contains(s.charAt(i)))
                return s.charAt(i);
            else
                characters.add(s.charAt(i));
        }
        return '-';
    }
}
