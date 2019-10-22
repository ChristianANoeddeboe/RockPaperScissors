package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Rockpaperscissors {
	
	private static boolean back = false;
	private static Scanner scanner = new Scanner(System.in);
	private static int pinput = 0;
	private static Type pchoice;
	private static Type bchoice;
	
	public enum Type {
		ROCK {
			@Override
			public boolean beats(Type other) {
				return other == SCISSOR;
			}
		}, PAPER {
			@Override
			public boolean beats(Type other) {
				return other == ROCK;
			}
		}, SCISSOR {
			@Override
			public boolean beats(Type other) {
				return other == PAPER;
			}
		};
		
		public abstract boolean beats(Type other);

		public static Type parseType(String string) {
			if (string.equals("1")) {
				return ROCK;
			} else if (string.equals("2")) {
				return PAPER;
			} else if (string.equals("3")) {
				return SCISSOR;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		boolean kill = false;
		System.out.println("      Welcome to Rock, Paper, Scissors!     ");
		while(!kill) {
			if (back) {
				System.out.println("   Welcome back to Rock, Paper, Scissors!   ");
				back = false;
			}
			System.out.println("---------------------------------------------");
			System.out.println("1. Bot vs. Bot  |  2. You vs. Bot  |  3. Exit");
			pinput = Integer.parseInt(scanner.next());
			switch(pinput) {
				case 1: 
					botvsbot();
					break;
				case 2:
					playervsbot();

					break;
				case 3:
					kill = true;
					break;
				}
			}
		}
	
	private static void botvsbot() {
		while(!back) {
			System.out.println("1. Play | 2. Play game 'x' times | 3. Back");
			pinput = Integer.parseInt(scanner.next());
			switch(pinput) {
			case 1: 
				try {
					pchoice = Type.parseType(bot());
					bchoice = Type.parseType(bot());
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Rock");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Paper");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Scissors");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Bot1: "+pchoice+" | Bot2: "+bchoice);
					TimeUnit.SECONDS.sleep(1);
					if (pchoice.equals(bchoice)) {
						System.out.println("Draw! Try again");
					} else if (pchoice.beats(bchoice)) {
						System.out.println("Bot1 wins!");
					} else {
						System.out.println("Bot2 wins!");
					}
					TimeUnit.SECONDS.sleep(1);
					break;
				} catch (InterruptedException e) {
					System.out.println("Shit");
					e.printStackTrace();
				}
			case 2:
				System.out.println(pinput);
				System.out.println("How many games should be played?");
				int times = Integer.parseInt(scanner.next());
				int win1 = 0;
				int win2 = 0;
				int draw = 0;
				System.out.println(times);
				for (int played = 0 ; played < times ; played++) {
					pchoice = Type.parseType(bot());
					bchoice = Type.parseType(bot());
					if (pchoice.equals(bchoice)) {
						draw++;
						System.out.println("Bot1: "+pchoice+" | Bot2: "+bchoice+" | Draw!");
					} else if (pchoice.beats(bchoice)) {
						win1++;
						System.out.println("Bot1: "+pchoice+" | Bot2: "+bchoice+" | Bot1 wins");
					} else {
						win2++;
						System.out.println("Bot1: "+pchoice+" | Bot2: "+bchoice+" | Bot2 wins");
					}
				}
				System.out.println("Bot 1 wins: "+win1+" | Bot 2 wins: "+win2+" | Draws: "+draw);
				break;
			case 3:
				back = true;
				break;
			}
		}
	}
	
	private static void playervsbot() {
		while(!back) {
			System.out.println("Choose your weapon!");
			System.out.println("1. Rock  |  2. Paper  |  3. Scissors  |  4. Back");
			pchoice = Type.parseType(scanner.next());
			bchoice = Type.parseType(bot());
			if ( pchoice == Type.parseType("4")) {
				back = true;
				break;
			} else {
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Rock");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Paper");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Scissors");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Player: "+pchoice+" | Bot: "+bchoice);
					TimeUnit.SECONDS.sleep(1);
					if (pchoice.equals(bchoice)) {
						System.out.println("Draw! Try again");
					} else if (pchoice.beats(bchoice)) {
						System.out.println("You win!");
					} else {
						System.out.println("Bot wins!");
					}
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					System.out.println("Shit");
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String bot() {
		Random random = new Random();
		int rand = random.nextInt(3)+1;
		return Integer.toString(rand);
	}
}
