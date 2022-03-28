package plic.repint;

import java.util.ArrayList;

/**
 * Bloc : stocke les instructions d'un programme
 */
public class Bloc {
	private final ArrayList<Instruction> instructions = new ArrayList<>();

	public void ajouterInstruction(Instruction i) {
		System.out.println("instruction ajoutée : " + i.toString());
		this.instructions.add(i);
	}

	public void verifier() {
		instructions.forEach(Instruction::verifier);
	}

	public String toMips() {
		StringBuilder sb = new StringBuilder();
		sb.append(".data\n");
		sb.append("str: .asciiz \"\\n\"\n");
		sb.append(".text\n");
		sb.append("main:\n");
		instructions.forEach(i -> sb.append(i.toMips()).append("\n"));
		return sb.toString();
	}
}
