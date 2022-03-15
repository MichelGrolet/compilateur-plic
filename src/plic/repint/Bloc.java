package plic.repint;

import java.util.ArrayList;

public class Bloc {
	private ArrayList<Instruction> instructions = new ArrayList<>();

	public void ajouterInstruction(Instruction i) {
		this.instructions.add(i);
	}
}
