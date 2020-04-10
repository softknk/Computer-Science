package application;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileHandling {

    public static List<String> getFileContentAsList(File file) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            return fileReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public static List<String> getFileContentThroughInputStream(InputStream stream, boolean line) {
        List<String> file = new ArrayList<>();
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNext()) {
            if (line)
                file.add(scanner.nextLine());
            else
                file.add(scanner.next());
        }
        return file;
    }

    public static void writeInFile(File file, String... lines) {
        List<String> oldContent = getFileContentAsList(file);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String oldLine : oldContent) {
                writer.write(oldLine);
                writer.newLine();
            }
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir") + "/src/application/test.txt");
        List<String> fileList = getFileContentAsList(file);
        fileList.forEach(System.out::println);

        writeInFile(file, "Daniel", "Künkel");

        List<String> _fileList = getFileContentThroughInputStream(FileHandling.class.getResourceAsStream("/application/test.txt"), true);
        _fileList.forEach(System.out::println);
    }
}
