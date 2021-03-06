package plic.repint;

public class SymboleTableau extends Symbole {
	private final int taille;

	public SymboleTableau(int taille) {
		this.taille = taille;
		this.setDepl(taille);
	}

	public void setDepl(int cptDepl) {
		this.deplacement = cptDepl;
	}

	public int getDepl() {
		return deplacement;
	}

	public int getTaille() {
		return taille;
	}

	public String getType() {
		return "tableau";
	}

	public String toString() {
		return getType() + " " + super.deplacement;
	}
}
