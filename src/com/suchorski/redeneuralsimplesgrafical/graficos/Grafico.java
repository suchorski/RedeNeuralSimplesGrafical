package com.suchorski.redeneuralsimplesgrafical.graficos;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.suchorski.redeneuralsimples.objetos.Camada;
import com.suchorski.redeneuralsimples.objetos.Neuronio;
import com.suchorski.redeneuralsimples.objetos.RedeNeural;
import com.suchorski.redeneuralsimplesgrafical.definicoes.Cores;
import com.suchorski.redeneuralsimplesgrafical.definicoes.Parametros;
import com.suchorski.redeneuralsimplesgrafical.utilitarios.TextoUtils;

public class Grafico extends JPanel {

	private int comprimento;
	private int altura;
	private RedeNeural redeNeural;
	private List<String> textos;

	protected Grafico(int comprimento, int altura, RedeNeural redeNeural) {
		setPreferredSize(new Dimension(comprimento, altura));
		this.comprimento = comprimento;
		this.altura = altura;
		this.redeNeural = redeNeural;
		this.textos = new ArrayList<String>();
	}

	protected RedeNeural getRedeNeural() {
		return redeNeural;
	}

	protected void setRedeNeural(RedeNeural redeNeural) {
		this.redeNeural = redeNeural;
	}
	
	public List<String> getTextos() {
		return textos;
	}
	
	public void setTextos(List<String> textos) {
		this.textos = textos;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Cores.FUNDO);
		g.fillRect(0, 0, comprimento, altura);
		if (redeNeural != null) {
			g.setColor(Cores.LINHA);
			int divisaoCamadas = comprimento / (redeNeural.getCamadas().size() + 1);
			int posicaoCamadas = comprimento - divisaoCamadas;
			for (int i = redeNeural.getCamadas().size() - 1; i > 0; --i) {
				Camada posterior = redeNeural.getCamadas().get(i);
				int divisaoPosterior = altura / (posterior.getNeuronios().size() + 1);
				int posicaoPosterior = altura - divisaoPosterior;
				Camada anterior = redeNeural.getCamadas().get(i - 1);
				int divisaoAnterior = altura / (anterior.getNeuronios().size() + 1);
				for (int j = posterior.getNeuronios().size() - 1; j >= 0; --j) {
					Neuronio neuronio = posterior.getNeuronios().get(j);
					if (!neuronio.isBias()) {
						int posicaoAnterior = altura - divisaoAnterior;
						for (int k = anterior.getNeuronios().size() - 1; k >= 0; --k) {
							int x1 = posicaoCamadas;
							int y1 = posicaoPosterior;
							int x2 = posicaoCamadas - divisaoCamadas;
							int y2 = posicaoAnterior;
							g.setColor(Cores.LINHA);
							g.drawLine(x1, y1, x2, y2);
							Rectangle enquadramento = new Rectangle(x2, y1, x1 - x2, y2 - y1);
							TextoUtils.escreverTextoCentralizado(g, String.format("%.0f", neuronio.getLigacoes().get(k).getPeso()), enquadramento, Cores.TEXTO_ALT);
							posicaoAnterior -= divisaoAnterior; 
						}
					}
					posicaoPosterior -= divisaoPosterior;
				}
				posicaoCamadas -= divisaoCamadas;
			}
			posicaoCamadas = comprimento - divisaoCamadas;
			for (int i = redeNeural.getCamadas().size() - 1; i >= 0; --i) {
				Camada camada = redeNeural.getCamadas().get(i);
				int divisaoCamada = altura / (camada.getNeuronios().size() + 1);
				int posicaoCamada = altura - divisaoCamada;
				for (int j = camada.getNeuronios().size() - 1; j >= 0; --j) {
					Neuronio neuronio = camada.getNeuronios().get(j);
					g.setColor(neuronio.isAtivo() ? Cores.ATIVO : Cores.INATIVO);
					if (i == 0) {
						g.setColor(Cores.ENTRADA);
					}
					if (neuronio.isBias()) {
						g.setColor(Cores.BIAS);
					}
					Rectangle enquadramento = new Rectangle(posicaoCamadas - Parametros.TAMANHO_NEURONIO / 2, posicaoCamada - Parametros.TAMANHO_NEURONIO / 2, Parametros.TAMANHO_NEURONIO, Parametros.TAMANHO_NEURONIO);
					g.fillOval(posicaoCamadas - Parametros.TAMANHO_NEURONIO / 2, posicaoCamada - Parametros.TAMANHO_NEURONIO / 2, Parametros.TAMANHO_NEURONIO, Parametros.TAMANHO_NEURONIO);
					TextoUtils.escreverTextoCentralizado(g, String.format("%.0f", neuronio.getValor()), enquadramento, Cores.TEXTO_CLARO);
					posicaoCamada -= divisaoCamada;
				}
				posicaoCamadas -= divisaoCamadas;
			}
			g.setColor(Cores.TEXTO_ESCURO);
			g.setFont(new Font("Arial", 0, 14));
			int y = 18;
			for (String s : textos) {
				g.drawString(s, 5, y);
				y += 16;
			}
		} else {
			TextoUtils.escreverTextoCentralizado(g, "Nenhuma rede selecionada!", new Rectangle(comprimento, altura), Cores.TEXTO_ESCURO);
		}
	}

}
