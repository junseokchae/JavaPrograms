
public abstract class Animal {
	private String species;
	private String sex;	
	private double weight;
	private GPS myGPS;
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Animal(String species, String sex, double weight) {
		super();
		this.species = species;
		this.sex = sex;
		this.weight = weight;
	}

}
