package br.edu.ifpb.monteiro.ads.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.edu.ifpb.monteiro.ads.dao.AlunoDao;
import br.edu.ifpb.monteiro.ads.model.Aluno;
import br.edu.ifpb.monteiro.ads.view.PainelAddAluno;

public class OuvinteEditar implements ActionListener{

	private JFrame janela;
	private AlunoDao dao;
	private JTable tabela;
	
	public OuvinteEditar(JFrame janela, AlunoDao dao, JTable tabela){
		this.janela = janela;
		this.dao = dao;
		this.tabela = tabela;
	}
	public void actionPerformed(ActionEvent arg0) {
		Aluno aluno = new Aluno();
		if(tabela.getSelectedRowCount() != 0){
			int linhaSelecionada = tabela.getSelectedRow();
			aluno = new Aluno();
			aluno.setMatricula(tabela.getValueAt(linhaSelecionada, 0).toString());
			aluno.setNome(tabela.getValueAt(linhaSelecionada, 1).toString());
			aluno.setCpf(tabela.getValueAt(linhaSelecionada, 2).toString()); 
			aluno.setCurso(tabela.getValueAt(linhaSelecionada, 3).toString()); 
			aluno.setTelefone(tabela.getValueAt(linhaSelecionada, 4).toString());	
		}
		else{
			JOptionPane.showMessageDialog(null, "Selecione a linha referente ao aluno que deseja editar!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		janela.getContentPane().removeAll();
		new PainelAddAluno(janela, dao, aluno);
		janela.setVisible(true);
	}
}
