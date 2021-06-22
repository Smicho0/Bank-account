package tvz.hr.priprema.x;

import java.math.BigDecimal;

public class IsplataPlace extends Transakcija {
    public IsplataPlace(TekuciRacun tekuciRacunPrvi, TekuciRacun tekuciRacunDrugi, BigDecimal prijenosIznosa) {
        super(tekuciRacunPrvi, tekuciRacunDrugi, prijenosIznosa);
    }

    @Override
    public void izvrsi() {
        if (racunPrvi.getValuta().equals("HRK")) {
            racunDrugi.setStanje(racunDrugi.getStanje()+racunPrvi.konvertirajUDevize(prijenosIznosa).doubleValue());
            racunPrvi.setStanje(racunPrvi.getStanje() - prijenosIznosa.doubleValue());
        }
        else if (racunDrugi.getValuta().equals("HRK")) {
            racunDrugi.setStanje(racunDrugi.getStanje()+racunPrvi.konvertirajUKune(prijenosIznosa).doubleValue());
            racunPrvi.setStanje(racunPrvi.getStanje() - prijenosIznosa.doubleValue());
        }
        else {
            racunDrugi.setStanje(racunDrugi.getStanje() + prijenosIznosa.doubleValue());
            racunPrvi.setStanje(racunPrvi.getStanje() - prijenosIznosa.doubleValue());
        }
    }


}
