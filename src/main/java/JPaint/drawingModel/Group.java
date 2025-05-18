package JPaint.drawingModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;


public class Group implements Drawable {
    private Double rotationDeg;
    private Double scaleX;
    private Double scaleY;

    private List<Drawable> items;


    public Group(List<Drawable> items, Double rotationDeg, Double scaleX, Double scaleY) {
        this.items = items;
        this.rotationDeg = rotationDeg;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform transform = g.getTransform();
        g.scale(scaleX, scaleY);
        g.rotate(rotationDeg/180*(Math.PI/180));
        for(Drawable item : items) {
            item.draw(g);
        }
        g.setTransform(transform);
    }
}
