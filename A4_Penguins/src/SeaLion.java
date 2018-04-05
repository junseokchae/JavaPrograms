
public class SeaLion extends Animal {
	private int numOfSpots;

	public SeaLion(String species, String sex, double weight, int numOfSpots) {
		super(species, sex, weight);
		this.numOfSpots = numOfSpots;
	}

	public int getNumOfSpots() {
		return numOfSpots;
	}

	public void setNumOfSpots(int numOfSpots) {
		this.numOfSpots = numOfSpots;
	}

}
