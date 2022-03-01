package rand;
import java.util.Scanner;

/*
 * Simulator program made by Luis Ruiz
 * Simulates an unbox simulator with legendary riser and granted legendary
 * 
 * */

public class LuckTest {

	private Scanner sc;
	
	private void start() {
		
		sc = new Scanner(System.in);
		
		// Assignable but no variable
		final float ch_common = 0.603f;				// 60.3%
		final float ch_rare = 0.34f;				// 34%
		final float ch_epic = 0.05f;				// 5%
		final float ch_legendary = 0.007f;			// 0.7%
		final float ch_leg_rater = 0.0015f;			// +0.015%
		final int ch_leg_assegurate = 30;			// 100% granted
		
		// Do the chance checksum
		// Yellow in eclipse bc we are making a sum with final values
		if (ch_common + ch_rare + ch_epic + ch_legendary != 1f) 
			{ System.out.print("Value error!"); System.exit(0); }
		
		// Variables
		float ch_common_decr = ch_common, ch_legendary_inc = ch_legendary, ch_rare_v = ch_rare, ch_epic_v = ch_epic;
		int leg = 0, legCheck = leg, epic = 0, rar = 0, com = 0, leg_grant = ch_leg_assegurate;
		
		// Text
		System.out.println("Common 		: " + ch_common * 100 + "%");
		System.out.println("Rare 		: " + ch_rare * 100 + "%");
		System.out.println("Epic 		: " + ch_epic * 100 + "%");
		System.out.println("Legendary 	: " + Math.floor(ch_legendary * 10000) / 100 + "%");
		System.out.println("Legendary rises to " + ch_leg_rater * 100 + "% every pack. Once you get one it resets.");
		System.out.println("To make it even more fair every " + ch_leg_assegurate +
			" packs opened you get one legendary granted\nif you didn't get one before.");
		System.out.println("------------");
		
		
		// Questions
		System.out.print("Introduce your packs: ");
		int packs = sc.nextInt();
		System.out.print("Introduce your quantity objects in one pack: ");
		int objects = sc.nextInt(), totObj = objects * packs;
		System.out.println("\n\tTotal objects are " + packs + "\n");
		String print = null;
		boolean showPrint = false, newPack = false;
		do {
			
			System.out.print("Print values? ");
			print = sc.next();
			if (print.equalsIgnoreCase("y") || print.equalsIgnoreCase("yes"))
				{ showPrint = true; System.out.println("------------"); }
			
		} while (!print.equalsIgnoreCase("y") && !print.equalsIgnoreCase("yes") &&
			!print.equalsIgnoreCase("n") && !print.equalsIgnoreCase("no"));
		
		// Algorithm
		do {
			
			if(showPrint) { 
				if(totObj % objects == 0) {
					
					System.out.printf("Pack %-4d	Object %-4d ", packs--, totObj--);
					newPack = true;
					
				} else
					System.out.printf("		Object %-4d ", totObj--);
			}
			
			float ch = (float) (Math.random());
			
			if(ch <= ch_legendary_inc)
				{ leg++; if(showPrint) System.out.printf("Chance of %.4f (Legendary)", 	ch); }
			else if(ch <= ch_epic_v + ch_legendary_inc)
				{ epic++; if(showPrint) System.out.printf("Chance of %.4f (Epic)	", 	ch); }
			else if(ch <= ch_rare_v + ch_epic_v + ch_legendary_inc)
				{ rar++; if(showPrint) System.out.printf("Chance of %.4f (Rare)	", 	ch); }
			else if(ch <= ch_common_decr + ch_rare_v + ch_epic_v + ch_legendary_inc)
				{ com++; if(showPrint) System.out.printf("Chance of %.4f (Common)	", 	ch); }
			
			if(showPrint) System.out.printf("	Legendary chance riser %.4f ", ch_legendary_inc);
			
			if (newPack)
				if (leg_grant != 2) {
					
					// Less common possibilities and more legendary possibilities
					if(leg == legCheck) { ch_common_decr -= ch_leg_rater; ch_legendary_inc += ch_leg_rater; }
					// Reset defaults on common, legendary and legendary granted
					else { legCheck++; ch_common_decr = ch_common; ch_legendary_inc = ch_legendary; leg_grant = ch_leg_assegurate; }
					// Define to default in case it's 0 due legendary granted
					ch_rare_v = ch_rare; ch_epic_v = ch_epic;
					if(showPrint) System.out.printf("	Assegurated in %2d%n", leg_grant--);
					
				} else { 
					
					leg_grant = ch_leg_assegurate; ch_rare_v = 0f; ch_epic_v = 0f; ch_common_decr = 0f; ch_legendary_inc = 1f; 
					if(showPrint) System.out.println("	Assegurated in  2");
					
				}
			
			else { System.out.println(); ch_rare_v = ch_rare; ch_epic_v = ch_epic; ch_common_decr = ch_common; ch_legendary_inc = ch_legendary; }
			newPack = false;
			
		} while (totObj != 0);
		
		// Results
		System.out.println("------------");
		System.out.println("Legendary	x" + leg);
		System.out.println("Epic		x" + epic);
		System.out.println("Rare		x" + rar);
		System.out.println("Common		x" + com);
		
		System.out.println("\n\tPress enter to continue...\n");
		try { System.in.read(); LuckTest lt = new LuckTest(); lt.start(); }
		catch (Exception e) {}
		
	}
	
	public static void main(String[] args) {
		
		LuckTest lt = new LuckTest();
		lt.start(); // start program

	}

}
