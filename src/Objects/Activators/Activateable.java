package src.Objects.Activators;

public interface Activateable {
    public void activate(Runnable op);
    public void onActivate(Runnable op);
}
