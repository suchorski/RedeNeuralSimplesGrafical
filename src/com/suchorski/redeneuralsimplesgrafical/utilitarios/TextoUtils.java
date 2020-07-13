package com.suchorski.redeneuralsimplesgrafical.utilitarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class TextoUtils {
	
	public static void escreverTextoCentralizado(Graphics g, String texto, Rectangle enquadramento, Color cor) {
		Font fonte = new Font("Arial", 0, 14);
		FontMetrics fontMetrics = g.getFontMetrics(fonte);
	    int x = enquadramento.x + (enquadramento.width - fontMetrics.stringWidth(texto)) / 2;
	    int y = enquadramento.y + ((enquadramento.height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
	    g.setFont(fonte);
	    g.setColor(cor);
	    g.drawString(texto, x, y);
	}
	
	public static void escreverTextoADireita(Graphics g, String texto, Rectangle enquadramento, Color cor) {
		Font fonte = new Font("Arial", 0, 14);
		FontMetrics fontMetrics = g.getFontMetrics(fonte);
		int x = enquadramento.width - fontMetrics.stringWidth(texto);
		int y = enquadramento.y + ((enquadramento.height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
		g.setFont(fonte);
		g.setColor(cor);
		g.drawString(texto, x, y);
	}

}
