package plic.repint;

import java.util.ArrayList;

/**
 * Bloc : stocke les instructions d'un programme
 */
public class Bloc {
	private final ArrayList<Instruction> instructions = new ArrayList<>();

	public void ajouterInstruction(Instruction i) {
		this.instructions.add(i);
		System.out.println("#### BLOC : ajout de l'instruction " + i.toString());
	}

	public void verifier() {
		instructions.forEach(Instruction::verifier);
	}
}
