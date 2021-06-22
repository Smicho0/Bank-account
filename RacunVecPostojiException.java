package tvz.hr.priprema.x;

import java.util.ArrayList;
import java.util.List;

public class RacunVecPostojiException extends Exception {

    public static List<String> racun = new ArrayList<>();

    public RacunVecPostojiException(String message) {
        super(message);
    }

    public static boolean provjeraPostojecegRacuna(String message) {
        for(String racun : racun ) {
            if(message.equals(racun)) {
                return false;
            }
        }
        return true;
    }

    public static void noviRacun (String message) {
        racun.add(message);
    }
}
