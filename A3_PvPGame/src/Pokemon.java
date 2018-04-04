
public class Pokemon {
	public Pokemon(String species, String type, int hp, int attack, int defense) {
		super();
		this.species = species;
		this.type = type;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}
	private String species;
	private String type;
	public int hp;
	public int attack;
	public int defense;
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}

}
