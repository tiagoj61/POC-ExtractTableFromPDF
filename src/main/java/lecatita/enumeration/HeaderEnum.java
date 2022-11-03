package lecatita.enumeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum HeaderEnum {
	MEN(new String[] { "homem", "homens", "h", "masculino", "m" }),
	WOMEN(new String[] { "mulher", "mulheres", "m", "feminino", "f" });

	private String[] value;

	HeaderEnum(String[] strings) {
		this.value = strings;
	}

	public String[] getValue() {
		return value;
	}

	public static boolean contains(String[] toCompare) {
		for (HeaderEnum headerEnum : values()) {
			if (Arrays.stream(headerEnum.getValue()).anyMatch(Arrays.stream(toCompare)::equals)) {
				return true;
			}
		}
		return false;
	}

	public static List<Integer> positionOfMenEquals(String[] toCompare) {
		List<Integer> matchings = new ArrayList<>();
		for (String homemCompare : Arrays.asList(toCompare)) {
			int match = Arrays.asList(HeaderEnum.MEN.getValue()).indexOf(homemCompare);
			if (match != -1) {
				matchings.add(match);
			}
		}
		return matchings;

	}
	public static List<Integer> positionOfWomenEquals(String[] toCompare) {
		List<Integer> matchings = new ArrayList<>();
		for (String homemCompare : Arrays.asList(toCompare)) {
			int match = Arrays.asList(HeaderEnum.WOMEN.getValue()).indexOf(homemCompare);
			if (match != -1) {
				matchings.add(match);
			}
		}
		return matchings;
		
	}


}
