package src.UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class UIImage implements UIDrawable
{
	BufferedImage image;

	public UIImage(BufferedImage b) {
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
		BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics x = outImage.getGraphics();
		x.drawImage(image, 0, 0, null);
		x.dispose();
		image = outImage;
	}

	public void scaleToWidth(int width) {
		int height = width / image.getWidth() * image.getHeight();
		scaleTo(width, height);
	}

	public void scaleToHeight(int height) {
		int width = height / image.getHeight() * image.getWidth();
		scaleTo(width, height);
	}

	public void scaleToScreen() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		scaleTo(tk.getScreenSize().width, tk.getScreenSize().height);
	}
}
