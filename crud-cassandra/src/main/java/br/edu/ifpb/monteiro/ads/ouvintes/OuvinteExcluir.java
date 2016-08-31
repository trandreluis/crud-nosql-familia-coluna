package br.edu.ifpb.monteiro.ads.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.edu.ifpb.monteiro.ads.dao.AlunoDao;
import br.edu.ifpb.monteiro.ads.execoes.AlunoInexistenteException;
import br.edu.ifpb.monteiro.ads.view.TableModelAluno;

public class OuvinteExcluir implements ActionListener{
	private JTable tabela; 
	private TableModelAluno modelTable;
	private AlunoDao dao;
	
	public OuvinteExcluir(JTable tabela, TableModelAluno modelTable, AlunoDao dao){
		this.tabela = tabela;
		this.dao = dao;
		this.modelTable = modelTable;
	}

	public void actionPerformed(ActionEvent e) {
		if(tabela.getSelectedRowCount() != 0){
			int linhaSelecionada = tabela.getSelectedRow();
				int opcao = 0;
				try {
					if(dao.consultar(tabela.getValueAt(linhaSelecionada, 0).toString()) != null){
						opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este aluno?");					
					}
				} catch (AlunoInexistenteException e4) {
					JOptionPane.showMessageDialog(null, e4.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				if(opcao == 0){
					try {
						dao.deletar(dao.consultar(tabela.getValueAt(linhaSelecionada, 0).toString()));
						modelTable.remover(linhaSelecionada);
						JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");
					} catch (AlunoInexistenteException e3) {
						JOptionPane.showMessageDialog(null, e3.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					return;
				}
		}
		else{
			JOptionPane.showMessageDialog(null, "Selecione a linha referente ao aluno que deseja excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
