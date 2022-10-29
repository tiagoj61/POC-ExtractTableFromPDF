package teste;

public class ListaEncadeada {
	private int qtdElementos;
	private No inical;

	public void inserirInicio(No no) {
		no.setProximo(this.inical);
		this.inical = no;
	}

	public void inserirFim(No no) {
		No busca = inical;
		while (busca.getProximo() != null) {
			busca = busca.getProximo();
		}
		busca.setProximo(no);
	}

	public void removePrimeiro() {
		inical = inical.getProximo();
	}

	public void removeUltimo() {
		No a1 = inical;
		while (a1.getProximo() != null) {
			No a3 = a1.getProximo();
			if (a3.getProximo() == null) {
				a1.setProximo(null);
				break;
			}
		}
	}

	public No buscar(int id) {
		No busca = inical;
		No retorno = null;
		if (busca.getId() == id) {
			retorno = busca;
			return retorno;
		}
		while (busca != null || busca.getProximo().getId() != id) {
			busca = busca.getProximo();
		}
		retorno = busca;
		return retorno;

	}

}
