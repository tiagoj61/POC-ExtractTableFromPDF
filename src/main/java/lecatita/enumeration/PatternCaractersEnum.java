package lecatita.enumeration;

import java.util.regex.Pattern;

public enum PatternCaractersEnum {
	PIPE(Pattern.compile(CaractersEnum.PIPE_SPACE.getPattern(), Pattern.CASE_INSENSITIVE)),
	LINEBREAK(Pattern.compile(CaractersEnum.LINEBREAK_PIPE.getPattern(), Pattern.CASE_INSENSITIVE)),
	ENDFILE(Pattern.compile(CaractersEnum.ENDFILE_PIPE.getPattern(), Pattern.CASE_INSENSITIVE)),
	MULTIPLESPACE(Pattern.compile(CaractersEnum.MULTIPLESPACE_ONESPACE.getPattern(), Pattern.CASE_INSENSITIVE)),
	SPACEPIPE(Pattern.compile(CaractersEnum.SPACEPIPE_PIPE.getPattern(), Pattern.CASE_INSENSITIVE));

	PatternCaractersEnum(Pattern compile) {
		this.pattern=compile;
	}

	private Pattern pattern;

	public Pattern getPattern() {
		return pattern;
	}

	

}
