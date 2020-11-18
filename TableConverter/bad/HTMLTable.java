import java.util.Scanner;

public class HTMLTable {
    public static void generate() {
        StringBuilder output = new StringBuilder();
        output.append("<table>");
        Scanner scanner = new Scanner(System.in);

        output.append("<thead>");
        String line = scanner.nextLine();
        String[] cols = line.split(",");
        output.append("<tr>");
        for (String col: cols) {
            output.append("<th>");
            output.append(col);
            output.append("</th>");
        }
        output.append("</tr>");
        output.append("</thead>");

        output.append("<tbody>");
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            cols = line.split(",");
            if (!line.trim().isBlank() && cols.length > 0) {
                output.append("<tr>");
                for (String col: cols) {
                    output.append("<td>");
                    output.append(col);
                    output.append("</td>");
                }
                output.append("</tr>");
            }
        }
        output.append("</tbody>");
        output.append("</table>");

        System.out.print(output);
    }
}
