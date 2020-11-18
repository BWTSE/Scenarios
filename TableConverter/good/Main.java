class Main {
	public static void main(String[] args) {
		Table input = CSVTable.fromCSV(System.in);

		PrintableTable output = switch (args[0]) {
			case "html" -> new HTMLTable(input);
			case "mediawiki" -> new MediaWikiTable(input);
			default -> throw new IllegalArgumentException(args[0]);
		};

		System.out.println(output.print());
	}
}