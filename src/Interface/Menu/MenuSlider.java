package src.Interface.Menu;

public class MenuSlider extends MenuText {
    public int numPlaces;
    public int slideVal = 0;

    public MenuSlider(int n, int v) {
        super();
        numPlaces = n;
        slideVal = v;
        StringBuilder slide = new StringBuilder();
        for (int i = 0; i < numPlaces; i++) {
            if (i == v) {
                slide.append("|");
            } else {
                slide.append("-");
            }
        }
        setText(slide.toString());
    }
}
