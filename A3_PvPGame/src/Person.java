
public abstract class Person {
	private String personName;
	private String gender;
	private int level;
	public Person(String personName, String gender, int level) {
		this.personName = personName;
		this.gender = gender;
		this.level = level;
		// TODO Auto-generated constructor stub
	}
	private Pokemon myPokemon;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public Pokemon getMyPokemon() {
		return myPokemon;
	}
	public void setMyPokemon(Pokemon myPokemon) {
		this.myPokemon = myPokemon;
	}

}
