package src.Interface.Menu;

import src.Interface.InterfaceImage;

import java.awt.image.BufferedImage;

public class MenuImage extends InterfaceImage implements MenuItem {
	public MenuImage (BufferedImage image) {
		super(image);
	}

	@Override
	public void select() {

	}

	@Override
	public void deselect() {

	}
}
