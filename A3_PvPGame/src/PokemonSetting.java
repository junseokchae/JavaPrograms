import java.util.Random;

public abstract class PokemonSetting {
	public static int[] pickStat()//method for picking stats
	{
		int[] pokemonStats = new int[3];
		int HP = 0;
		int attack = 0;
		int defense = 0;
		
		while (HP+attack+defense != 250)//get random stats until the total of three stats are 250
		{
			Random randHP = new Random();
			Random randAttack = new Random();
			Random randDefense = new Random();
			
			HP = randHP.nextInt(100);
			
			attack = randAttack.nextInt(100);
			
			defense = randDefense.nextInt(100);
		}
		
		pokemonStats[0] = HP;
		pokemonStats[1] = attack;
		pokemonStats[2] = defense;
		
		return pokemonStats;
	}
	
	public static int selectSpecies()//method for selecting random pokemon for the foe
	{
		int foePokemonNum;
		Random rand = new Random();
		foePokemonNum = rand.nextInt(6);
		
		return foePokemonNum;
	}
	public static String[] findTypeAndSpecies(int pokemonNum)//method for setting type and species for the pokemon
	{
		String[] pokemonSpeciesAndType = new String[2];
		String pokemonSpecies = null;
		String pokemonType = null;
		try
		{
			switch(pokemonNum) {
				case 0:
					pokemonSpecies = "Bulbasaur";
					pokemonType = "Grass";
					break;
				case 1:
					pokemonSpecies = "Charmander";
					pokemonType = "Fire";
					break;
				case 2:
					pokemonSpecies = "Squirtle";
					pokemonType = "Water";
					break;
				case 3:
					pokemonSpecies = "Pidgey";
					pokemonType = "Fly";
					break;
				case 4:
					pokemonSpecies = "Rattata";
					pokemonType = "Normal";
					break;
				case 5:
					pokemonSpecies = "Pikachu";
					pokemonType = "Electricity";
					break;
			}
		}
		finally {
			pokemonSpeciesAndType[0] = pokemonSpecies;
			pokemonSpeciesAndType[1] = pokemonType;
			return pokemonSpeciesAndType;
		}
		
	}
}
