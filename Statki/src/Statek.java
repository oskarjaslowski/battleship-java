import java.awt.*;
public abstract class Statek {
    private int dlugosc;
    private boolean trafiony;
    public  boolean pionowo;
    protected Color color;
    public Statek(int dlugosc) {
        this.dlugosc = dlugosc;
        this.trafiony = false;
        this.pionowo = true;
        this.color = Color.RED;
    }
    public int getDlugosc() {
        return dlugosc;
    }
    public Color getKolor() {
        return color;
    }
    public void setKolor(Color color) {
        this.color = color;
    }
    public void trafiony() {
        this.trafiony = true;
        dlugosc--;
    }
    public boolean isTrafiony() {
        return trafiony;
    }
    public boolean isZatopiony() {
        return trafiony && dlugosc == 0;
    }
    public abstract String getRodzaj();
}