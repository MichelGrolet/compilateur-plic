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
}
