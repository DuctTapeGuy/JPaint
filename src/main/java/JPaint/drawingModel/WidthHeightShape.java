package JPaint.drawingModel;

import java.awt.*;

public abstract class WidthHeightShape extends XYShape{
    protected int width;
    protected int height;

    public WidthHeightShape(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
}
