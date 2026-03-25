import java.awt.*;
public class PrzeciwnikTrudny extends Przeciwnik {

    public PrzeciwnikTrudny(PlanszaPrzeciwnika planszaPrzeciwnika) {
        super(planszaPrzeciwnika);
        maxSzczescie = 3;
    }

    @Override
    protected void uzupelnijWybranePola(Point wybranePole) {
        ostatnieTrafionePole = wybranePole;
        wybranePola.add(new Point(wybranePole.x - 1, wybranePole.y - 1));
        wybranePola.add(new Point(wybranePole.x - 1, wybranePole.y + 1));
        wybranePola.add(new Point(wybranePole.x + 1, wybranePole.y + 1));
        wybranePola.add(new Point(wybranePole.x + 1, wybranePole.y - 1));
    }
}