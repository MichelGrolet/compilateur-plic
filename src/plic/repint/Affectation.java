package plic.repint;

public class Affectation extends Instruction {

	/**
	 * acces : nom de (la variable / emplacement de tableau) à affecter
	 */
	private final Acces acces;

	/**
	 * expr : valeur de l'affectation
	 */
	private final Expression expr;

	public Affectation(Acces a, Expression expr) {
		this.acces = a;
		this.expr = expr;
	}

	@Override
	public String toString() {
		return acces.toString() + " := " + expr;
	}

	/**
	 * Vérifie que l'affectation se fait sur une variable existante
	 */
	@Override
	public void verifier() {
		acces.verifier();
		expr.verifier();
		// il faut avoir des entiers à gauche et à droite (pas de tableau mais tab[i] ou alors directement un entier)

	}

	@Override
	public String toMips() throws RuntimeException {
		String mips = "# Affectation " + this + "\n";
		// réservation de l'espace mémoire
		mips += "add $sp, $sp, -4\n";
		// assignation de la valeur de l'expression à $v0
		mips += this.expr.toMips();
		// récupération de l'adresse de la variable dans TDS
		this.verifier();
		Entree e = new Entree(this.acces.toString());
		Symbole s = TDS.getInstance().identifier(e);
		System.out.println(s);
		int empl = s.getDepl() - 4;
		mips += "sw $v0, " + empl + "($s7)\n";
		return mips;
	}
}
