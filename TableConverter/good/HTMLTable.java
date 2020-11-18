import java.util.List;

public class HTMLTable extends Table implements PrintableTable{

    public HTMLTable(Table table) {
        super(table);
    }

    public String print() {
        StringBuilder output = new StringBuilder();
        output.append("<table>");

        output.append("<thead>");
        output.append("<tr>");
        for (String col: this.headers) {
            output.append("<th>");
            output.append(col);
            output.append("</th>");
        }
        output.append("</tr>");
        output.append("</thead>");

        output.append("<tbody>");
        for (List<String> row: this.content) {
            output.append("<tr>");
            for (String col: row) {
                output.append("<td>");
                output.append(col);
                output.append("</td>");
            }
            output.append("</tr>");
        }
        output.append("</tbody>");

        output.append("</table>");

        return output.toString();
    }
}
