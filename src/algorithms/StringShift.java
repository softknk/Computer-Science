package algorithms;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class StringShift {

    enum Color {
        WHITE, BLACK, RED
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(shift(s, new int[][]{{0, 1}, {1, 2}}));
        String s1 = "abcdefg";
        System.out.println(shift(s1, new int[][]{{1, 1}, {1, 1}, {0, 2}, {1, 3}}));

        System.out.println(shift("abcde", new int[][]{{0, 3}}));

        /*make();
        hallo();*/

        System.out.println(firstNonRepeatingCharacter("aaabbcddeffgc"));

        System.out.println(Color.RED.ordinal());

        System.out.println(duplicateWithRangeN(Arrays.asList(2, 56, 98, 102, 105), 3));
    }

    private static String shift(String s, int[][] shift) {
        int total = 0;

        for (int[] a : shift)
            total += (a[0] == 0) ? -a[1] : a[1];

        if (total == 0)
            return s;

        int n = s.length();
        total %= n;

        if (total < 0)
            total += n;

        return s.substring(n - total) + s.substring(0, n - total);
    }

    public static boolean rotateString(String a, String b) {
        return (a.length() == b.length() && (a + a).contains(b));
    }

    public static void make() {
        for (int i = 0, j = 9; i < 10 && j >= 0; i++, j--) {
            System.out.println(i + ", " + j);
        }
    }

    public static void hallo() {
        PriorityQueue<Integer> queue = new PriorityQueue<>((int1, int2) -> (int1 > int2) ? 1 : -1);
        queue.addAll(Arrays.asList(5, 6, 7, 2, 1, 9));
        while (!queue.isEmpty())
            System.out.println(queue.poll());
    }

    public static char firstNonRepeatingCharacter(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)))
                map.replace(s.charAt(i), map.get(s.charAt(i)) + 1);
            else
                map.put(s.charAt(i), 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1)
                return s.charAt(i);
        }

        return '_';
    }

    public static boolean duplicateWithRangeN(List<Integer> values, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < values.size(); i++) {
            int a = values.get(i);
            for (int j = a - k; j <= a + k; j++) {
                if (set.contains(j))
                    return true;
            }
            if (!set.contains(a))
                set.add(a);
        }
        return false;
    }
}

class ArrayList {

    int[] data;

    public ArrayList() {
        data = new int[0];
    }

    public void add(int value) {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = value;
    }

    public int get(int index) {
        return data[index];
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(5);
        list.add(9);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    public static <T> void print(T[] n) {
        for (T t : n)
            System.out.print(t + " ");
    }
}

class Test {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.gymneureut.de/");
        /*    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            while (reader.readLine() != null) {
                System.out.println(reader.readLine());
            } */

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while (reader.readLine() != null)
                System.out.println(reader.readLine());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void webServer() throws IOException {
        ServerSocket server = new ServerSocket(9999);
        List<Socket> clients = new LinkedList<>();

        Thread clientRequest = new Thread(() -> {
            while (clients.size() <= 10) {
                try {
                    clients.add(server.accept());
                    handleClient(clients.get(clients.size() - 1));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //debugging purpose
                clients.forEach(System.out::println);
            }
        });
        clientRequest.start();
    }

    private static void handleClient(Socket socket) {
        Thread clientHandling = new Thread(() -> {
            try {
                /*BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write("Hallo " + socket.getInetAddress());
                writer.flush();
                writer.close();*/
                PrintStream out = new PrintStream(socket.getOutputStream());
                File file = new File(System.getProperty("user.dir") + "/src/algorithms/index.html");
                File style_file = new File(System.getProperty("user.dir") + "/src/algorithms/style.css");


                //send header
                out.println("HTTP/1.1 200 OK");
                out.println("Server: softknk Webserver : 1.0");
                out.println("Date: " + LocalDate.now());
                out.println("Content-type: " + "text/html");
                out.println("Content-length: " + file.length());

                out.println();
                out.flush();

                //send the actual website file
                out.write(Files.readAllBytes(file.toPath()));

                out.flush();

                //send header
                out.println("HTTP/1.1 200 OK");
                out.println("Server: softknk Webserver : 1.0");
                out.println("Date: " + LocalDate.now());
                out.println("Content-type: " + "text/css");
                out.println("Content-length: " + file.length());

                out.write(Files.readAllBytes(style_file.toPath()));

                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        clientHandling.start();
    }
}

class Server {
    public static void main(String[] args) throws IOException {
        Test.webServer();
    }
}

class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 9999);
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        reader.lines().forEach(System.out::println);
    }
}

class UDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9999);
        byte[] buf = new byte[3];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        byte[] data = packet.getData();
        for (byte b : data) {
            System.out.println(b);
        }

        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());
        socket.send(packet);
    }
}

class Client_UDP {
    public static void main(String[] args) throws IOException {
        byte[] buf = {0x2, 0x5, 0x7};
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("127.0.0.1"), 9999);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
    }
}

class EchoUDPServer {
    public static void main(String[] args) throws SocketException {
        DatagramSocket serverSocket = new DatagramSocket(9999);
        Thread server = new Thread(() -> {
            while (true) {
                try {
                    byte[] b = new byte[256];
                    DatagramPacket packet = new DatagramPacket(b, b.length);
                    serverSocket.receive(packet);

                    DatagramPacket sendPacket = new DatagramPacket(b, b.length, packet.getAddress(), packet.getPort());
                    serverSocket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        server.start();
    }
}

class EchoUDPClient {
    public static void main(String[] args) throws IOException {
        byte[] b = input();
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(b, b.length, InetAddress.getByName("127.0.0.1"), 9999);
        socket.send(packet);

        byte[] response = new byte[b.length];
        DatagramPacket responsePacket = new DatagramPacket(response, response.length);
        socket.receive(responsePacket);

        System.out.println(new String(responsePacket.getData()));
    }

    private static byte[] input() {
        byte[] b = Base64.getDecoder().decode("Daniel");
        for (byte a : b)
            System.out.println(a);
        System.out.println(new String(b));
        int z = 13 >>> 2;
        int u = ~4;
        System.out.println(u);
        System.out.println(Integer.toBinaryString(z));

        //192.168.0.34
        int ip = 0b11000000_10101000_00000000_00100010;
        //255.255.255.0
        int subnetmask = 0b11111111_11111111_11111111_00000000;

        int networkAdress = ip & subnetmask;
        String binary = Integer.toBinaryString(networkAdress);

        System.out.println("Test: " + getBinaryAsNumber("1101"));

        System.out.print(getBinaryAsNumber(binary.substring(0, 8)) + ".");
        System.out.print(getBinaryAsNumber(binary.substring(8, 16)) + ".");
        System.out.print(getBinaryAsNumber(binary.substring(16, 24)) + ".");
        System.out.print(getBinaryAsNumber(binary.substring(24, 32)));

        return (new Scanner(System.in).nextLine()).getBytes();
    }

    private static int getBinaryAsNumber(String binary) {
        int number = 0;
        for (int j = 1, i = binary.length() - 1; i >= 0; i--, j *= 2) {
            number += Character.getNumericValue(binary.charAt(i)) * j;
        }
        return number;
    }
}

class FileReading {
    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException {
      /*  File file = new File(StringShift.class.getResource("/algorithms/index.html").toURI());
        FileInputStream inputStream = new FileInputStream(file);
        byte[] b = new byte[(int) (file.length())];
        inputStream.read(b);
        System.out.println(new String(b)); */

        File file = new File(StringShift.class.getResource("/algorithms/test.txt").toURI());
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(new Person(15, "Daniel"));
        outputStream.flush();
        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        Person person = (Person) inputStream.readObject();
        System.out.println(person);
    }
}

class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" + "age=" + age + ", name='" + name + '\'' + '}';
    }
}

class EquationTree {

    static Set<Character> operations;

    static {
        operations = new HashSet<>();
        operations.addAll(Arrays.asList('+', '-', '*', '/'));
    }

    static class Node {
        char data;
        Node left, right;

        public Node(char data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node() {

        }
    }

    public static void main(String[] args) {
        String equation = new Scanner(System.in).nextLine();
        Node root = toEquationTree(equation);
        System.out.println(solveEquation(root));
    }

    private static int solveEquation(Node head) {
        if (head == null)
            return 0;

        if (Character.isDigit(head.data))
            return Character.getNumericValue(head.data);

        switch (head.data) {
            case '+':
                return Operations.add(solveEquation(head.left), solveEquation(head.right));
            case '-':
                return Operations.subtract(solveEquation(head.left), solveEquation(head.right));
            case '*':
                return Operations.multiply(solveEquation(head.left), solveEquation(head.right));
            case '/':
                return Operations.divide(solveEquation(head.left), solveEquation(head.right));
        }

        return -1;
    }

    private static Node toEquationTree(String equation) {
        if (equation == null || equation == "")
            return null;

        if (equation.length() <= 1)
            return new Node(equation.charAt(0), null, null);

        if (equation.startsWith("(") && equation.endsWith(")"))
            return toEquationTree(equation.substring(1, equation.length() - 1));

        Node root = new Node();

        HashMap<Character, Integer> operationMap = new HashMap<>();
        int in_klammern_counter = 0;

        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '(')
                in_klammern_counter++;
            else if (equation.charAt(i) == ')')
                in_klammern_counter--;
            if (operations.contains(equation.charAt(i)) && in_klammern_counter == 0)
                operationMap.put(equation.charAt(i), i);
        }

        int i = getNextOperationIndex(operationMap);

        root.data = equation.charAt(i);
        System.out.println("left: " + equation.substring(0, i));
        System.out.println("right: " + equation.substring(i+1));
        root.left = toEquationTree(equation.substring(0, i));
        root.right = toEquationTree(equation.substring(i+1));
        return root;
    }

    private static int getNextOperationIndex(HashMap<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getKey() == '+' || entry.getKey() == '-')
                return entry.getValue();
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getKey() == '*' || entry.getKey() == '/')
                return entry.getValue();
        }

        return -1;
    }

    interface Operations {
        static int add(int a, int b) {
            return a + b;
        }

        static int subtract(int a, int b) {
            return a - b;
        }

        static int multiply(int a, int b) {
            return a * b;
        }

        static int divide(int a, int b) {
            return a / b;
        }
    }

    static void useCases() {
       HashMap<Integer, String> map = new HashMap<>();
       map.put(1, "Daniel");
       LinkedList<String> values = (LinkedList<String>) map.values();
       map.compute(1, (key, value) -> key + value);
       ((LinkedList<String>) map.values()).getFirst();
    }
}

class Tests {
    public static void main(String[] args) {
        useCases();
    }

    static void useCases() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Daniel");
        map.compute(1, (key, value) -> key + value);

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a+b;
        System.out.println(sum.apply(1, 6));

        Consumer<Integer> consumer = a -> Math.pow(a, 2);
        consumer.accept(2);

        TreeMap<Integer, Character> treeMap = new TreeMap<>();
        treeMap.put(127, 'd');
        treeMap.put(56, 'e');
        treeMap.put(76, 'z');

        for (Map.Entry<Integer, Character> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }
}