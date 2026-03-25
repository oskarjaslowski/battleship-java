import java.awt.*;
import java.util.Random;
public class PrzeciwnikLatwy extends Przeciwnik {
    public PrzeciwnikLatwy(PlanszaPrzeciwnika planszaPrzeciwnika) {
        super(planszaPrzeciwnika);
    }

    @Override
    protected Point wybierzPole() {
        Random random = new Random();
        int x, y;
        Point wybranePole;
        do {
            x = random.nextInt(Plansza.ROZMIAR_PLANSZY);
            y = random.nextInt(Plansza.ROZMIAR_PLANSZY);
            wybranePole = new Point(x, y);

        } while (czyPoleWybrane(wybranePole));
        wybranePola.add(wybranePole);
        return wybranePole;
    }
}