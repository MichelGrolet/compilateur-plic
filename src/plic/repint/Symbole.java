package plic.repint;

/**
 * Symbole :
 * - soit une variable
 * - soit un tableau
 */
public abstract class Symbole {
	protected int deplacement;

	public abstract String getType();

	public void setDepl(int cptDepl) {
		this.deplacement = cptDepl;
	}

	public int getDepl() {
		return deplacement;
	}

	@Override
	public abstract String toString();
}
