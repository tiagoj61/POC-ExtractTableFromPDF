package lecatita.enumeration;

public enum IdenfierStepEnum {
	TABLE_KEY("table_key"),
	LINE_KEY("line_key");

	private String value;

	IdenfierStepEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
