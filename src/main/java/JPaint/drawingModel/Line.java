package JPaint.drawingModel;

import java.awt.*;

public class Line implements Drawable{
    private String color;
    private int x1, y1, x2, y2;

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode(color));
        int[] xka = {x1,x1+2,x2,x2+2};
        int[] yka = {y1,y1+2,y2,y2+2};
        Polygon p = new Polygon(xka,yka,4);
        g.fillPolygon(p);
    }


    public void setColorus(String colorus){
        color = colorus;
    }
}
