/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;

/**
 *
 * @author msy
 */
public class Main {
    
    public static void main(String[] args) {
        /**FlatLightLaf.install();
        JFrame f = new JFrame();
        f.add(new UserManagement());
        //498,320
        f.setSize(800,500);
        f.setResizable(false);
        f.setVisible(true);**/
        GUI f= new GUI(400,300);
        f.createDiscountPlan(3);
        //f.flexibleDiscount(3);
        f.setVisible(true);

    }
    
}
