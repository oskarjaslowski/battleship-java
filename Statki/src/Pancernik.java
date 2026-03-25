import java.awt.*;
public class Pancernik extends Jednomasztowiec {
    private int trafienia;
    public Pancernik() {
        super();
        setKolor(Color.ORANGE);
        trafienia = 0;
    }
    @Override
    public void trafiony() {
        trafienia++;
    }
    @Override
    public boolean isZatopiony() {
        return trafienia >= 2;
    }
    @Override
    public String getRodzaj() {
        return "Pancernik";
    }
    @Override
    public Color getKolor() {
        return isZatopiony() ? this.color : Color.YELLOW;
    }
}