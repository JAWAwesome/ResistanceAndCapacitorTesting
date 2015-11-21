/**
 * Created by Jared on 11/20/2015.
 */
public class Capacitor {
    private double value = 0;
    private double adjValue = 0;
    private String prefix = " Pico Farads";
    private int code = 0;

    public Capacitor() {
        code = 0;
    }

    public Capacitor(int n) {
        code = n;
    }

    private void addPrefix() {
        adjValue = value;
        if(!((int)adjValue/1000==0)) {
            adjValue = adjValue/1000;
            if(!((int)adjValue/1000==0)) {
                adjValue = adjValue/1000;
                if(!((int)adjValue/1000==0)) {
                    adjValue = adjValue/1000;
                    prefix = " Milli Farads";
                }
                else {
                    prefix = " Micro Farads";
                }
            }
            else {
                prefix = " Nano Farads";
            }
        } else {
            prefix = " Pico Farads";
        }
    }

    private void calculate() {
        String sCode = String.valueOf(code);
        int p1 = Integer.parseInt(sCode.substring(0,1));
        int p2 = Integer.parseInt(sCode.substring(1,2));
        int p3 = Integer.parseInt(sCode.substring(2,3));
        value = p1*10 + p2;
        value = value * Math.pow(10,p3);
    }

    public void setCode(int n) {
        code = n;
    }

    public String getValue() {
        calculate();
        return value + " Farads";
    }

    public String getValuePrefix() {
        calculate();
        addPrefix();
        return adjValue + prefix;
    }
}
