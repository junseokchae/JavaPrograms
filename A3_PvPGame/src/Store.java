
public class Store {
	public static int loadGold(User userName) {//get gold to compare to the price of the item
		int gold;
		gold = userName.getGold();
		return gold;		
	}
	public static boolean pickItem(User userName, Item itemName)
	{
		boolean abletoPickItem;//when the user tries to go out with the item, we need to check if s/he has enough money to pay that
		int userGold = userName.getGold();
		int itemPrice = itemName.getPrice();
		if (userGold >= itemPrice)//when user can pay the price for the item
		{
			userGold = userGold - itemPrice;//subtract the price from user's gold
			userName.setGold(userGold);
			abletoPickItem = true;
		}
		else
			abletoPickItem = false;
		return abletoPickItem;
	}
	public static void applyEffect(Pokemon pokemonName, Item itemName)//apply effect of the item - get the effect from the item and set it into the pokemon
	{
		int effectHP = itemName.getEffectHP();
		int effectAttack = itemName.getEffectAttack();
		int effectDefense = itemName.getEffectDefense();
		int pokemonHP = pokemonName.getHp();
		int pokemonAttack = pokemonName.getAttack();
		int pokemonDefense = pokemonName.getDefense();
		pokemonName.setHp(pokemonHP + effectHP);
		pokemonName.setAttack(pokemonAttack + effectAttack);
		pokemonName.setDefense(pokemonDefense + effectDefense);
	}
	

}
