import java.awt.*;
public class Niszczyciel extends Trojmasztowiec {
    public Niszczyciel() {
        super();
        setKolor(Color.CYAN);
    }
    @Override
    public String getRodzaj() {
        return "Niszczyciel";
    }
}