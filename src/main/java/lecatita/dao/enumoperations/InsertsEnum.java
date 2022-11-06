package lecatita.dao.enumoperations;

import lecatita.step.writer.state.model.Burden;

public enum InsertsEnum {
	BURDEN("INSERT INTO " + Burden.class.getSimpleName() + "VALUES(" + " :" + Burden.class.getFields()[0] + " :"
			+ Burden.class.getFields()[1] + " :" + Burden.class.getFields()[2] + ")");

	private final String value;

	InsertsEnum(String string) {
		value = string;
	}

	public String getValue() {
		return value;
	}

}
