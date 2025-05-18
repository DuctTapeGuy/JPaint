package JPaint.drawingModel;

import java.awt.*;

public class DrawableString extends XYShape{
    private String str;
    private int size;
    private String color;
    public DrawableString(int x, int y, int size, String str, String color) {
        super(x, y);
        this.size = size;
        this.str = str;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, size));
        g.setColor(Color.decode(color));
        g.drawString(str, x, y);
    }
}
