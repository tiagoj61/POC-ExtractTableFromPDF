package lecatita.enumeration;

import java.util.regex.Pattern;

public enum PattenEnum {
	PIPE(Pattern.compile(PatternReplaceEnum.PIPE_SPACE.getPattern(), Pattern.CASE_INSENSITIVE)),
	LINEBREAK(Pattern.compile(PatternReplaceEnum.LINEBREAK_PIPE.getPattern(), Pattern.CASE_INSENSITIVE)),
	ENDFILE(Pattern.compile(PatternReplaceEnum.ENDFILE_PIPE.getPattern(), Pattern.CASE_INSENSITIVE)),
	MULTIPLESPACE(Pattern.compile(PatternReplaceEnum.MULTIPLESPACE_ONESPACE.getPattern(), Pattern.CASE_INSENSITIVE)),
	SPACEPIPE(Pattern.compile(PatternReplaceEnum.SPACEPIPE_PIPE.getPattern(), Pattern.CASE_INSENSITIVE));

	PattenEnum(Pattern compile) {
		this.pattern=compile;
	}

	private Pattern pattern;

	public Pattern getPattern() {
		return pattern;
	}

	

}
