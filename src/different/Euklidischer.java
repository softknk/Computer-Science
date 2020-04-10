package different;

public class Euklidischer {

    public static void main(String[] args) {
        System.out.println(ggT(156, 66));
        System.out.println(ggT(2931, 681));
        System.out.println(ggT(80, 100));
    }

    public static int ggT(int num1, int num2) {
        int a = num1, b = num2, c = a % b;
        while (c != 0) {
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }
}
