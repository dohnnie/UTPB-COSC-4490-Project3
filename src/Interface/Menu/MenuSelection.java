package src.Interface.Menu;

public class MenuSelection extends MenuText {
    private Runnable operation;

    public MenuSelection(Runnable op, String text) {
        super(text);
        operation = op;
    }

    public void run() {
        operation.run();
    }
}
