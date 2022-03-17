package plic.repint;

public class Ecrire extends Instruction {
	private Expression expr;

	public Ecrire(Expression expr) {
		this.expr = expr;
	}

	@Override
	public String toString() {
		return expr.toString();
	}

	@Override
	public void verifier() {

	}
}
