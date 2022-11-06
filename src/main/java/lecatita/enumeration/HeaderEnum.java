package lecatita.enumeration;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public enum HeaderEnum {
	MEN(new String[] { "homem", "male", "homens", "h", "masculino", "m" }),
	WOMEN(new String[] { "mulher", "female", "mulheres", "m", "feminino", "f" });

	private String[] value;

	HeaderEnum(String[] strings) {
		this.value = strings;
	}

	public String[] getValue() {
		return value;
	}

	private static List<String> cleanArrayToList(String[] array) {
		return Arrays.stream(array)
				.map(x -> Normalizer.normalize(x, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase())
				.collect(Collectors.toList());

	}

	public static boolean contains(String[] toCompare) {

		List<String> coverted = cleanArrayToList(toCompare);
		for (HeaderEnum headerEnum : values()) {
			boolean anyMatch = Arrays.asList(headerEnum.getValue()).stream()
					.anyMatch(new HashSet<>(coverted)::contains);
			if (anyMatch) {
				return true;
			}
		}
		return false;
	}

	public static List<Integer> positionOfMenEquals(String[] toCompare) {
		List<String> coverted = cleanArrayToList(toCompare);
		List<Integer> matchings = new ArrayList<>();
		for (String homemCompare : coverted) {
			int match = Arrays.asList(HeaderEnum.MEN.getValue()).indexOf(homemCompare);
			if (match != -1) {
				matchings.add(match);
			}
		}
		return matchings;

	}

	public static List<Integer> positionOfWomenEquals(String[] toCompare) {
		List<String> coverted = cleanArrayToList(toCompare);
		List<Integer> matchings = new ArrayList<>();
		for (String homemCompare : coverted) {
			int match = Arrays.asList(HeaderEnum.WOMEN.getValue()).indexOf(homemCompare);
			if (match != -1) {
				matchings.add(match);
			}
		}
		return matchings;

	}

}
