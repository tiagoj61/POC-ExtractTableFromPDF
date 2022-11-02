package lecatita.enumeration;

public enum CaractersEnum {
	PIPE_SPACE("(\\|+)", " "),
	LINEBREAK_PIPE("[\\n\\r]+", "\\|\n"), 
	ENDFILE_PIPE(" $", "\\|"),
	MULTIPLESPACE_ONESPACE("( +)", " "),
	SPACEPIPE_PIPE(" \\|", "\\|");

	private final String pattern;
	private final String replace;

	CaractersEnum(String pattern, String replace) {
		this.pattern = pattern;
		this.replace = replace;
	}

	public String getPattern() {
		return pattern;
	}

	public String getReplace() {
		return replace;
	}
}
