
public class Item {
	
	public Item(String name, int price, int effectHP, int effectAttack, int effectDefense) {
		super();
		this.name = name;
		this.price = price;
		this.effectHP = effectHP;
		this.effectAttack = effectAttack;
		this.effectDefense = effectDefense;
	}
	private String name;
	public int price;
	public int effectHP;
	public int effectAttack;
	public int effectDefense;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getEffectHP() {
		return effectHP;
	}
	public void setEffectHP(int effectHP) {
		this.effectHP = effectHP;
	}
	public int getEffectAttack() {
		return effectAttack;
	}
	public void setEffectAttack(int effectAttack) {
		this.effectAttack = effectAttack;
	}
	public int getEffectDefense() {
		return effectDefense;
	}
	public void setEffectDefense(int effectDefense) {
		this.effectDefense = effectDefense;
	}
	
}
