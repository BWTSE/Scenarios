import java.util.ArrayList;
import java.util.List;

public class Table {
    protected final List<String> headers;
    protected final List<List<String>> content;

    public Table () {
        this.headers = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    public Table (List<String> headers, List<List<String>> content) {
        this();
        this.headers.addAll(headers);
        for (List<String> row: content) {
            this.content.add(new ArrayList<>(row));
        }
    }

    public Table (Table table) {
        this(table.headers, table.content);
    }
}
