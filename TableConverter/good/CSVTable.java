import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVTable extends Table {

    public CSVTable(List<String> headers, List<List<String>> content) {
        super(headers, content);
    }

    public static CSVTable fromCSV(InputStream source) {
        List<String> headers = new ArrayList<>();
        List<List<String>> content = new ArrayList<>();

        Scanner scanner = new Scanner(source);
        if (scanner.hasNextLine()) {
            List<String> row = getCSVColumns(scanner.nextLine());
            headers.addAll(row);
        }
        while (scanner.hasNextLine()) {
            List<String> row = getCSVColumns(scanner.nextLine());
            content.add(new ArrayList<>(row));
        }
        return new CSVTable(headers, content);
    }

    private static List<String> getCSVColumns(String row) {
        String[] cols = row.split(",");
        return Arrays.asList(cols);
    }
}
