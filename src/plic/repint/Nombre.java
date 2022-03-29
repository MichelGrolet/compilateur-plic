package plic.repint;

public class Nombre extends Expression {
	private final int val;

	public Nombre(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return Integer.toString(val);
	}

	@Override
	public void verifier() {}

	@Override
	public String getType() {
		return "entier";
	}

	@Override
	public String toMips() {
		return "li $v0, " + this.val + "\n";
	}
}
