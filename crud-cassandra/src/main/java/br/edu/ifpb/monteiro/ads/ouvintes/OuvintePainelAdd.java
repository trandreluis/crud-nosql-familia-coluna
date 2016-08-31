package br.edu.ifpb.monteiro.ads.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import br.edu.ifpb.monteiro.ads.dao.AlunoDao;
import br.edu.ifpb.monteiro.ads.view.PainelAddAluno;

public class OuvintePainelAdd implements ActionListener{

	private JFrame janela;
	private AlunoDao dao;
	
	public OuvintePainelAdd(JFrame janela, AlunoDao dao){
		this.janela = janela;
		this.dao = dao;
	}
	public void actionPerformed(ActionEvent arg0) {
		janela.getContentPane().removeAll();
		new PainelAddAluno(janela, dao, null);
		janela.setVisible(true);
	}
}