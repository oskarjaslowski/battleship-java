import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public record Gracz(String pseudonim, int wynik) {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public void zapiszDoPliku() {
        List<Gracz> gracze = odczytajWszystkichGraczy();
        gracze.add(this);
        JSONArray tabelaJSON = new JSONArray();
        for (Gracz gracz : gracze) {
            JSONObject obiektJSON = new JSONObject();
            obiektJSON.put("pseudonim", gracz.pseudonim());
            obiektJSON.put("wynik", gracz.wynik());
            tabelaJSON.put(obiektJSON);
        }
        try (Writer pisacz = new FileWriter("gracze.json")) {
            tabelaJSON.write(pisacz);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Wystąpił błąd podczas zapisywania do pliku gracze.json", e);
        }
    }

    static List<Gracz> odczytajWszystkichGraczy() {
        List<Gracz> gracze = new ArrayList<>();
        try (BufferedReader czytacz = new BufferedReader(new FileReader("gracze.json"))) {
            StringBuilder trescJSON = new StringBuilder();
            String linia;
            while ((linia = czytacz.readLine()) != null) {
                trescJSON.append(linia);
            }
            JSONArray tablicaJSON = new JSONArray(trescJSON.toString());
            for (int i = 0; i < tablicaJSON.length(); i++) {
                JSONObject jsonObject = tablicaJSON.getJSONObject(i);
                String pseudonim = jsonObject.getString("pseudonim");
                int wynik = jsonObject.getInt("wynik");
                gracze.add(new Gracz(pseudonim, wynik));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Błąd podczas odczytu danych z pliku gracze.json", e);
        }
        return gracze;
    }
}