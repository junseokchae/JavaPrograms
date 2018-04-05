
public class Warlus extends Animal {
	private String dentalHealth;

	public Warlus(String species, String sex, double weight, String dentalHealth) {
		super(species, sex, weight);
		this.dentalHealth = dentalHealth;
	}

	public String getDentalHealth() {
		return dentalHealth;
	}

	public void setDentalHealth(String dentalHealth) {
		this.dentalHealth = dentalHealth;
	}

}
