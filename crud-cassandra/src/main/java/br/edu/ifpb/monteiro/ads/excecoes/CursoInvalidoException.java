package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class CursoInvalidoException extends Exception{
	
	public CursoInvalidoException() {
		super("Curso inválido!");
	}
}