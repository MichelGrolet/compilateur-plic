package plic.repint;

/**
 * Acces :
 * - soit le nom d'une variable : var
 * - soit le nom d'un tableau suivi d'un emplacement : tab[2]
 */
public abstract class Acces extends Expression {
	public abstract String getType();

	public abstract void verifier();

	public abstract String toMips();

	public abstract int getAdresse();
}
