package src.Levels.Classic;

import src.Collision.CollideRect;
import src.Drawing.Drawable;
import src.Func;
import src.Objects.Actors.Obstacle;
import src.Rand;
import src.Threads.Engine;
import src.Threads.Updateable;

import java.awt.*;

public class Pipe extends Obstacle implements Drawable, Updateable {
    final ClassicMode parent;
    final Toolkit tk;

    public double defaultVel = 3.0;
    public double xVel = 3.0;

    public int width;
    public int height;
    public int gap;

    private boolean scoreable = true;
    private final int index;

    public Pipe(Engine e, ClassicMode p, int idx)
    {
        super(e);
        parent = p;
        index = idx;
        tk = Toolkit.getDefaultToolkit();

        xPos = tk.getScreenSize().width;

        int min = tk.getScreenSize().height / 4;
        int y = Rand.range(min, min*2);

        width = p.pipeWidth;
        height = p.pipeHeight;

        int range = tk.getScreenSize().height / 6;
        gap = Rand.range(range, range*2);

        yPos = y + gap / 2;

        this.addBody(new CollideRect(new Point(xPos, yPos), width, height));
        this.addBody(new CollideRect(new Point(xPos, yPos - gap - height), width, height));
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(parent.pipeImage, xPos, yPos, null);
        g2d.drawImage(parent.pipeFlipped, xPos, yPos - gap - height, null);
        super.draw(g2d);
    }

    @Override
    public Point getAnchor()
    {
        return new Point(xPos, yPos);
    }

    @Override
    public void update()
    {
        xVel = defaultVel + defaultVel * Func.tanh((double) parent.score / 10);
        xPos -= (int) xVel;

        if (scoreable && xPos < tk.getScreenSize().width / 2)
        {
            scoreable = false;
            parent.score();
        }

        if (xPos < -width) {
            parent.killPipe(index);
        }
    }
}
