package lecatita.dao.enumoperations;

import lecatita.step.writer.state.model.Burden;

public enum InsertsEnum {
	BURDEN("INSERT INTO " + Burden.class.getSimpleName() + " ("+Burden.class.getDeclaredFields()[0].getName()+
			","+Burden.class.getDeclaredFields()[1].getName()+","+Burden.class.getDeclaredFields()[2].getName()+") VALUES(" + " :" + Burden.class.getDeclaredFields()[0].getName() + ", :"
			+ Burden.class.getDeclaredFields()[1].getName() + ", :" + Burden.class.getDeclaredFields()[2].getName() + ")");

	private final String value;

	InsertsEnum(String string) {
		value = string;
	}

	public String getValue() {
		return value;
	}

}
