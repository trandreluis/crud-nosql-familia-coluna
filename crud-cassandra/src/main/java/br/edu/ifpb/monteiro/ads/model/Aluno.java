package br.edu.ifpb.monteiro.ads.model;

/**
 * Classe temporária para persistencia inicial
 * 
 * @author André
 *
 */

public class Aluno {

	private String nome;
	private String matricula;
	private String curso;
	private String cpf;
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", matricula=" + matricula + ", curso="
				+ curso + ", cpf=" + cpf + ", telefone=" + telefone + "]";
	}
	
}