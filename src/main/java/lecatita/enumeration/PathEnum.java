package lecatita.enumeration;

public enum PathEnum {
	FILE_STORE("src/main/resources/file/"),
	FILE_CUT_STORE("src/main/resources/fileCuted/"),
	FILE_KEY("fileKey");

	private String value;

	PathEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
