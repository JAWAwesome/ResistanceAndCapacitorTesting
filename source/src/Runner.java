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
    static int boxHeight = 250;
    static int boxWidth = 500;
    static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    static String[] values = {"black","brown","red","orange","yellow","green","blue","violet","grey","white",""};
    static JFrame window = new JFrame("Resistance and Capacitance Calculator");
    static JLabel bd1l = new JLabel("Band 1");
    static JLabel bd2l = new JLabel("Band 2");
    static JLabel bd3l = new JLabel("Band 3");
    static JLabel bd4l = new JLabel("Band 4");
    static JLabel res = new JLabel("Resistance Value");
    static JLabel capin = new JLabel("Capacitance code");
    static JLabel cap = new JLabel("Capacitance Value");
    static JLabel errors = new JLabel("Errors with values");
    static JComboBox<String> r1;
    static JComboBox<String> r2;
    static JComboBox<String> r3;
    static JComboBox<String> r4;
    static JTextField c = new JTextField();
    static JTextField ro = new JTextField();
    static JTextField co = new JTextField();
    static JTextField out = new JTextField();
    static Font boxes = new Font("Times New Roman",0,24);
    static Font textField = new Font("Times New Roman",0,24);
    static Font labels = new Font("Times New Roman",0, 18);
    static int labelHeight = 20;
    static String b1 = "null";
    static String b2 = "null";
    static String b3 = "null";
    static String b4 = "null";
    static String code = "null";
    static String resistanceValue = "0.0 Ohms";
    static String capacitanceValue = "0.0 Pico Farads";

    public static void main (String args[]) {
        buildView();
    }

    public static void buildView() {
        // Get the window size
        window.show();
        System.out.println(window.getInsets().top);
        System.out.println(window.getInsets().bottom);
        System.out.println(window.getInsets().left);
        System.out.println(window.getInsets().right);
        boxHeight = boxHeight + window.getInsets().top+window.getInsets().bottom;
        boxWidth = boxWidth + window.getInsets().left+window.getInsets().right;
        window.hide();

        /*
        // Set up Labels
        bd1l.setSize(boxWidth/4,labelHeight);
        bd1l.setLocation(0,0);
        bd1l.setFont(labels);
        bd2l.setSize(boxWidth/4,labelHeight);
        bd2l.setLocation(boxWidth/4,0);
        bd2l.setFont(labels);
        bd3l.setSize(boxWidth/4,labelHeight);
        bd3l.setLocation(boxWidth/2,0);
        bd3l.setFont(labels);
        bd4l.setSize(boxWidth/4,labelHeight);
        bd4l.setLocation(3*boxWidth/4,0);
        bd4l.setFont(labels);
        res.setSize(boxWidth,labelHeight);
        res.setLocation(0,boxHeight/4+labelHeight);
        res.setFont(labels);
        capin.setSize(boxWidth/2,labelHeight);
        capin.setLocation(0,0);
        capin.setFont(labels);
        cap.setSize(boxWidth/2,labelHeight);
        cap.setLocation(0,0);
        cap.setFont(labels);
        errors.setSize(boxWidth,labelHeight);
        errors.setLocation(0,0);
        errors.setFont(labels);
        */

        // Set up resistance combobox
        r1 = new JComboBox<>(values);
        r1.setSize(boxWidth/4,boxHeight/4);
        r1.setSelectedItem(values[values.length-1]);
        r1.setLocation(0,0);
        r1.setFont(boxes);
        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b1 = r1.getSelectedItem().toString();
                if (rValidateValue(b1)&&!b1.equals(values[values.length-1])) {
                    updateView(true);
                } else {
                    out.setText("Invalid String Value for Band 1");
                }
            }
        });
        r2 = new JComboBox<>(values);
        r2.setSize(boxWidth/4,boxHeight/4);
        r2.setSelectedItem(values[values.length-1]);
        r2.setLocation(boxWidth/4,0);
        r2.setFont(boxes);
        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b2 = r2.getSelectedItem().toString();
                if (rValidateValue(b2)&&!b2.equals(values[values.length-1])) {
                    updateView(true);
                } else {
                    out.setText("Invalid String value for Band 2");
                }
            }
        });
        r3 = new JComboBox<>(values);
        r3.setSize(boxWidth/4,boxHeight/4);
        r3.setSelectedItem(values[values.length-1]);
        r3.setLocation(2*boxWidth/4,0);
        r3.setFont(boxes);
        r3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b3 = r3.getSelectedItem().toString();
                if (rValidateValue(b3)&&!b3.equals(values[values.length-1])) {
                    updateView(true);
                }
                else {
                    out.setText("Invalid String value for Band 3");
                }
            }
        });
        r4 = new JComboBox<>(values);
        r4.setSize(boxWidth/4,boxHeight/4);
        r4.setSelectedItem(values[values.length-1]);
        r4.setLocation(3*boxWidth/4,0);
        r4.setFont(boxes);
        r4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b4 = r4.getSelectedItem().toString();
                if(rValidateValue(b4)&&!b4.equals(values[values.length-1])) {
                    updateView(true);
                } else if (!b4.equals(values[values.length-1])) {
                    out.setText("Invalid String Value for Band 4");
                }
            }
        });

        // Set up the capacitance listener
        c.setSize(boxWidth/2,boxHeight/4);
        c.setLocation(0,boxHeight/2);
        c.setFont(textField);
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (capacitance.goodString(c.getText())) {
                    code = c.getText();
                    updateView(false);
                } else {
                    out.setText("Invalid Capacitance Value");
                }
            }
        });

        // Set output textbox size
        ro.setSize(boxWidth,boxHeight/4);
        ro.setLocation(0,boxHeight/4);
        ro.setEditable(false);
        ro.setFont(textField);
        ro.setText("No Ohms");
        co.setSize(boxWidth/2,boxHeight/4);
        co.setLocation(boxWidth/2,boxHeight/2);
        co.setEditable(false);
        co.setFont(textField);
        co.setText("No Farads");

        // Create Error output area
        out.setSize(boxWidth,boxHeight-r1.getHeight()-ro.getHeight()-co.getHeight());
        out.setLocation(0,3*boxHeight/4);
        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(out.getText());
            }
        });
        out.setFont(textField);

        // Put the stuff in the window
        window.add(r1);
        window.add(r2);
        window.add(r3);
        window.add(r4);
        window.add(c);
        window.add(ro);
        window.add(co);
        window.add(out);
        window.add(new JLabel());

        // Modify window properties
        window.setSize(boxWidth,boxHeight);
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.setLocation(screenWidth/2-boxWidth/2,screenHeight/2-boxHeight/2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.show();
    }

    public static void updateView(boolean resistor) {
        // Resistor checks
        resistance.reset();
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
        if(b4.equals(values[values.length-1])){
            resistance.setB4("null");
        }
        if(!b1.equals("null")&&!b2.equals("null")&&!b3.equals("null")&&resistor) {
            resistanceValue = resistance.getValuePrefix();
            // Resistor update
            ro.setText(resistanceValue);
            window.repaint();
        }
        // Capacitor Update
        if (!resistor) {
            capacitance.setSCode(code);
            capacitanceValue = capacitance.getSValuePrefix();
            co.setText(capacitanceValue);
            window.repaint();
        }
    }

    public static boolean rValidateValue(String s) {
        for (String here: values) {
            if (here.equals(s)&&!s.equals(values[values.length-1])) {
                return true;
            }
        }
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
