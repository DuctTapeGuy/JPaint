package JPaint;

import JPaint.SwingComponents.MainFrame;
import JPaint.drawingModel.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Drawable> list = new ArrayList<Drawable>();
        SwingUtilities.invokeLater(() -> {
            Group g1 = new Group(list, 0.0,1.0,1.0);

            new MainFrame(g1);
        });

    }
}
