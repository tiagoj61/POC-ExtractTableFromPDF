package lecatita.dto.response;

public class MensageResponseDTO {
	private String mensage;

	public MensageResponseDTO(String mensage) {
		super();
		this.mensage = mensage;
	}

	public String getMensage() {
		return mensage;
	}

	public void setMensage(String mensage) {
		this.mensage = mensage;
	}
	
}

