package lecatita.dto;

public class StandartReturn<T> {
	private T data;

	public static <T> StandartReturn<T> build(T t) {
		var novo = new StandartReturn<T>();
		novo.data = t;
		return novo;
	}
}
