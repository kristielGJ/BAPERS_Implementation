import GUI.GUI;
import com.formdev.flatlaf.FlatLightLaf;


public class Main {

    public static void main(String[] args) {
        FlatLightLaf.install();
        GUI f = new GUI(800,500);
        f.setResizable(false);
        f.login();
        f.setVisible(true);
    }

}
