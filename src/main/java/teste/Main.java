package teste;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import lecatita.enumeration.HeaderEnum;

public class Main {
	public static void main(String[] args) {
		ListaEncadeada listaEncadeada = new ListaEncadeada();
		String[] toCompare = new String[] { "mulher", "mulheres", "m", "feminino", "f" };
		Arrays.stream(HeaderEnum.MEN.getValue()).forEach(el -> Arrays.asList(toCompare).indexOf(el));
	}

}
