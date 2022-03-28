package plic.repint;

public class SymboleEntier extends Symbole {
	public SymboleEntier() {
	}

	public String getType() {
		return "entier";
	}

	public String toString() {
		return getType() + " " + super.deplacement;
	}
}
