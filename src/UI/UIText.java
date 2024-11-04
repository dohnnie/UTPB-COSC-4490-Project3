package src.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class UIText implements UIDrawable {

	public String defaultText;
	public String text;
	public String fontName = "Arial";
	public int fontStyle = Font.PLAIN;
	public int fontSize = 10;
	public int offset = 10;

	public UIText() {

	}

	public UIText (String t) {
		setText(t);
	}

	public void setText(String t) {
		defaultText = t;
		text = t;
	}

	public void setOffset(int o) {
		offset = o;
	}

	public void setFontName(String name) {
		fontName = name;
	}

	public void setFontStyle(int style) {
		fontStyle = style;
	}

	public void setFontSize(int size) {
		fontSize = size;
	}

	public void setFont(String name, int style, int size) {
		setFontName(name);
		setFontStyle(style);
		setFontSize(size);
	}

	public void sizeFont(Graphics g, int width, int height) {
		Font f;
		FontMetrics fm;
		int size = fontSize;
		int w = 0, h = 0;

		while (w <= width && h <= height) {
			f = new Font(fontName, fontStyle, fontSize);
			fm = g.getFontMetrics(f);
			h = fm.getHeight();
			w = fm.stringWidth(text);
			size += 1;
		}

		setFontSize(size-1);
		setOffset(h/8);
	}

	public void drawAt(Graphics2D g2d, int x, int y) {
		g2d.setFont(new Font(fontName, fontStyle, fontSize));
		g2d.setColor(Color.BLACK);
		g2d.drawString(text, x, y);

		g2d.setColor(Color.WHITE);
		g2d.drawString(text, x-offset, y-offset);
		g2d.drawString(text, x-offset, y+offset);
		g2d.drawString(text, x+offset, y-offset);
		g2d.drawString(text, x+offset, y+offset);
	}

	public void drawCentered(Graphics2D g2d, int x, int y) {
		g2d.setFont(new Font(fontName, fontStyle, fontSize));
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		drawAt(g2d, x - fm.stringWidth(text) /2, y - fm.getHeight() /2);
	}

	public void drawCenteredX(Graphics2D g2d, int x, int y) {
		g2d.setFont(new Font(fontName, fontStyle, fontSize));
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		drawAt(g2d, x - fm.stringWidth(text) /2, y);
	}

	public void drawCenteredY(Graphics2D g2d, int x, int y) {
		g2d.setFont(new Font(fontName, fontStyle, fontSize));
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		drawAt(g2d, x, y - fm.getHeight() /2);
	}
}
