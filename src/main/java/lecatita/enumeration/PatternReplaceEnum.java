package lecatita.enumeration;

public enum PatternReplaceEnum {
	PIPE_SPACE("(\\|+)", " "),
	LINEBREAK_PIPE("[\\n\\r]+", "\\|\n"), 
	ENDFILE_PIPE(" $", "\\|"),
	MULTIPLESPACE_ONESPACE("( +)", " "),
	SPACEPIPE_PIPE(" \\|", "\\|");

	private final String pattern;
	private final String replace;

	PatternReplaceEnum(String pattern, String replace) {
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
