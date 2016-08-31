package br.edu.ifpb.monteiro.ads.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpb.monteiro.ads.model.Aluno;

@SuppressWarnings("serial")
public class TableModelAluno extends AbstractTableModel{

	private List<Aluno> linhas;
	private String[] colunas = new String[]{"Matrícula", "Nome", "CPF", "Curso", "Telefone"};
	private final int matricula = 0, nome = 1, cpf = 2, curso = 3, telefone = 4;
	
	public TableModelAluno(List<Aluno> linhas){
		this.linhas = linhas;
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public Class<?> getColumnClass(int indiceColuna) {
		switch(indiceColuna){
		case matricula:
			return String.class;
			
		case nome:
			return String.class;
			
		case cpf:
			return String.class;
		
		case curso:
			return String.class;
			
		case telefone:
			return String.class;
			
		default:
			throw new IndexOutOfBoundsException("Índice da coluna fora dos limites");
		}
	}
	
	public Object getValueAt(int linha, int coluna) {
		Aluno aluno = linhas.get(linha);
		
		switch (coluna) {
		case matricula:
			return aluno.getMatricula();
	
		case nome:
			return aluno.getNome();
		
		case cpf:
			return aluno.getCpf();
		
		case curso:
			return aluno.getCurso();
				
		case telefone:
			return aluno.getTelefone();
			
		default:
			throw new IndexOutOfBoundsException("Índice da coluna fora dos limites");
		}
	}
	
	public Aluno getObjeto(int indiceLinha){
		return linhas.get(indiceLinha);
	}
	
	public void addObjeto(Aluno aluno){
		linhas.add(aluno);
		
		int ultimoElemento = getRowCount() - 1;
		
		fireTableRowsInserted(ultimoElemento, ultimoElemento);
	}
	
	public void remover(int indiceLinha){
		linhas.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
}