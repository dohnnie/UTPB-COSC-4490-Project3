package src.Interface.Menu;

import src.Interface.InterfaceText;

public class MenuText extends InterfaceText implements MenuItem {
	public MenuText() {
		super();
	}

	public MenuText(String t) {
		super(t);
	}

	@Override
	public void select() {
		text = String.format("> %s <", defaultText);
	}

	@Override
	public void deselect() {
		text = defaultText;
	}
}
