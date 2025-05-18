package JPaint.SwingComponents;

import JPaint.drawingModel.Drawable;
import JPaint.drawingModel.Group;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    Drawable image;

    public DrawingPanel(Drawable image) {
        this.image = image;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        image.draw((Graphics2D) g);
    }
    public void setImage(Drawable image) {
        this.image = image;
        repaint();
    }
}
