import java.util.Scanner;

public class MediaWikiTable {
    public static void generate() {
        StringBuilder output = new StringBuilder();
        output.append("{|\n");
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String[] cols = line.split(",");
        for (String col: cols) {
            output.append("! ");
            output.append(col);
            output.append("\n");
        }

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            cols = line.split(",");
            if (!line.trim().isBlank() && cols.length > 0) {
                output.append("|-\n");
                for (String col: cols) {
                    output.append("|");
                    output.append(col);
                    output.append("\n");
                }
            }
        }
        output.append("|}\n");

        System.out.print(output);
    }
}
