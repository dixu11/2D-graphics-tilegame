package tilegame.display;

import tilegame.wrappers.Size;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private Size size;


    public Display(String title, Size size ) {
        this.title = title;
        this.size = size;

        createDisplay();
    }


    private void createDisplay() {          // initialize jframe
        frame = new JFrame(title);
        frame.setSize(size.getWidth(),size.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setisible(true);       //i'll do it after choosing starting lvl
        frame.setLocationRelativeTo(null);        // to appear in the center
        frame.setResizable(false);

        canvas = new Canvas();

        canvas.setPreferredSize(
                new Dimension(size.getWidth(),size.getHeight())
        );
        canvas.setMaximumSize(
                new Dimension(size.getWidth(),size.getHeight())
        );
        canvas.setMinimumSize(
                new Dimension(size.getWidth(),size.getHeight())
        );

        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }



    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Size getSize() {
        return size;
    }
}
