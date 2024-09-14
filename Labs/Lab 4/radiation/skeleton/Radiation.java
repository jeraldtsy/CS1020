/**
 * Name			:
 * Matric No.	:
 * PLab Acct.	:
 */

import java.util.*;

public class Radiation {

	public void run() {
		// implement your "main" method here...
	}

	public static void main(String[] args) {
		Radiation myChemicalElements = new Radiation();
		myChemicalElements.run();
	}
}

// hint for O(N) solution...
class Element {
	private int strength;
	private int yearsBeforeDecay;

	public Element(int strength, int yearsBeforeDecay) {
		this.strength = strength;
		this.yearsBeforeDecay = yearsBeforeDecay;
	}

	public int getStrength() {
		return this.strength;
	}

	public int getYearsBeforeDecay() {
		return this.yearsBeforeDecay;
	}
}
