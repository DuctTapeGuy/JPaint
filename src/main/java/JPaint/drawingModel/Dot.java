package JPaint.drawingModel;

import java.awt.*;

public class Dot implements Drawable {
    private String color;
    private int x;
    private int y;

    public Dot(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public void draw(Graphics2D g) {
        g.setColor(Color.decode(color));
        g.fillOval(x, y, 4, 4);
    }
}
