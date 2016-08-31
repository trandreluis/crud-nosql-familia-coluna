package br.edu.ifpb.monteiro.ads.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.ifpb.monteiro.ads.dao.AlunoDao;
import br.edu.ifpb.monteiro.ads.excecoes.AlunoInexistenteException;
import br.edu.ifpb.monteiro.ads.model.Aluno;
import br.edu.ifpb.monteiro.ads.view.TableModelAluno;

public class OuvinteBuscar implements ActionListener{
	
	private JFrame janela;
	private JTable tabela;
	private JTextField textFieldNormal;
	private AlunoDao dao;
	
	public OuvinteBuscar(JFrame janela, JTable tabela, JTextField textFieldNormal, AlunoDao dao) {
		this.janela = janela;
		this.tabela = tabela;
		this.textFieldNormal = textFieldNormal;
		this.dao = dao;
	}

	public void actionPerformed(ActionEvent e) {
		String valor = null;
		if(textFieldNormal.getText() != null && !textFieldNormal.getText().equals("")){
			valor = textFieldNormal.getText();
		}
			
		try {
			ArrayList<Aluno> alunos = new ArrayList<Aluno>();
			alunos.add(dao.consultar(valor));
			tabela.setModel(new TableModelAluno(alunos));
			janela.setVisible(true);
		} catch (AlunoInexistenteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
