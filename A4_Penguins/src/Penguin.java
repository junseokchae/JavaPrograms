
public class Penguin extends Animal {
	private double bloodPressure;

	public double getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(double bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public Penguin(String species, String sex, double weight, double bloodPressure) {
		super(species, sex, weight);
		this.bloodPressure = bloodPressure;
	}

}
