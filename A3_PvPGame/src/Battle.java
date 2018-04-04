public class Battle {
	
	public static String move(Pokemon userPokemon, Pokemon foePokemon, int turn)
	{
		String battleCommentary = null;
		int userPokemonHP = userPokemon.getHp();
		int userPokemonAttack = userPokemon.getAttack();
		int userPokemonDefense = userPokemon.getDefense();
		int foePokemonHP = foePokemon.getHp();
		int foePokemonAttack = foePokemon.getAttack();
		int foePokemonDefense = foePokemon.getDefense();
		int userPokemonAttackEffect = userPokemonAttack - foePokemonDefense;//effect should be calculated by attacker's attack and defender's defense
		if(userPokemonAttackEffect <0)//if the effect is negative, it makes no sense
			userPokemonAttackEffect = 0;
		int foePokemonAttackEffect = foePokemonAttack - userPokemonDefense;//same of the effect of user's pokemon's move
		if(userPokemonHP > 0 && foePokemonHP > 0)//we shouldn't make the pokemon move when either of pokemons are fainted.
		{
			if(foePokemonAttackEffect <0)
				foePokemonAttackEffect = 0;
			switch(turn) {//pokemons should take turns
				case 1:
				case 3:
				case 5:
				case 7:
				case 9:
					foePokemonHP = foePokemonHP - userPokemonAttackEffect;
					if(foePokemonHP < 0)
						foePokemonHP = 0;					
					foePokemon.setHp(foePokemonHP);
					if(userPokemonAttackEffect > 0)
						battleCommentary = "Your Pokemon attacks the foe's Pokemon" + "The HP of the foe's Pokemon decreased by " + String.valueOf(userPokemonAttackEffect);//make a commentary about user pokemon's move
					else
						battleCommentary = "Your Pokemon missed his/her move";//when the effect is 0, we will say the pokemon missed his/her move
					break;
				case 2:
				case 4:
				case 6:
				case 8:
				case 10:
					userPokemonHP = userPokemonHP - foePokemonAttackEffect;
					if(userPokemonHP < 0)
						userPokemonHP = 0;
					userPokemon.setHp(userPokemonHP);
					if(foePokemonAttackEffect > 0)
						battleCommentary = "The foe's Pokemon attacks your Pokemon. The HP of your Pokemon decreased by " + String.valueOf(foePokemonAttackEffect);//make a commentary about foe pokemon's move
					else
						battleCommentary = "The foe's Pokemon missed his/her move";//when the effect is 0, we will say the pokemon missed his/her move
					break;
			}
		}
		else
			battleCommentary = "ERROR";
		
		return battleCommentary;
	}
	public static String result(User user, Pokemon userPokemon, Pokemon foePokemon) {
		String result;
		int userPokemonHP = userPokemon.getHp();
		int foePokemonHP = foePokemon.getHp();
		if(Integer.compare(userPokemonHP,0) == 0)//if your pokemon got fainted, you will lose half of your gold
		{
			result = "Your pokemon has fainted. You lost your half of your gold.";
			int gold = user.getGold();
			gold = (int) (0.5 * gold);
			user.setGold(gold);
		}
		else if(Integer.compare(foePokemonHP,0) == 0)//if foe's pokemon got fainted, you will get 500 gold
		{
			result = "The foe's pokemon has fainted. You gained 500 gold. Congratulations!";
			int gold = user.getGold();
			gold = gold + 500;
			user.setGold(gold);
		}
		else if(Integer.compare(userPokemonHP,foePokemonHP) > 0)//if your pokemon has more HP than the foe's pokemon, you will get 100 gold
		{
			result = "Your pokemon won the battle. You gained 100 gold. Congratulations!";
			int gold = user.getGold();
			gold = gold + 100;
			user.setGold(gold);
		}
		else if(Integer.compare(userPokemonHP,foePokemonHP) < 0)//if your pokemon has less HP than the foe's pokemon, you will lose a quarter of your gold
		{
			result = "The foe's pokemon won the battle. You lost your quarter of your gold.";
			int gold = user.getGold();
			gold = (int) (0.75 * gold);
			user.setGold(gold);
		}
		else
			result = "The battle ended in a tie. Let's try again!";//if the battle ended in a tie, nobody will get the money from the other
		return result;
	}
	
}
