import java.util.List;

public class MediaWikiTable extends Table implements PrintableTable{

    public MediaWikiTable(Table table) {
        super(table);
    }

    public String print() {
        StringBuilder output = new StringBuilder();
        output.append("{|\n");

        for (String col: this.headers) {
            output.append("! ");
            output.append(col);
            output.append("\n");
        }

        for (List<String> row: this.content) {
            output.append("|-\n");
            for (String col: row) {
                output.append("|");
                output.append(col);
                output.append("\n");
            }
        }
        output.append("|}\n");

        return output.toString();
    }
}
