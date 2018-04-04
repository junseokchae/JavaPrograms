import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextPane;
import java.util.Timer;
import java.util.TimerTask;


public class PvPGame extends JFrame {
	private boolean selectGender = false;//boolean variable for checking if the user set his/her gender
	private boolean selectPokemon = false;//boolean variable for checking if the user set his/her pokemon
	private boolean selectStats = false;//boolean variable for checking if the user set pokemon's stat 
	int foePokemonNum = 0;
	private String userName;
	private String userGender;
	private int userLevel = 1;
	private String foePokemonSpecies;
	private String userPokemonSpecies;
	private String foePokemonType;
	private String userPokemonType;
	private int foePokemonHP;
	private int foePokemonAttack;
	private int foePokemonDefense;
	private int userPokemonHP;
	private int userPokemonAttack;
	private int userPokemonDefense;
	private Item pickItemName;//the object is needed when the economic system is introduced

	private JPanel contentPane;
	private String[] trainerFiles = {"Foe.jpg","UserFemale.jpg","UserMale.png"};//connecting images about trainers including user
	private Icon[] trainerImages = {
			new ImageIcon(getClass().getResource("images/Trainer/" + trainerFiles[0])),
			new ImageIcon(getClass().getResource("images/Trainer/" + trainerFiles[1])),
			new ImageIcon(getClass().getResource("images/Trainer/" + trainerFiles[2]))
	};
	private String[] itemFiles = {"HPUp.png","Iron.png","Protein.png","Rarecandy.jpg"};//connecting images about items
	private Icon[] itemImages = {
			new ImageIcon(getClass().getResource("images/Item/" + itemFiles[0])),
			new ImageIcon(getClass().getResource("images/Item/" + itemFiles[1])),
			new ImageIcon(getClass().getResource("images/Item/" + itemFiles[2])),
			new ImageIcon(getClass().getResource("images/Item/" + itemFiles[3]))
	};
	private String[] pokemonFiles = {"Bulbasaur.png","Charmander.png","Squirtle.png","Pidgey.png","Rattata.png","Pikachu.png"};//connecting images about pokemons
	private Icon[] pokemonImages = {
			new ImageIcon(getClass().getResource("images/Pokemon/" + pokemonFiles[0])),
			new ImageIcon(getClass().getResource("images/Pokemon/" + pokemonFiles[1])),
			new ImageIcon(getClass().getResource("images/Pokemon/" + pokemonFiles[2])),
			new ImageIcon(getClass().getResource("images/Pokemon/" + pokemonFiles[3])),
			new ImageIcon(getClass().getResource("images/Pokemon/" + pokemonFiles[4])),
			new ImageIcon(getClass().getResource("images/Pokemon/" + pokemonFiles[5]))
	};
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PvPGame frame = new PvPGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PvPGame() {
		
		User User = new User(userName, userGender, userLevel, 50);//creating user
		setTitle("PokemonBattle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Foe Foe1 = new Foe("Green","Male",1,0);//making a foe named foe1
		String foeName = Foe1.getPersonName();
		int foeLevel = Foe1.getLevel(); 
				
		JPanel panelUser = new JPanel();
		panelUser.setBackground(Color.PINK);
		contentPane.add(panelUser, "name_294478479814968");
		panelUser.setLayout(null);
		
		JPanel panelPokemon = new JPanel();
		panelPokemon.setBackground(Color.ORANGE);
		contentPane.add(panelPokemon, "name_296060429167249");
		panelPokemon.setLayout(null);
		
		JPanel panelStore = new JPanel();
		panelStore.setBackground(Color.LIGHT_GRAY);
		panelStore.setVisible(false);
		contentPane.add(panelStore, "name_467107465629540");
		panelStore.setLayout(null);
		
		JLabel lblChooseTheItem = new JLabel("Choose the Item");
		lblChooseTheItem.setBounds(10, 11, 119, 14);
		panelStore.add(lblChooseTheItem);
		
		JLabel labelItemImage = new JLabel("");
		labelItemImage.setBounds(137, 14, 171, 113);
		panelStore.add(labelItemImage);
		
		JLabel lblName_1 = new JLabel("Name: ");
		lblName_1.setBounds(138, 209, 46, 14);
		panelStore.add(lblName_1);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(137, 234, 46, 14);
		panelStore.add(lblPrice);
		
		JLabel lblEffect = new JLabel("Effect:");
		lblEffect.setBounds(138, 260, 46, 14);
		panelStore.add(lblEffect);
		
		JLabel labelItemName = new JLabel("");
		labelItemName.setBounds(210, 209, 218, 14);
		panelStore.add(labelItemName);
		
		JLabel labelItemPrice = new JLabel("");
		labelItemPrice.setBounds(210, 234, 188, 14);
		panelStore.add(labelItemPrice);
		
		JLabel labelItemEffect = new JLabel("");
		labelItemEffect.setBounds(210, 260, 188, 14);
		panelStore.add(labelItemEffect);
				
		Item rareCandy = new Item("Rare Candy", 2500, 10,10,10);//making items as objectives using class
		Item hpUp = new Item("HP UP",10,10,0,0);
		Item iron = new Item("Iron",10,0,0,10);
		Item protein = new Item("Protein",10,0,10,0);
		Item none = new Item("None",0,0,0,0);
		
		JLabel labelEffectHP = new JLabel("+10");
		labelEffectHP.setForeground(Color.BLUE);
		labelEffectHP.setBounds(407, 38, 46, 14);
		labelEffectHP.setVisible(false);
		panelStore.add(labelEffectHP);
		
		JLabel labelEffectAttack = new JLabel("+10");
		labelEffectAttack.setForeground(Color.BLUE);
		labelEffectAttack.setBounds(407, 64, 46, 14);
		labelEffectAttack.setVisible(false);
		panelStore.add(labelEffectAttack);
		
		JLabel labelEffectDefense = new JLabel("+10");
		labelEffectDefense.setForeground(Color.BLUE);
		labelEffectDefense.setBounds(407, 90, 46, 14);
		labelEffectDefense.setVisible(false);
		panelStore.add(labelEffectDefense);
		
		pickItemName = none;
		
		JLabel lblSorryYouDont = new JLabel("Sorry, you don't have enough gold to buy this item. Select another item.");
		lblSorryYouDont.setVisible(false);
		lblSorryYouDont.setForeground(Color.RED);
		lblSorryYouDont.setBounds(91, 174, 407, 14);
		panelStore.add(lblSorryYouDont);
				
		JRadioButton rdbtnRarecandy = new JRadioButton("RareCandy");//action related to set item
		rdbtnRarecandy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelItemImage.setIcon(itemImages[3]);
				labelItemName.setText("Rare Candy");
				labelItemPrice.setText("2500");
				labelItemEffect.setText("Increase 10 for all stats");
				labelEffectHP.setVisible(true);
				labelEffectAttack.setVisible(true);
				labelEffectDefense.setVisible(true);
				pickItemName = rareCandy;
				lblSorryYouDont.setVisible(false);
			}
		});
		buttonGroup_2.add(rdbtnRarecandy);
		rdbtnRarecandy.setBackground(Color.LIGHT_GRAY);
		rdbtnRarecandy.setBounds(30, 34, 109, 23);
		panelStore.add(rdbtnRarecandy);
		
		JRadioButton rdbtnHpup = new JRadioButton("HPUp");//action related to set item
		rdbtnHpup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelItemImage.setIcon(itemImages[0]);
				labelItemName.setText("HP UP");
				labelItemPrice.setText("10");
				labelItemEffect.setText("Increase 10 for HP");
				labelEffectHP.setVisible(true);
				labelEffectAttack.setVisible(false);
				labelEffectDefense.setVisible(false);
				pickItemName = hpUp;
				lblSorryYouDont.setVisible(false);
			}
		});
		
		buttonGroup_2.add(rdbtnHpup);
		rdbtnHpup.setBackground(Color.LIGHT_GRAY);
		rdbtnHpup.setBounds(30, 60, 109, 23);
		panelStore.add(rdbtnHpup);
		
		JRadioButton rdbtnIron = new JRadioButton("Iron");//action related to set item
		rdbtnIron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelItemImage.setIcon(itemImages[1]);
				labelItemName.setText("Iron");
				labelItemPrice.setText("10");
				labelItemEffect.setText("Increase 10 for Defense");
				labelEffectHP.setVisible(false);
				labelEffectAttack.setVisible(false);
				labelEffectDefense.setVisible(true);
				pickItemName = iron;
				lblSorryYouDont.setVisible(false);
			}
		});
		buttonGroup_2.add(rdbtnIron);
		rdbtnIron.setBackground(Color.LIGHT_GRAY);
		rdbtnIron.setBounds(30, 86, 109, 23);
		panelStore.add(rdbtnIron);
		
		JRadioButton rdbtnProtein = new JRadioButton("Protein");//action related to set item
		rdbtnProtein.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelItemImage.setIcon(itemImages[2]);
				labelItemName.setText("Protein");
				labelItemPrice.setText("10");				
				labelItemEffect.setText("Increase 10 for Attack");
				labelEffectHP.setVisible(false);
				labelEffectAttack.setVisible(true);
				labelEffectDefense.setVisible(false);
				pickItemName = protein;
				lblSorryYouDont.setVisible(false);
			}
		});
		buttonGroup_2.add(rdbtnProtein);
		rdbtnProtein.setBackground(Color.LIGHT_GRAY);
		rdbtnProtein.setBounds(30, 116, 109, 23);
		panelStore.add(rdbtnProtein);
		
		JLabel lblYourGold = new JLabel("Your Gold");
		lblYourGold.setBounds(10, 174, 89, 14);
		panelStore.add(lblYourGold);
		
		JLabel labelUserGold = new JLabel("");
		labelUserGold.setBounds(40, 189, 88, 31);
		panelStore.add(labelUserGold);
		
		JButton btnBack_1 = new JButton("< Back");
		btnBack_1.setBounds(10, 300, 89, 23);
		panelStore.add(btnBack_1);
		
		JLabel lblBefore = new JLabel("Before");
		lblBefore.setBounds(318, 11, 46, 14);
		panelStore.add(lblBefore);
		
		JLabel lblAfter = new JLabel("After");
		lblAfter.setBounds(419, 11, 46, 14);
		panelStore.add(lblAfter);
		
		JLabel lblHp = new JLabel("HP");
		lblHp.setBounds(280, 38, 46, 14);
		panelStore.add(lblHp);
		
		JLabel lblAttack_1 = new JLabel("Attack");
		lblAttack_1.setBounds(280, 64, 46, 14);
		panelStore.add(lblAttack_1);
		
		JLabel lblDefense_1 = new JLabel("Defense");
		lblDefense_1.setBounds(280, 90, 46, 14);
		panelStore.add(lblDefense_1);
		
		JLabel labelPokemonHP = new JLabel("");
		labelPokemonHP.setBounds(328, 38, 46, 14);
		panelStore.add(labelPokemonHP);
		
		JLabel labelPokemonAttack = new JLabel("");
		labelPokemonAttack.setBounds(338, 64, 46, 14);
		panelStore.add(labelPokemonAttack);
		
		JLabel labelPokemonDefense = new JLabel("");
		labelPokemonDefense.setBounds(336, 90, 46, 14);
		panelStore.add(labelPokemonDefense);
		
		JRadioButton rdbtnNone = new JRadioButton("None");
		rdbtnNone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelItemImage.setIcon(null);
				labelEffectHP.setVisible(false);
				labelEffectAttack.setVisible(false);
				labelEffectDefense.setVisible(false);
				labelItemName.setText(null);
				labelItemPrice.setText(null);
				labelItemEffect.setText(null);
				pickItemName = none;
			}
		});
		buttonGroup_2.add(rdbtnNone);
		rdbtnNone.setBackground(Color.LIGHT_GRAY);
		rdbtnNone.setBounds(30, 144, 109, 23);
		panelStore.add(rdbtnNone);
		
		JLabel lblNewLabel = new JLabel("Choose Your Character's gender");
		lblNewLabel.setBounds(10, 41, 215, 14);
		panelUser.add(lblNewLabel);
		
		JLabel lblYouDidntSelect = new JLabel("You didn't select the Character's gender!");
		lblYouDidntSelect.setVisible(false);
		lblYouDidntSelect.setForeground(Color.RED);
		lblYouDidntSelect.setBounds(10, 196, 235, 14);
		panelUser.add(lblYouDidntSelect);

		JLabel lblNameError = new JLabel("Name Error: Include Number(s)");
		lblNameError.setForeground(Color.RED);
		lblNameError.setVisible(false);
		lblNameError.setBounds(10, 246, 276, 14);
		panelUser.add(lblNameError);
		
		JLabel lblNameError_1 = new JLabel("Name Error: You Should Input the Name");
		lblNameError_1.setVisible(false);
		lblNameError_1.setForeground(Color.RED);
		lblNameError_1.setBounds(10, 221, 276, 14);
		panelUser.add(lblNameError_1);		

		JLabel lblUserImage = new JLabel("");
		lblUserImage.setBounds(206, 39, 246, 158);
		panelUser.add(lblUserImage);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");//action related to set user's gender
		rdbtnMale.setBackground(Color.PINK);
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblUserImage.setIcon(trainerImages[2]);
				selectGender = true;//user selected his/her gender
				userGender = "Male";
				lblYouDidntSelect.setVisible(false);
			}
		});
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(32, 62, 109, 23);
		panelUser.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");//action related to set user's gender
		rdbtnFemale.setBackground(Color.PINK);
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblUserImage.setIcon(trainerImages[1]);
				selectGender = true;//user selected his/her gender
				userGender = "Female";
				lblYouDidntSelect.setVisible(false);
			}
		});
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(32, 88, 109, 23);
		panelUser.add(rdbtnFemale);
		
		JLabel lblYourName = new JLabel("Your Name: ");
		lblYourName.setBounds(252, 14, 78, 14);
		panelUser.add(lblYourName);
		
		textField = new JTextField();
		textField.setBounds(322, 11, 156, 20);
		panelUser.add(textField);
		textField.setColumns(10);
		
		JLabel lblVs = new JLabel("vs.");
		lblVs.setBounds(268, 208, 46, 14);
		panelUser.add(lblVs);
		
		JLabel labelFoeImage = new JLabel("");
		labelFoeImage.setBounds(296, 208, 97, 115);
		labelFoeImage.setIcon(trainerImages[0]);
		panelUser.add(labelFoeImage);
		
		JButton btnNext = new JButton("Next >");
		btnNext.setBounds(409, 289, 89, 23);
		panelUser.add(btnNext);
		
		JLabel lblYouNeedTo = new JLabel("You Need to Select Your Pokemon!!");
		lblYouNeedTo.setVisible(false);
		lblYouNeedTo.setForeground(Color.RED);
		lblYouNeedTo.setBounds(292, 180, 300, 14);
		panelPokemon.add(lblYouNeedTo);
				
		JLabel lblYouNeedTo_1 = new JLabel("You Need to Roll the stats!!!");
		lblYouNeedTo_1.setVisible(false);
		lblYouNeedTo_1.setForeground(Color.RED);
		lblYouNeedTo_1.setBounds(292, 205, 206, 14);
		panelPokemon.add(lblYouNeedTo_1);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(406, 221, 46, 14);
		panelUser.add(lblName);
		
		JLabel lblLevel = new JLabel("Level: ");
		lblLevel.setBounds(406, 246, 46, 14);
		panelUser.add(lblLevel);
		
		JLabel labelFoeName = new JLabel("");
		labelFoeName.setBounds(452, 221, 46, 14);
		labelFoeName.setText(foeName);
		panelUser.add(labelFoeName);
		
		JLabel labelFoeLevel = new JLabel("");
		labelFoeLevel.setBounds(452, 246, 46, 14);
		labelFoeLevel.setText(String.valueOf(foeLevel));
		panelUser.add(labelFoeLevel);
		
		JLabel lblSpecies = new JLabel("Species: ");
		lblSpecies.setBounds(159, 180, 67, 14);
		panelPokemon.add(lblSpecies);
		
		JLabel labelSpecies = new JLabel("");
		labelSpecies.setBounds(233, 180, 145, 14);
		panelPokemon.add(labelSpecies);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(159, 206, 67, 14);
		panelPokemon.add(lblType);
		
		JLabel labelType = new JLabel("");
		labelType.setBounds(233, 206, 178, 14);
		panelPokemon.add(labelType);
		
		JLabel lblChooseYourPokemon = new JLabel("Choose Your Pokemon");
		lblChooseYourPokemon.setBounds(10, 22, 112, 14);
		panelPokemon.add(lblChooseYourPokemon);

		JLabel labelUserPokemon = new JLabel("");
		labelUserPokemon.setBounds(170, 21, 289, 148);
		panelPokemon.add(labelUserPokemon);
		
		JRadioButton rdbtnBulbasaur = new JRadioButton("Bulbasaur");//action related to set user's pokemon
		rdbtnBulbasaur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelUserPokemon.setIcon(pokemonImages[0]);
				userPokemonSpecies = "Bulbasaur";
				userPokemonType = "Grass";
				labelSpecies.setText(userPokemonSpecies);
				labelType.setText(userPokemonType);
				selectPokemon = true;//user selected his/her pokemon
				lblYouNeedTo.setVisible(false);
			}
		});
		buttonGroup_1.add(rdbtnBulbasaur);
		rdbtnBulbasaur.setBackground(Color.ORANGE);
		rdbtnBulbasaur.setBounds(31, 43, 109, 23);
		panelPokemon.add(rdbtnBulbasaur);
		
		JRadioButton rdbtnCharmander = new JRadioButton("Charmander");//action related to set user's pokemon
		rdbtnCharmander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelUserPokemon.setIcon(pokemonImages[1]);
				userPokemonSpecies = "Charmander";
				userPokemonType = "Fire";
				labelSpecies.setText(userPokemonSpecies);
				labelType.setText(userPokemonType);
				selectPokemon = true;//user selected his/her pokemon
				lblYouNeedTo.setVisible(false);
			}
		});
		buttonGroup_1.add(rdbtnCharmander);
		rdbtnCharmander.setBackground(Color.ORANGE);
		rdbtnCharmander.setBounds(31, 70, 109, 23);
		panelPokemon.add(rdbtnCharmander);
		
		JRadioButton rdbtnSquirtle = new JRadioButton("Squirtle");//action related to set user's pokemon
		rdbtnSquirtle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelUserPokemon.setIcon(pokemonImages[2]);
				userPokemonSpecies = "Squirtle";
				userPokemonType = "Water";
				labelSpecies.setText(userPokemonSpecies);
				lblYouNeedTo.setVisible(false);
				labelType.setText(userPokemonType);
				selectPokemon = true;//user selected his/her pokemon
			}
		});
		buttonGroup_1.add(rdbtnSquirtle);
		rdbtnSquirtle.setBackground(Color.ORANGE);
		rdbtnSquirtle.setBounds(31, 96, 109, 23);
		panelPokemon.add(rdbtnSquirtle);
		
		JRadioButton rdbtnPidgey = new JRadioButton("Pidgey");//action related to set user's pokemon
		rdbtnPidgey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelUserPokemon.setIcon(pokemonImages[3]);
				userPokemonSpecies = "Pidgey";
				userPokemonType = "Fly";
				labelSpecies.setText(userPokemonSpecies);
				labelType.setText(userPokemonType);
				selectPokemon = true;//user selected his/her pokemon
				lblYouNeedTo.setVisible(false);
			}
		});
		buttonGroup_1.add(rdbtnPidgey);
		rdbtnPidgey.setBackground(Color.ORANGE);
		rdbtnPidgey.setBounds(31, 122, 109, 23);
		panelPokemon.add(rdbtnPidgey);
		
		JRadioButton rdbtnRattata = new JRadioButton("Rattata");//action related to set user's pokemon
		rdbtnRattata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelUserPokemon.setIcon(pokemonImages[4]);
				userPokemonSpecies = "Rattata";
				userPokemonType = "Normal";
				labelSpecies.setText(userPokemonSpecies);
				labelType.setText(userPokemonType);
				selectPokemon = true;//user selected his/her pokemon
				lblYouNeedTo.setVisible(false);
			}
		});
		buttonGroup_1.add(rdbtnRattata);
		rdbtnRattata.setBackground(Color.ORANGE);
		rdbtnRattata.setBounds(31, 146, 109, 23);
		panelPokemon.add(rdbtnRattata);
		
		JRadioButton rdbtnPikachu = new JRadioButton("Pikachu");//action related to set user's pokemon
		rdbtnPikachu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelUserPokemon.setIcon(pokemonImages[5]);
				userPokemonSpecies = "Pikachu";
				userPokemonType = "Electricity";
				labelSpecies.setText(userPokemonSpecies);
				labelType.setText(userPokemonType);
				selectPokemon = true;//user selected his/her pokemon
				lblYouNeedTo.setVisible(false);
			}
		});
		buttonGroup_1.add(rdbtnPikachu);
		rdbtnPikachu.setBackground(Color.ORANGE);
		rdbtnPikachu.setBounds(31, 172, 109, 23);
		panelPokemon.add(rdbtnPikachu);
		
		JButton btnBack = new JButton("< Back");
		btnBack.setBounds(31, 300, 89, 23);
		panelPokemon.add(btnBack);
		
		JLabel lblVs_1 = new JLabel("vs.");
		lblVs_1.setBounds(170, 227, 46, 14);
		panelPokemon.add(lblVs_1);
		
		JLabel labelFoePokemon = new JLabel("");
		labelFoePokemon.setBounds(207, 227, 222, 107);
		panelPokemon.add(labelFoePokemon);
		
		JLabel lblHP = new JLabel("HP");
		lblHP.setBounds(10, 229, 46, 14);
		panelPokemon.add(lblHP);
		
		JLabel lblAttack = new JLabel("Attack");
		lblAttack.setBounds(10, 254, 46, 14);
		panelPokemon.add(lblAttack);
		
		JLabel lblDefense = new JLabel("Defense");
		lblDefense.setBounds(10, 275, 46, 14);
		panelPokemon.add(lblDefense);
		
		JLabel lblHPStat = new JLabel("");
		lblHPStat.setBounds(66, 229, 46, 14);
		panelPokemon.add(lblHPStat);
		
		JLabel lblAttackStat = new JLabel("");
		lblAttackStat.setBounds(66, 254, 46, 14);
		panelPokemon.add(lblAttackStat);
		
		JLabel lblDefenseStat = new JLabel("");
		lblDefenseStat.setBounds(66, 275, 46, 14);
		panelPokemon.add(lblDefenseStat);
		
		JButton btnRollTheStats = new JButton("Roll the stats!");//action related to set the stats
		btnRollTheStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] userPokemonStats = PokemonSetting.pickStat();//use method from another class
				lblHPStat.setText(String.valueOf(userPokemonStats[0]));
				lblAttackStat.setText(String.valueOf(userPokemonStats[1]));
				lblDefenseStat.setText(String.valueOf(userPokemonStats[2]));
				userPokemonHP = userPokemonStats[0];
				userPokemonAttack = userPokemonStats[1];
				userPokemonDefense = userPokemonStats[2];
				selectStats = true;//user selected pokemon's stats
				lblYouNeedTo_1.setVisible(false);
			}
		});
		btnRollTheStats.setBounds(20, 202, 110, 23);
		panelPokemon.add(btnRollTheStats);

		JPanel panelBattle = new JPanel();
		contentPane.add(panelBattle, "name_37841913093822");
		panelBattle.setLayout(null);
		
		JLabel lblUsersPokemon = new JLabel("User's Pokemon");
		lblUsersPokemon.setBounds(102, 11, 117, 14);
		panelBattle.add(lblUsersPokemon);
		
		JLabel lblFoesPokemon = new JLabel("Foe's Pokemon");
		lblFoesPokemon.setBounds(276, 11, 117, 14);
		panelBattle.add(lblFoesPokemon);
		
		JLabel lblHp_1 = new JLabel("HP");
		lblHp_1.setBounds(0, 36, 46, 14);
		panelBattle.add(lblHp_1);
		
		JLabel lblAttack_2 = new JLabel("Attack");
		lblAttack_2.setBounds(0, 61, 70, 14);
		panelBattle.add(lblAttack_2);
		
		JLabel lblDefense_2 = new JLabel("Defense");
		lblDefense_2.setBounds(0, 86, 70, 14);
		panelBattle.add(lblDefense_2);
		
		JLabel labelUserPokemonHP = new JLabel("");
		labelUserPokemonHP.setForeground(Color.BLACK);
		labelUserPokemonHP.setBounds(102, 36, 46, 14);
		panelBattle.add(labelUserPokemonHP);
		
		JLabel labelFoePokemonHP = new JLabel("");
		labelFoePokemonHP.setBounds(276, 36, 46, 14);
		panelBattle.add(labelFoePokemonHP);
		
		JLabel labelUserPokemonAttack = new JLabel("");
		labelUserPokemonAttack.setBounds(102, 61, 46, 14);
		panelBattle.add(labelUserPokemonAttack);
		
		JLabel labelFoePokemonAttack = new JLabel("");
		labelFoePokemonAttack.setBounds(276, 61, 46, 14);
		panelBattle.add(labelFoePokemonAttack);
		
		JLabel labelUserPokemonDefense = new JLabel("");
		labelUserPokemonDefense.setBounds(102, 86, 46, 14);
		panelBattle.add(labelUserPokemonDefense);
		
		JLabel labelFoePokemonDefense = new JLabel("");
		labelFoePokemonDefense.setBounds(276, 86, 46, 14);
		panelBattle.add(labelFoePokemonDefense);
		
		JLabel lblVs_2 = new JLabel("vs.");
		lblVs_2.setBounds(204, 11, 46, 14);
		panelBattle.add(lblVs_2);
		
		JTextPane textPaneBattle = new JTextPane();
		textPaneBattle.setBounds(10, 125, 488, 180);
		panelBattle.add(textPaneBattle);
		
		JButton btnNext_1 = new JButton("Next >");
		btnNext_1.setBounds(409, 300, 89, 23);
		panelPokemon.add(btnNext_1);

		JButton btnSeeTheReslt = new JButton("See the Result");

		btnSeeTheReslt.setBounds(370, 311, 128, 23);
		panelBattle.add(btnSeeTheReslt);
				
		JButton btnStartBattle = new JButton("Start Battle");

		btnStartBattle.setBounds(20, 311, 128, 23);
		panelBattle.add(btnStartBattle);
		
		btnNext.addActionListener(new ActionListener() {//when the user tries to move to pokemon panel
			public void actionPerformed(ActionEvent e) {
				if(selectGender == false)//check if user selected his/her gender
					lblYouDidntSelect.setVisible(true);
				else
				{
					String name = textField.getText();
					if(name.length()==0)//check if the user typed his/her name
						lblNameError_1.setVisible(true);
					else {
						lblNameError_1.setVisible(false);
						boolean nameCheckContainingNumbers = UserSetting.validateName(name);//check if the user's name contains numbers
						if(nameCheckContainingNumbers)
							lblNameError.setVisible(true);
						else
						{
							userName = lblYourName.getText();
							User User = new User(userName,userGender,1,50);//make user object based on the information we've got so far
							lblNameError.setVisible(false);
							panelUser.setVisible(false);
							panelPokemon.setVisible(true);//move to the pokemon panel
							foePokemonNum = PokemonSetting.selectSpecies();//assigning foe's pokemon since we need to show it on next panel
							String [] foePokemonTypeAndSpecies = PokemonSetting.findTypeAndSpecies(foePokemonNum);//pick the type and species for the foe's pokemon
							foePokemonType = foePokemonTypeAndSpecies[1];
							foePokemonSpecies = foePokemonTypeAndSpecies[0];
							int[] foePokemonStats = PokemonSetting.pickStat();//pick the stats for the foe's pokemon
							foePokemonHP = foePokemonStats[0];
							foePokemonAttack = foePokemonStats[1];
							foePokemonDefense = foePokemonStats[2];
							labelFoePokemon.setIcon(pokemonImages[foePokemonNum]);//set the foe pokemon's image
							Pokemon foePokemon = new Pokemon(foePokemonSpecies,foePokemonType,foePokemonHP,foePokemonAttack,foePokemonDefense);//making a foe's pokemon
							Pokemon userPokemon = new Pokemon(null,null,0,0,0);//initiate user's pokemon: needed to use when we have battles more than one.
							lblHPStat.setText(null);
							lblAttackStat.setText(null);
							lblDefenseStat.setText(null);
							selectStats = false;
						}
					}
					
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//go back to the user panel
				panelPokemon.setVisible(false);
				panelUser.setVisible(true);
			}
		});
		
		btnNext_1.addActionListener(new ActionListener() {//try to move to the store panel
			public void actionPerformed(ActionEvent e) {
				if(selectPokemon)//check if the user selected his/her pokemon
				{
					if(selectStats)//check if the user selected pokemon's stats
					{
						panelPokemon.setVisible(false);
						panelStore.setVisible(true);
						labelUserGold.setText(String.valueOf(Store.loadGold(User)));
						Pokemon userPokemon = new Pokemon(userPokemonSpecies,userPokemonType,userPokemonHP,userPokemonAttack,userPokemonDefense);//make a pokemon again based on the information we have so far
						User.setMyPokemon(userPokemon);
						labelPokemonHP.setText(String.valueOf(userPokemon.getHp()));
						labelPokemonAttack.setText(String.valueOf(userPokemon.getAttack()));
						labelPokemonDefense.setText(String.valueOf(userPokemon.getDefense()));
						labelEffectHP.setVisible(false);
						labelEffectAttack.setVisible(false);		
						labelEffectDefense.setVisible(false);
						rdbtnNone.setSelected(true);
						
					}
					else
						lblYouNeedTo_1.setVisible(true);
				}
				else			
					lblYouNeedTo.setVisible(true);
			}
		});
		
		btnBack_1.addActionListener(new ActionListener() {//move back to the pokemon panel
			public void actionPerformed(ActionEvent e) {
				panelPokemon.setVisible(true);
				panelStore.setVisible(false);
			}
		});
		
		JButton btnGoToBattle = new JButton("Go To Battle");//move to the battle panel
		btnGoToBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean affordableItem;//variable to check if user is affordable to that item
				affordableItem = Store.pickItem(User, pickItemName);
				if(affordableItem)
				{
					panelStore.setVisible(false);
					panelBattle.setVisible(true);
					Pokemon userPokemon = new Pokemon(userPokemonSpecies,userPokemonType,userPokemonHP,userPokemonAttack,userPokemonDefense);//make a pokemon again - we need to do that, if not, the pokemon would be empty
					Pokemon foePokemon = new Pokemon(foePokemonSpecies,foePokemonType,foePokemonHP,foePokemonAttack,foePokemonDefense);//make a foe's pokemon again - we need to do that, if not, the pokemon would be empty 
					Store.applyEffect(userPokemon, pickItemName);//apply the effect because of the item user picked
					userPokemonHP = userPokemon.getHp();//get the new stat from the pokemon and display it to the label on the battle panel
					userPokemonAttack = userPokemon.getAttack();
					userPokemonDefense = userPokemon.getDefense();
					labelUserPokemonHP.setText(String.valueOf(userPokemon.getHp()));
					labelUserPokemonAttack.setText(String.valueOf(userPokemon.getAttack()));
					labelUserPokemonDefense.setText(String.valueOf(userPokemon.getDefense()));
					labelFoePokemonHP.setText(String.valueOf(foePokemon.getHp()));
					labelFoePokemonAttack.setText(String.valueOf(foePokemon.getAttack()));
					labelFoePokemonDefense.setText(String.valueOf(foePokemon.getDefense()));	
					btnStartBattle.setVisible(true);
					btnSeeTheReslt.setVisible(false);
					textPaneBattle.setText(null);
					labelFoePokemonHP.setForeground(Color.BLACK);
					labelUserPokemonHP.setForeground(Color.BLACK);
				}
				else
					lblSorryYouDont.setVisible(true);
				
				
			}
		});
		btnGoToBattle.setBounds(379, 300, 119, 23);
		panelStore.add(btnGoToBattle);
		
		btnStartBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pokemon userPokemon = new Pokemon(userPokemonSpecies,userPokemonType,userPokemonHP,userPokemonAttack,userPokemonDefense);//make a pokemon again - we need to do that, if not, the pokemon would be empty
				Pokemon foePokemon = new Pokemon(foePokemonSpecies,foePokemonType,foePokemonHP,foePokemonAttack,foePokemonDefense);//make a foe's pokemon again - we need to do that, if not, the pokemon would be empty
				Foe1.setMyPokemon(foePokemon);
				User.setMyPokemon(userPokemon);
				Timer timer = new Timer();//use timer
				TimerTask timerTask = new TimerTask() {
				String newline = System.getProperty("line.separator");//this variable to use when we set the text for commentary
				String battleCommentary;
				
					int counter = 1;
					@Override//inheriting from superclass
					public void run() {
						//every time the timer says so, write a number to console
						if (counter == 11)//when 11, we're done
						{
							//stop the timer
							timer.cancel();
							timer.purge();
							btnSeeTheReslt.setVisible(true);//prevent the user from click the result button because it might have effect
							return;//return keyword does: jumps out from the method
						}
						String moveResult = Battle.move(userPokemon,foePokemon,counter);//move the pokemon and get the commentary
						if(moveResult != "ERROR")
							try {
								if(this.battleCommentary == null)//first line doesn't need the line breaker
									this.battleCommentary = moveResult;
								else
									this.battleCommentary = battleCommentary + newline + moveResult;//if it's not the first line, we need the line breaker
								textPaneBattle.setText(this.battleCommentary);
								int previousUserPokemonHP = Integer.parseInt(labelUserPokemonHP.getText());
								int previousFoePokemonHP = Integer.parseInt(labelFoePokemonHP.getText());
								userPokemonHP = userPokemon.getHp();
								foePokemonHP = foePokemon.getHp();
								labelUserPokemonHP.setText(String.valueOf(userPokemonHP));
								labelFoePokemonHP.setText(String.valueOf(foePokemonHP));
								if(previousUserPokemonHP > userPokemonHP) {//if the HP lowers because of move, let's set the text color as RED
									labelUserPokemonHP.setForeground(Color.RED);
									labelFoePokemonHP.setForeground(Color.BLACK);//if not, let's set it as BLACK
								}									
								else
									labelUserPokemonHP.setForeground(Color.BLACK);
								if(previousFoePokemonHP > foePokemonHP) {
									labelFoePokemonHP.setForeground(Color.RED);
									labelUserPokemonHP.setForeground(Color.BLACK);
								}									
								else
									labelFoePokemonHP.setForeground(Color.BLACK);
								if(Integer.compare(userPokemonHP,0) == 0 || Integer.compare(foePokemonHP,0) == 0)//if either of pokemons get fainted, stop the battle.
									counter = 10;
							}
							catch(Exception e) { System.out.println(e); }
						counter++;
						int userPokemonHP = Integer.parseInt(labelUserPokemonHP.getText());
						int foePokemonHP = Integer.parseInt(labelFoePokemonHP.getText());						
					}
				};//Think about the array! We gave the value into the array with {}.
				
				//Assign our timertask to a schedule
				timer.schedule(timerTask, 0, 1000);//in milliseconds
				btnStartBattle.setVisible(false);
			}
		});
		
		btnSeeTheReslt.addActionListener(new ActionListener() {//When we try to get the result of the battle
			public void actionPerformed(ActionEvent e) {
				int userPokemonHP = Integer.parseInt(labelUserPokemonHP.getText());//get the pokemon's HP
				int foePokemonHP = Integer.parseInt(labelFoePokemonHP.getText());//get the pokemon's HP
				Pokemon userPokemon = new Pokemon(userPokemonSpecies,userPokemonType,userPokemonHP,userPokemonAttack,userPokemonDefense);//make a pokemon again, if we don't the user will always lose half of his/her gold  
				Pokemon foePokemon = new Pokemon(foePokemonSpecies,foePokemonType,foePokemonHP,foePokemonAttack,foePokemonDefense);//make a pokemon again, the result of battle might be weird
				String battleResult = Battle.result(User, userPokemon, foePokemon);//get the result
				JOptionPane.showMessageDialog(null, battleResult);//make a new dialog window for result
				Object[] options = {"One More Battle", "Quit Game"}; 
				int response = JOptionPane.showOptionDialog(null, "Do You Want to Play More Battles?", "Another Battle?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);//ask the user if s/he wants to play more
				if (response == 0)
				{
					panelBattle.setVisible(false);
					panelUser.setVisible(true);//go back to user panel
					
				}
				else
					System.exit(0);//end the program
				
			}
		});
		
		
		
	}
	
}
