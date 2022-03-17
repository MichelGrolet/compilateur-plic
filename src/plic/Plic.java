package plic;

import plic.analyse.AnalyseurSyntaxique;
import plic.exception.*;
import plic.repint.Bloc;

import java.io.File;

public class Plic {
	public static void main(String[] args) {
		try {
			new Plic(args[0]);
		} catch (Exception e) {
			System.out.println("ERREUR: " + e.getMessage());
		}
	}

	public Plic(String path) throws ErreurSyntaxique, DoubleDeclaration {
		File file = new File(path);
		AnalyseurSyntaxique analyseurSyntaxique = new AnalyseurSyntaxique(file);
		Bloc bloc = analyseurSyntaxique.analyse();
		bloc.verifier();
		// MIPS
		String code = bloc.toMips();
		System.out.println("\n # fichier compilé depuis PLIC\n # @author : michel grolet\n\n" + code + "#######################################");
	}
}
