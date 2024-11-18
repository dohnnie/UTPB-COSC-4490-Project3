package src.Interface;

import src.Drawing.ImageScaler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InterfaceImage implements InterfaceDrawable
{
	BufferedImage image;

	public InterfaceImage(BufferedImage b) {
		image = b;
	}

	public void drawAt(Graphics2D g2d, int x, int y) {
		g2d.drawImage(image, x, y, null);
	}

	public void drawCentered(Graphics2D g2d, int x, int y) {
		drawAt(g2d, x-image.getWidth()/2, y-image.getHeight()/2);
	}

	public void drawCenteredX(Graphics2D g2d, int x, int y) {
		drawAt(g2d, x-image.getWidth()/2, y);
	}

	public void drawCenteredY(Graphics2D g2d, int x, int y) {
		drawAt(g2d, x, y-image.getHeight()/2);
	}

	public void scaleTo(int width, int height) {
		image = ImageScaler.scaleTo(image, width, height);
	}

	public void scaleToWidth(int width) {
		double scale = (double)width / (double)image.getWidth();
		int height = (int)(scale * image.getHeight());
		scaleTo(width, height);
	}

	public void scaleToHeight(int height) {
		double scale = (double)height / (double)image.getHeight();
		int width = (int)(scale * image.getWidth());
		scaleTo(width, height);
	}

	public void scaleToScreen() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		scaleTo(tk.getScreenSize().width, tk.getScreenSize().height);
	}
}
