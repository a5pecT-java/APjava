package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Game.Player.ClassTypes;

public class Game
{
	static Scanner scanner = new Scanner(System.in);
	static Player userPlayer;
	boolean playing = true;

	final static String filePath = "C:\\Users\\DanDa\\Documents\\GitHub\\APjava\\Javamon\\src\\Game\\Players.txt";

	public void Run()
	{
		printTitle();

		while (playing)
		{
			int menuChoice = printMainMenu();
			checkMenuChoice(menuChoice);
		}
	}

	private void printTitle()
	{
		System.out.println("Welcome to the Infinity Dungeon!");
	}

	private int printMainMenu()
	{
		System.out.println("1. Create a Character!");
		System.out.println("2. Load a Character!");
		System.out.println("3. Check your Stats!");
		System.out.println("4. Goto Battle!");
		System.out.println("5. Save");
		System.out.println("6. Quit");
		System.out.print("Choice: ");
		int menuChoice = scanner.nextInt();
		return menuChoice;
	}

	private void checkMenuChoice(int menuChoice)
	{
		switch (menuChoice)
		{
		case 1: // 1. Create a Character!
			{
				createPlayer();
				break;
			}
		case 2: // 2. Load a Character!"
			{
				Load.loadPlayer(scanner, userPlayer, filePath);
				break;
			}
		case 3: // 3. Check your Stats!
			{
				checkStats();
				break;
			}
		case 4: // 4. Go and Battle!
			{
				userPlayer.getExp();
				battle(callCreateMonster(), userPlayer);
				break;
			}
		case 5: // 5. Save and Quit
			{
				Save.savePlayer(userPlayer, filePath);
				break;
			}
		case 6: // 6. Quit
			{
				playing = false;
				break;
			}
		}
	}

	private int attack(int monsterHP, int monsterAttack, String monster, boolean monsterDead)
	{
		int attack = userPlayer.getAttack();
		int health = userPlayer.getHealth();
		if (monsterHP > 0)
		{
			monsterHP -= attack;
			System.out.println("You did " + attack + " damage, the " + monster + "'s HP is " + monsterHP);
		} else if (monsterHP < 0)
		{
			System.out.println("You killed the " + monster + "!");
			monsterHP = 0;
			monsterDead = true;
		}
		if (health > 0)
		{
			health -= monsterAttack;
			System.out.println("The monster did " + monsterAttack + " damage, your HP is " + health);
		} else
		{
			System.out.println("You died!");
		}
		return monsterHP;
	}

	private static int battleMenu()
	{
		System.out.println("1. Attack");
		System.out.println("2. Check Monster Stats");
		System.out.println("3. Leave Battle");
		System.out.print("Choice: ");
		int choice = scanner.nextInt();
		return choice;
	}

	private void battle(String monster, Player userPlayer)
	{
		System.out.println();
		// Amount of XP you get from each monster.
		int lvl1 = (int) (Math.random() * 10 + 15);
		int lvl2 = (int) (Math.random() * 20 + 15);
		int lvl3 = (int) (Math.random() * 30 + 20);
		int lvl4 = (int) (Math.random() * 40 + 25);
		int lvl5 = (int) (Math.random() * 50 + 30);
		double XP = userPlayer.getExp();
		int level = 1;
		if (XP < 100)
		{
			int xpBar1 = 200;
			System.out.println(userPlayer.getName() + "'s Level: " + level);
			System.out.print("EXP BAR: ");
			System.out.println("[" + XP + "/" + xpBar1 + "]");
		} else if (XP > 100 && XP < 200)
		{
			int xpBar2 = 275;
			level++;
			System.out.println(userPlayer.getName() + "'s Level: " + level);
			System.out.print("EXP BAR: ");
			System.out.println("[" + XP + "/" + xpBar2 + "]");
		} else if (XP > 200 && XP < 275)
		{
			int xpBar3 = 325;
			level++;
			System.out.println(userPlayer.getName() + "'s Level: " + level);
			System.out.print("EXP BAR: ");
			System.out.println("[" + XP + "/" + xpBar3 + "]");
		} else if (XP > 275 && XP < 325)
		{
			int xpBar4 = 500;
			level++;
			System.out.println(userPlayer.getName() + "'s Level: " + level);
			System.out.print("EXP BAR: ");
			System.out.println("[" + XP + "/" + xpBar4 + "]");
		} else
		{
			level++;
			System.out.println(userPlayer.getName() + "'s Level: " + level);
			System.out.print("EXP BAR: ");
			System.out.println("[MAX LEVEL]");
		}
		boolean battling = true;
		int monsterHealth = 0;
		int monsterLevel = 0;
		int monsterAttack = 0;
		while (battling)
		{
			switch (monster)
			{
			// Level Ones
			case "Undead Corpse":
				{
					monsterLevel = 1;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 60;

					int mAttack = (int) (Math.random() * 10 + 20);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl1);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Rat":
				{
					monsterLevel = 1;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();

						break;
					}
					monsterHealth = 15;
					int mAttack = (int) (Math.random() * 10 + 20);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl1);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Slime":
				{
					monsterLevel = 1;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 60;
					int mAttack = (int) (Math.random() * 10 + 20);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl1);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Giant Leech":
				{
					monsterLevel = 1;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 25;
					int mAttack = (int) (Math.random() * 10 + 20);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl1);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Skeleton":
				{
					monsterLevel = 1;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 50;
					int mAttack = (int) (Math.random() * 10 + 20);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl1);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			// Level Twos
			case "Lich":
				{
					monsterLevel = 2;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 100;
					int mAttack = (int) (Math.random() * 10 + 20);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl2);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Skeleton Archer":
				{
					monsterLevel = 2;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 30;
					int mAttack = (int) (Math.random() * 10 + 20);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl2);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Rust Monster":
				{
					monsterLevel = 2;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 140;
					int mAttack = (int) (Math.random() * 10 + 25);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl2);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Ghoul":
				{
					monsterLevel = 2;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 120;
					int mAttack = (int) (Math.random() * 10 + 25);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl2);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Blood Hound":
				{
					monsterLevel = 2;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 80;
					int mAttack = (int) (Math.random() * 10 + 25);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl2);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			// Level Threes
			case "Orc":
				{
					monsterLevel = 3;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 200;
					int mAttack = (int) (Math.random() * 10 + 40);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl3);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Skeleton Warrior":
				{
					monsterLevel = 3;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 135;
					int mAttack = (int) (Math.random() * 10 + 40);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl3);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Giant":
				{
					monsterLevel = 3;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 250;
					int mAttack = (int) (Math.random() * 10 + 60);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl3);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Dark Mage":
				{
					monsterLevel = 3;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 110;
					int mAttack = (int) (Math.random() * 10 + 40);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl3);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Stone Giant":
				{
					monsterLevel = 3;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 400;
					int mAttack = (int) (Math.random() * 10 + 50);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl3);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			// Level Fours
			case "Vampire":
				{
					monsterLevel = 4;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 90;
					int mAttack = (int) (Math.random() * 10 + 40);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl4);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Corrupted Hero":
				{
					monsterLevel = 4;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 120;
					int mAttack = (int) (Math.random() * 10 + 40);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}
								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl4);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Elemental":
				{
					monsterLevel = 4;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 40;
					int mAttack = (int) (Math.random() * 10 + 40);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl4);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Mimic":
				{
					monsterLevel = 4;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 300;
					int mAttack = (int) (Math.random() * 10 + 40);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl4);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			// Level Fives
			case "Demon":
				{
					monsterLevel = 5;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 400;
					int mAttack = (int) (Math.random() * 40 + 50);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl5);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			case "Dragon":
				{
					monsterLevel = 5;
					if (monsterLevel > level)
					{
						monster = callCreateMonster();
						break;
					}
					monsterHealth = 700;
					int mAttack = (int) (Math.random() * 60 + 70);
					if (userPlayer.getDefense() > mAttack)
						monsterAttack = mAttack / 2;
					monsterAttack = mAttack;
					System.out.println("You're fighting a " + monster + " and it has " + monsterHealth + "HP");
					boolean monsterDead = false;
					while (monsterDead == false)
					{
						int battleMenuChoice = battleMenu();
						switch (battleMenuChoice)
						{
						case 1: // Attack
							{
								monsterHealth = attack(monsterHealth, monsterAttack, monster, monsterDead);
								if (monsterHealth < 0)
								{
									System.out.println("You gained " + lvl1 + "EXP");
									userPlayer.giveExp(lvl1);
									monsterDead = true;
								}

								break;
							}
						case 2: // Check Monster Stats
							{
								System.out.println(
										monster + ": \nAttack [" + monsterAttack + "]\nHealth [" + monsterHealth + "]");

								System.out.println("Possible EXP: " + lvl5);
								break;
							}
						case 3: // Leave Battle
							{
								Save.savePlayer(userPlayer, filePath);
								battling = false;
								printMainMenu();
								break;
							}
						}
					}
					break;
				}
			}
		}
	}

	private static String callCreateMonster()
	{
		try
		{
			return monsterSelector();
		} catch (IOException e)
		{
			System.out.println("didn't reach it");
			e.printStackTrace();
			try
			{
				return monsterSelector();
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	private static String monsterSelector() throws IOException
	{
		String monster = "";
		FileReader fr = new FileReader("C:\\Users\\DanDa\\Documents\\GitHub\\APjava\\Javamon\\src\\Game\\Monsters");
		BufferedReader ReadFileBuffer = new BufferedReader(fr);
//		File monsterSelect = new File("Monsters");
//		BufferedReader c1 = new BufferedReader(new FileReader(monsterSelect));
		int random = (int) (Math.random() * 21 + 1);
		for (int i = 0; i < random; i++)
			monster = ReadFileBuffer.readLine();
		fr.close();
		return monster;
	}

	private void createPlayer()
	{
		scanner.nextLine();
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		System.out.println();

		ClassTypes selectedClassType = selectClass(name);
		pickStats(name, selectedClassType);
	}

	private ClassTypes selectClass(String name)
	{
		System.out.println("What class of character do you want " + name + "?");
		System.out.println("1. Knight");
		System.out.println("2. Rouge");
		System.out.println("3. Wizard");
		System.out.println("4. Deity");
		System.out.print("Choice: ");

		int choice = scanner.nextInt();

		while (choice < 1 || choice > 4)
		{
			System.err.print("Invalid Range: \"Try again\"");
			choice = scanner.nextInt();
		}

		ClassTypes classType = getClassTypeFromInt(choice);
		System.out.println();

		return classType;
	}

	private ClassTypes getClassTypeFromInt(int choice)
	{
		switch (choice)
		{
		case 1:
			return ClassTypes.Knight;
		case 2:
			return ClassTypes.Rouge;
		case 3:
			return ClassTypes.Wizard;
		case 4:
			return ClassTypes.Deity;
		default:
			return ClassTypes.Knight;
		}
	}

	private void pickStats(String name, ClassTypes selectedClassType)
	{
		System.out.println("Pick attack, defense, and agility stats.");
		System.out.println("You get 6 points to distribute among the three.");
		int attack, defense, agility;
		do
		{
			System.out.print("Enter Attack stat: ");
			attack = scanner.nextInt();
			System.out.print("Enter Defense stat: ");
			defense = scanner.nextInt();
			System.out.print("Enter Agility stat: ");
			agility = scanner.nextInt();
		} while (attack + defense + agility > 6); // check for negatives
		System.out.println();

		Player newPlayer = null;

		switch (selectedClassType)
		{
		case Knight:
			{
				newPlayer = new Knight(name, 100, 15 + attack, 30 + defense, 8 + agility, 0.0, 0.0);
				System.out.println("You Picked Knight, you have more armor and a special attribute called Pride!");
				break;
			}
		case Rouge:
			{
				newPlayer = new Rouge(name, 80, 25 + attack, 10 + defense, 30 + agility, 0.0, 100.0);
				System.out.println(
						"You Picked Rouge, you have more agility and attack and a special attribute called Stamina!");
				break;
			}
		case Wizard:
			{
				newPlayer = new Wizard(name, 60, 40 + attack, 5 + defense, 15 + agility, 0.0, 100.0);
				System.out.println("You Picked Wizard, you have the most attack and a special attribute called Mana!");
				break;
			}
		case Deity:
			{
				newPlayer = new Deity("Lord " + name, 0.0);
				System.out.println("You're literally a God, unleash your wrath amongst the infinity tower!");
				break;
			}
		}

		userPlayer = newPlayer;
		System.out.println();
	}

	private void checkStats()
	{
		System.out.println(userPlayer.getStats());
		System.out.println();
	}
}