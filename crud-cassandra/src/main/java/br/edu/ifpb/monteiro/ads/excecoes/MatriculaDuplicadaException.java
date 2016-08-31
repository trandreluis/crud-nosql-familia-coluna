package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class MatriculaDuplicadaException extends Exception {

	public MatriculaDuplicadaException() {
		super("Matrícula duplicada!");
	}
	
}
