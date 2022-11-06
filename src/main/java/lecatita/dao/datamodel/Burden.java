package lecatita.dao.datamodel;

public class Burden {
	private String name;
	private String quantity_male;
	private String quantity_female;

	public Burden(String name, String quantity_male, String quantity_female) {
		super();
		this.name = name;
		this.quantity_male = quantity_male;
		this.quantity_female = quantity_female;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity_male() {
		return quantity_male;
	}

	public void setQuantity_male(String quantity_male) {
		this.quantity_male = quantity_male;
	}

	public String getQuantity_female() {
		return quantity_female;
	}

	public void setQuantity_female(String quantity_female) {
		this.quantity_female = quantity_female;
	}

}
