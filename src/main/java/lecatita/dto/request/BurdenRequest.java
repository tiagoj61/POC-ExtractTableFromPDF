package lecatita.dto.request;

public class BurdenRequest {
	private String company_id;
	private String year;
	private String name;
	private String quantity_male;
	private String quantity_female;

	public BurdenRequest(String company_id, String year, String name, String quantity_male, String quantity_female) {
		super();
		this.company_id = company_id;
		this.year = year;
		this.name = name;
		this.quantity_male = quantity_male;
		this.quantity_female = quantity_female;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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
