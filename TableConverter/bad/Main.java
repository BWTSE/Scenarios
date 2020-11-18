class Main {
	public static void main(String[] args) {
		switch (args[0]) {
			case "html":
				HTMLTable.generate();
				break;
			case "mediawiki":
				MediaWikiTable.generate();
				break;
			default:
				throw new IllegalArgumentException(args[0]);
		}
	}
}