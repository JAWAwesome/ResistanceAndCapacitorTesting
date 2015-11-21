import java.util.ArrayList;

/**
 * Created by Jared on 11/20/2015.
 */
public class Resistor {
    private double value = 0;
    private double adjValue = 0;
    private String prefix = " Ohms";
    private int size = 0;
    private ArrayList<String> colors = new ArrayList<>();
    private String b1 = "null";
    private String b2 = "null";
    private String b3 = "null";
    private String b4 = "null";

    public Resistor() {
        mkAL();
    }

    public Resistor(String band1,String band2,String band3) {
        b1 = band1; b2 = band2; b3 = band3;
        mkAL();
    }

    public Resistor(String band1,String band2,String band3,String band4) {
        b1 = band1; b2 = band2; b3 = band3; b4 = band4;
        mkAL();
    }

    private void mkAL() {
        colors.add("black");
        colors.add("brown");
        colors.add("red");
        colors.add("orange");
        colors.add("yellow");
        colors.add("green");
        colors.add("blue");
        colors.add("violet");
        colors.add("grey");
        colors.add("white");
    }

    private int colorToNumber(String color) {
        if(colorGood(color)) {
            switch (color) {
                case "purple":
                    return colors.indexOf("violet");
                case "gray":
                    return colors.indexOf("grey");
                case "gold":
                    return -1;
                case "silver":
                    return -2;
                default:
                    break;
            }
            return colors.indexOf(color);
        }
        return 555555555;
    }

    private void calculate() {
        findSize();

        if (size==3) {
            value = colorToNumber(b1) * Math.pow(10,size-2) + colorToNumber(b2) * Math.pow(10,size-3);
            value = value * Math.pow(10,colorToNumber(b3));
        }else {
            value = colorToNumber(b1) * Math.pow(10,size-2) + colorToNumber(b2) * Math.pow(10,size-3) + colorToNumber(b3) * Math.pow(10,size-4);
            value = value * Math.pow(10,colorToNumber(b4));
        }

    }

    private void findSize() {
       if (b4.equals("null")) {
           size = 3;
       } else {
           size = 4;
       }
    }

    public void setB1(String band1) {
        b1 = band1;
    }

    public void setB2(String band2) {
        b2 = band2;
    }

    public void setB3(String band3) {
        b3 = band3;
    }

    public void setB4(String band4) {
        b4 = band4;
    }

    public void reset() {
        b1 = b2 = b3 = b4 = "null";
        value = 0;
        size = 0;
    }

    public boolean colorGood(String color) {
        color = color.toLowerCase();
        return colors.contains(color)||color.equals("purple")||color.equals("gray")||color.equals("gold")||color.equals("silver");
    }

    public void addPrefix() {
        adjValue = value;
        if(!((int)adjValue/1000==0)) {
            adjValue = adjValue/1000;
            if(!((int)adjValue/1000==0)) {
                adjValue = adjValue/1000;
                prefix = " Mega Ohms";
            }
            prefix = " Kilo Ohms";
        } else {
            prefix = " Ohms";
        }
    }

    public String getValue() {
        calculate();
        return value + " Ohms";
    }

    public String getValuePrefix() {
        calculate();
        addPrefix();
        return adjValue + prefix;
    }
}