package JPaint.drawingModel;

import java.awt.*;

public class Triangle implements Drawable {
    private String color;
    private int x1, y1, x2, y2, x3, y3;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode(color));
        int[] xka = {x1,x2,x3};
        int[] yka = {y1,y2,y3};
        Polygon p = new Polygon(xka,yka,3);
        g.fillPolygon(p);
    }


    public void setColorus(String colorus){
            color = colorus;
    }
}
