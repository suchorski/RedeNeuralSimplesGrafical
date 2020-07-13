package com.suchorski.redeneuralsimplesgrafical.graficos;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;

import com.suchorski.redeneuralsimples.objetos.RedeNeural;

public class RedeNeuralGrafical extends JFrame {
	
	private Grafico grafico;
	
	public RedeNeuralGrafical(RedeNeural redeNeural) {
		super("Gráfico");
		grafico = new Grafico(900, 1000, redeNeural);
		setResizable(false);
		this.add(grafico);
		this.pack();
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - getWidth(), 0);
		setFocusableWindowState(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void atualizar() {
		repaint();
	}
	
	public List<String> getTextos() {
		return grafico.getTextos();
	}
	
	public void setTextos(List<String> textos) {
		grafico.setTextos(textos);
	}
	
	public RedeNeural getRedeNeural() {
		return grafico.getRedeNeural();
	}
	
	public void setRedeNeural(RedeNeural redeNeural) {
		grafico.setRedeNeural(redeNeural);
		atualizar();
	}

}
