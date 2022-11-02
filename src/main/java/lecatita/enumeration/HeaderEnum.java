package lecatita.enumeration;

import java.util.Arrays;

public enum HeaderEnum {
	HOMEM(new String[] { "homem", "homens", "h", "masculino", "m" }),
	MULHER(new String[] { "mulher", "mulheres", "m", "feminino", "f" });

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
}
