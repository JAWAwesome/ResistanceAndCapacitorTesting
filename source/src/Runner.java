import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * Created by Jared on 11/20/2015.
 */
public class Runner {
    static Resistor resistance = new Resistor();
    static Capacitor capacitance = new Capacitor();
    static Scanner kb = new Scanner(System.in);
    static int boxHeight = 500;
    static int boxWidth = 500;
    static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    static String[] values = {"black","brown","red","orange","yellow","green","blue","violet","grey","white",""};
    static JFrame window = new JFrame();
    static JComboBox<String> r1;
    static JComboBox<String> r2;
    static JComboBox<String> r3;
    static JComboBox<String> r4;
    static JTextField c = new JTextField();
    static JTextField ro = new JTextField();
    static JTextField co = new JTextField();
    static JButton jb = new JButton();
    static String b1 = "null";
    static String b2 = "null";
    static String b3 = "null";
    static String b4 = "null";
    static int code = 000;
    static String resistanceValue = "null";
    static String capacitanceValue = "null";

    public static void main (String args[]) {
        buildView();
        window.show();
    }

    public static void buildView() {
        window.show();
        boxHeight = boxHeight + window.getInsets().top+window.getInsets().bottom;
        boxWidth = boxWidth + window.getInsets().left+window.getInsets().right;
        window.hide();
        r1 = new JComboBox<>(values);
        r1.setSize(boxWidth/4,boxHeight/4);
        r1.setSelectedItem(values[values.length-1]);
        r1.setLocation(0,0);
        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b1 = r1.getSelectedItem().toString();
                check();
                System.out.println("Combo 1");
            }
        });
        r2 = new JComboBox<>(values);
        r2.setSize(boxWidth/4,boxHeight/4);
        r2.setSelectedItem(values[values.length-1]);
        r2.setLocation(boxWidth/4,0);
        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b2 = r1.getSelectedItem().toString();
                check();
                System.out.println("Combo 2");
            }
        });
        r3 = new JComboBox<>(values);
        r3.setSize(boxWidth/4,boxHeight/4);
        r3.setSelectedItem(values[values.length-1]);
        r3.setLocation(2*boxWidth/4,0);
        r3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b3 = r1.getSelectedItem().toString();
                check();
                System.out.println("Combo 3");
            }
        });
        r4 = new JComboBox<>(values);
        r4.setSize(boxWidth/4,boxHeight/4);
        r4.setSelectedItem(values[values.length-1]);
        r4.setLocation(3*boxWidth/4,0);
        r4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b4 = r1.getSelectedItem().toString();
                check();
                System.out.println("Combo 4");
            }
        });

        c.setSize(boxWidth/4,boxHeight/4);
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Capacitor");
                code = Integer.parseInt(c.getText());
            }
        });

        ro.setSize(boxWidth/2,boxHeight/4);
        co.setSize(boxWidth/2,boxHeight/4);

        jb.setSize(boxWidth,boxHeight/4);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button");
            }
        });

        window.add(r1);
        window.add(r2);
        window.add(r3);
        window.add(r4);

        window.add(c);
        window.add(ro);
        window.add(co);
        window.add(jb);
        window.setSize(boxWidth,boxHeight);
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.setLocation(screenWidth/2-boxWidth/2,screenHeight/2-boxHeight/2);
    }

    public static void updateView() {
        ro.setText(resistanceValue);
        co.setText(capacitanceValue);
        window.repaint();
    }

    public static void check() {
        if (!b1.equals("null")) {
            resistance.setB1(b1);
        }
        if(!b2.equals("null")) {
            resistance.setB2(b2);
        }
        if(!b3.equals("null")) {
            resistance.setB3(b3);
        }
        if(!b4.equals("null")) {
            resistance.setB4(b4);
        }
        if(!b1.equals("null")&&!b2.equals("null")&&!b3.equals("null")) {
            resistanceValue = resistance.getValuePrefix();
            updateView();
        }
        capacitance.setCode(code);
        capacitanceValue = capacitance.getValuePrefix();
    }

    public static boolean rValidateValue(String s) {
        return false;
    }

    public static void testResistor() {
        System.out.println("Enter the colors of the bands separated by enter");
        String band1;
        band1 = kb.nextLine();
        while(!resistance.colorGood(band1)) {
            System.out.println("Invalid color");
            band1 = kb.nextLine();
        }
        resistance.setB1(band1);
        System.out.println("Next");
        String band2;
        band2 = kb.nextLine();
        while(!resistance.colorGood(band2)) {
            System.out.println("Invalid color");
            band2 = kb.nextLine();
        }
        resistance.setB2(band2);
        System.out.println("Next");
        String band3;
        band3 = kb.nextLine();
        while(!resistance.colorGood(band3)) {
            System.out.println("Invalid color");
            band3 = kb.nextLine();
        }
        resistance.setB3(band3);
        System.out.println("Next");
        String band4;
        band4 = kb.nextLine();
        if (!band4.equals(" ")) {
            while (!resistance.colorGood(band4)) {
                if (!band4.equals(" ")) {
                    System.out.println("Invalid color");
                    band4 = kb.nextLine();
                } else {
                    break;
                }
            }
            if (!band4.equals(" ")) {
                resistance.setB4(band4);
            }
        }
        System.out.println(resistance.getValuePrefix());
    }

    public static void testCapacitor() {
        boolean exit = false;
        int code = 000;
        System.out.println("Enter the code as written and then press enter");
        do {
            try {
                code = kb.nextInt();
                exit = true;
            } catch (Exception e) {
                System.out.println("Incorrect code");
                exit = false;
            }
            if(code<100) {
                exit = false;
                System.out.println("Incorrect code");
            }
        } while (!exit);
        capacitance.setCode(code);
        System.out.println(capacitance.getValuePrefix());
    }
}
