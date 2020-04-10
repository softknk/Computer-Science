package different;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode {

    public static int findPeak(int[] nums) {
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i-1] && nums[i] > nums[i+1])
                return i;
        }
        return -1;
    }

    public static boolean hasPairWithSum(List<Integer> data, int sum) {
        int i = 0, j = data.size() - 1;
        while (i < j) {
            int _sum = data.get(i) + data.get(j);
            if (_sum == sum)
                return true;
            else if (_sum < sum)
                i++;
            else
                j--;
        }
        return false;
    }

    public static boolean hasPairWithSum2(List<Integer> data, int sum) {
        Set<Integer> complements = new HashSet<>();
        for (int a : data) {
            if (complements.contains(sum - a))
                return true;
            complements.add(a);
        }
        return false;
    }

    public static int sortDesc(final int num) {
        int[] nums = new int[String.valueOf(num).length()];
        for (int i = 0; i < nums.length; i++)
            nums[i] = (num / (int) (Math.pow(10, i))) % 10;
        Arrays.sort(nums);
        int result = 0;
        for (int i = nums.length - 1; i >= 0; i--)
            result += nums[i] * (int) (Math.pow(10, i));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findPeak(new int[]{1, 2, 3, 1}));
        System.out.println(sortDesc(21445));
        System.out.println(sortDesc(145263));
        System.out.println(sortDesc(123456789));
    }
}

class Cycle {
    public static void main(String[] args) {
        List list = new List();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.getNode(8).next = list.getNode(3);

        System.out.println(isThereACycle(list.getNode(0)));
    }

    private static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private static class List {
        Node head;

        public void add(int item) {
            if (head == null)
                head = new Node(item, null);
            else {
                Node tmp = head;
                while (tmp.next != null)
                    tmp = tmp.next;
                tmp.next = new Node(item, null);
            }
        }

        public Node getNode(int index) {
            Node tmp = head;
            for (int i = 0; i < index; i++) {
                if (tmp != null)
                    tmp = tmp.next;
                else
                    return null;
            }
            return tmp;
        }
    }

    private static boolean isThereACycle(Node head) {
        if (head == null)
            return false;

        Node i = head, j = head;
        while (i.next != null && j.next != null && j.next.next != null) {
            i = i.next;
            j = j.next.next;

            if (i == j)
                return true;
        }
        return false;
    }
}

