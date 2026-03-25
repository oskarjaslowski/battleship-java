import java.awt.*;
import java.util.Objects;
public class Nawigator extends Jednomasztowiec {
    public Nawigator() {
        super();
        setKolor(Color.GREEN);
    }
    public Point znajdzNietrafionePole(Statek[][] statki) {
        for (int i = 0; i < statki.length; i++) {
            for (int j = 0; j < statki[i].length; j++) {
                if (statki[i][j] != null && !statki[i][j].isTrafiony() && (!Objects.equals(statki[i][j].getRodzaj(), "Pancernik"))) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }
    @Override
    public String getRodzaj() {
        return "Nawigator";
    }
}