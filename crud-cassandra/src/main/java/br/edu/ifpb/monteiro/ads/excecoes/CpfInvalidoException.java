package br.edu.ifpb.monteiro.ads.excecoes;

@SuppressWarnings("serial")
public class CpfInvalidoException extends Exception {

	public CpfInvalidoException() {
		super("CPF inválido!");
	}
}

