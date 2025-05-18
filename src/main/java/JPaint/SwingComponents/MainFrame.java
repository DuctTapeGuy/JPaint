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
import java.util.TimerTask;

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
    public List<Dot> dots = new ArrayList<>();



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

        JPanel rightModePanel = new JPanel(new GridLayout(3, 2));
        rightModePanel.setPreferredSize(new Dimension(220, 220));
        JButton btnRectangle = new JButton("obdelnik");
        JButton btnOval = new JButton("kolecko");
        JButton btnTriangle = new JButton("trojuhelnik");
        JButton btnLine = new JButton("cara");
        JButton btnText = new JButton("text");
        JButton btnFree = new JButton("free");
        rightModePanel.add(btnRectangle);
        rightModePanel.add(btnOval);
        rightModePanel.add(btnTriangle);
        rightModePanel.add(btnLine);
        rightModePanel.add(btnText);
        rightModePanel.add(btnFree);
        JTextArea textArea = new JTextArea(5, 15);
        textArea.setPreferredSize(new Dimension(220, 20));
        rightPanel.add(rightModePanel);
        rightPanel.add(textArea);


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

        DefaultListModel<String> listModel = new DefaultListModel<>();

        JList utvary = new JList(listModel);
        JScrollPane utvaryFix = new JScrollPane(utvary);
        utvary.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        utvary.setFixedCellHeight(20);
        utvary.setFixedCellWidth(80);
        utvary.setVisibleRowCount(8);
        rightPanel.add(utvaryFix);




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
        btnLine.addActionListener((e) -> {
            pickedShape = "Line";
        });
        btnText.addActionListener((e) -> {
           pickedShape = "Text";
        });
        btnFree.addActionListener((e) -> {
            pickedShape = "Free";
        });
        List<Drawable> dotList = new ArrayList<>();
        List<Group> grouplist = new ArrayList<>();
        drawingPanel.addMouseListener(new MouseAdapter() {
            private java.util.Timer t;
            public void mousePressed(MouseEvent e) {
                firstX = e.getX();
                firstY = e.getY();

                if((t == null)&&(pickedShape.equals("Free"))) {
                    t = new java.util.Timer();
                }
                if (pickedShape.equals("Free")) {
                    t.schedule(new TimerTask(){
                        public void run(){
                            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                            Dot dot = new Dot((int) mouseLocation.getX(), (int) (mouseLocation.getY()-85),pickedColor);
                            dots.add(dot);
                        }
                    },1,3);
                }

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
                    listModel.addElement("obdelnik");
                } else if(pickedShape.equals("Oval")) {
                    Oval oval = new Oval(rohX,rohY,abs(lastX-firstX),abs(lastY-firstY),pickedColor);
                    tvary.add(oval);
                    listModel.addElement("oval");
                } else if(pickedShape.equals("Triangle")) {
                    Triangle triangle = new Triangle(rohX,roh2Y, roh2X, roh2Y, rohX+(abs(lastX-firstX)/2), rohY);
                    triangle.setColorus(pickedColor);
                    tvary.add(triangle);
                    listModel.addElement("triangle");
                } else if(pickedShape.equals("Line")) {
                    Line line = new Line(firstX,firstY,lastX,lastY);
                    line.setColorus(pickedColor);
                    tvary.add(line);
                    listModel.addElement("line");
                } else if ((pickedShape.equals("Text"))&&(!textArea.getText().trim().equals(""))) {
                    DrawableString pismo = new DrawableString(rohX,rohY,15, textArea.getText(),"#000000");
                    tvary.add(pismo);
                    listModel.addElement("pismo");
                } else if (pickedShape.equals("Free")) {
                    dotList.addAll(dots);
                    dots.clear();
                    Group tecky = new Group(dotList,0.0,1.0,1.0);
                    tvary.add(tecky);
                    grouplist.add(tecky);
                    listModel.addElement("drag");
                }


                if(t != null){
                    t.cancel();
                    t = null;
                }

                drawGroup();
            }

        });











    }

    private void drawGroup() {
        drawingPanel.setImage(new Group(tvary, 0.0,1.0,1.0));
    }

}
