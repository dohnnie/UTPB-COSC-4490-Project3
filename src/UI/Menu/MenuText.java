package src.UI.Menu;

import src.UI.UIText;

public class MenuText extends UIText implements MenuItem {
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
