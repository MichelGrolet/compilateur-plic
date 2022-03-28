package plic.repint;

public class Ecrire extends Instruction {
	private final Expression expr;

	/**
	 * Code mips pour aller à la ligne
	 */
	public final String sautdeligne = "\n# saut de ligne\n" + "li $v0, 4\n" + "la $a0, str\n" + "syscall\n";

	public Ecrire(Expression expr) {
		this.expr = expr;
	}

	@Override
	public String toString() {
		return expr.toString();
	}

	@Override
	public void verifier() {
		System.out.println("Verification de l'instruction ecrire : " + this);
		Entree e = new Entree(this.expr.toString());
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null) throw new RuntimeException("ecrire : l'identificateur " + this.expr + " n'existe pas");
	}

	@Override
	public String toMips() {
		String mips = "# Ecrire " + this + "\n";
		Entree e = new Entree(this.expr.toString());
		Symbole s = TDS.getInstance().identifier(e);
		int adresse = s.getDepl() - 4;
		mips += "li $v0, 1\n";
		mips += "lw $a0, " + adresse + "($s7)\n";
		mips += "syscall\n";
		mips += this.sautdeligne;
		return mips;
	}
}
