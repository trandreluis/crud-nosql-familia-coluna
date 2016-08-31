package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class CpfDuplicadoException extends Exception {

	public CpfDuplicadoException() {
		super("CPF duplicado!");
	}
	
}

