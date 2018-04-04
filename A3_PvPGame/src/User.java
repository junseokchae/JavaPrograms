
public class User extends Person{
	public User(String personName, String gender, int level, int gold) {
		super(personName, gender, level);
		// TODO Auto-generated constructor stub
		this.gold = gold;
	}

	public int gold;

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	

}
