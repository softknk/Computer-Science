package datastructures;

public class Test {

    public static void main(String[] args) {
        testList();
    }

    private static void testList() {
        List<Integer> list = new List<>();
        list.addAll(12, 45, 23, 67, 2);

        List.Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println(list.contains(45));
        System.out.println(list.contains(123));
        System.out.println(list.remove(23));
        System.out.println(list.removeAt(0));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
