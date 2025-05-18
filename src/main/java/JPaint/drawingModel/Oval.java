package JPaint.drawingModel;

import java.awt.*;

public class Oval extends WidthHeightShape{
    private String color;
    public Oval(int x, int y, int width, int height, String color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode(color));
        g.fillOval(x, y, width, height);
    }
}
