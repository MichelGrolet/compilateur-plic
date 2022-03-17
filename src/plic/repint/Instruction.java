package plic.repint;


/**
 * Instruction :
 * - Affectation
 * - Ecrire
 */
public abstract class Instruction {
	public Instruction() {

	}

	public abstract String toString();

	public abstract void verifier();

	public abstract String toMips();
}
