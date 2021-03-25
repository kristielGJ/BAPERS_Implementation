package GUI;

import javax.swing.*;

public class PopUpBox extends JFrame {

    public PopUpBox(int width, int height, String title) {
        this.setSize(width,height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle(title);
    }
}
