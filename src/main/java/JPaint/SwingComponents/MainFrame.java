package JPaint.SwingComponents;

import JPaint.drawingModel.*;
import JPaint.drawingModel.Rectangle;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class MainFrame extends JFrame {
    DrawingPanel drawingPanel;
    DrawingPanel drawingPanel2;
    private int firstX;
    private int firstY;
    private int lastX;
    private int lastY;
    private String pickedColor = "#222222";
    private String pickedShape = "Rectangle";

    public List<Drawable> tvary = new ArrayList<>();


    public MainFrame(Drawable example) {
        //tvary.add(new Rectangle(1,1,1,1,"#000000"));

        setTitle("JPaint");
        setVisible(true);
        setSize(1600, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        drawingPanel = new DrawingPanel(example);
        drawingPanel2 = new DrawingPanel(null);
        add(drawingPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(250, 0));
        add(rightPanel, BorderLayout.EAST);

        //JPanel rightSectionPanel = new JPanel(new BoxLayout(drawingPanel, BoxLayout.Y_AXIS));
        //rightSectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //rightPanel.setBackground(Color.decode("#0000bb"));
        JLabel test1Label = new JLabel("tvary?");
        rightPanel.add(test1Label);

        JPanel rightModePanel = new JPanel(new GridLayout(2, 2));
        rightModePanel.setPreferredSize(new Dimension(220, 220));
        JButton btnRectangle = new JButton("obdelnik");
        JButton btnOval = new JButton("kolecko");
        JButton btnTriangle = new JButton("trojuhelnik");
        JButton test4 = new JButton("Test4");
        rightModePanel.add(btnRectangle);
        rightModePanel.add(btnOval);
        rightModePanel.add(btnTriangle);
        rightModePanel.add(test4);
        rightPanel.add(rightModePanel);


        JLabel test2Label = new JLabel("Barvicky");
        rightPanel.add(test2Label);
        JPanel rightColorPanel = new JPanel(new GridLayout(2, 2));
        rightColorPanel.setPreferredSize(new Dimension(220, 220));
        JButton btnColorRed = new JButton("red");
        JButton btnColorGreen = new JButton("green");
        JButton btnColorBlue = new JButton("blue");
        JButton btnColorYellow = new JButton("yellow");
        rightColorPanel.add(btnColorRed);
        rightColorPanel.add(btnColorGreen);
        rightColorPanel.add(btnColorBlue);
        rightColorPanel.add(btnColorYellow);
        rightPanel.add(rightColorPanel);
        JLabel test3Label = new JLabel("Veci?");
        rightPanel.add(test3Label);




        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setPreferredSize(new Dimension(0, 60));
        topPanel.setBackground(Color.decode("#888888"));
        add(topPanel, BorderLayout.NORTH);


        JButton button = new JButton("Paint");
        JButton button2 = new JButton("Scene");
        topPanel.add(button);
        topPanel.add(button2);


        button.addActionListener((e) -> {
            remove(drawingPanel);
            add(drawingPanel, BorderLayout.CENTER);
            repaint();
        });

        button2.addActionListener((e) -> {
            remove(drawingPanel);
            add(drawingPanel2, BorderLayout.CENTER);
            repaint();
        });

        btnColorRed.addActionListener((e) -> {
           pickedColor = "#FF0000";
        });
        btnColorGreen.addActionListener((e) -> {
            pickedColor = "#00FF00";
        });
        btnColorBlue.addActionListener((e) -> {
            pickedColor = "#0000FF";
        });
        btnColorYellow.addActionListener((e) -> {
            pickedColor = "#FFFF00";
        });

        btnRectangle.addActionListener((e) -> {
            pickedShape = "Rectangle";
        });
        btnOval.addActionListener((e) -> {
            pickedShape = "Oval";
        });
        btnTriangle.addActionListener((e) -> {
            pickedShape = "Triangle";
        });


        drawingPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                firstX = e.getX();
                firstY = e.getY();
            }
            public void mouseReleased(MouseEvent e) {
                int rohX = 0;
                int rohY = 0;
                int roh2X = 0;
                int roh2Y = 0;
                lastX = e.getX();
                lastY = e.getY();
                rohX = firstX > lastX ? lastX : firstX;
                rohY = firstY > lastY ? lastY : firstY;
                roh2X = firstX < lastX ? lastX : firstX;
                roh2Y = firstY < lastY ? lastY : firstY;


                if(pickedShape.equals("Rectangle")) {
                    Rectangle rectangle = new Rectangle(rohX,rohY,abs(lastX-firstX),abs(lastY-firstY),pickedColor);
                    tvary.add(rectangle);
                } else if(pickedShape.equals("Oval")) {
                    Oval oval = new Oval(rohX,rohY,abs(lastX-firstX),abs(lastY-firstY),pickedColor);
                    tvary.add(oval);
                } else if(pickedShape.equals("Triangle")) {
                    //Triangle triangle = new Triangle(roh2X,roh2Y,rohX,rohY,abs(lastX-firstX),abs(lastY-firstY));
                    Triangle triangle = new Triangle(rohX,roh2Y, roh2X, roh2Y, rohX+(abs(lastX-firstX)/2), rohY);
                    triangle.setColorus(pickedColor);
                    tvary.add(triangle);


                }


                drawGroup();
            }

        });











    }

    private void drawGroup() {
        drawingPanel.setImage(new Group(tvary, 0.0,1.0,1.0));
    }

}
