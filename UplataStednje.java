package tvz.hr.priprema.x;

import java.math.BigDecimal;

public class UplataStednje extends Transakcija implements Kamatna {

    BigDecimal kamatna;
    public UplataStednje(StedniRacun racunPrvi, StedniRacun racunDrugi, BigDecimal prijenosIznosa, BigDecimal kamatna) {
        super(racunPrvi, racunDrugi, prijenosIznosa);
        this.kamatna = kamatna;
    }


    @Override
    public void izvrsi() {
        if (racunPrvi.getValuta().equals("HRK")) {
            racunDrugi.setStanje(racunPrvi.getStanje() + racunPrvi.konvertirajUDevize(prijenosIznosa).doubleValue());
            racunDrugi.setStanje(uvecajZaKamatu().doubleValue());
            racunPrvi.setStanje(racunPrvi.getStanje() - prijenosIznosa.doubleValue());
        }
        else if (racunDrugi.getValuta().equals("HRK")) {
            racunDrugi.setStanje(racunPrvi.getStanje() + racunPrvi.konvertirajUKune(prijenosIznosa).doubleValue());
            racunDrugi.setStanje(uvecajZaKamatu().doubleValue());
            racunPrvi.setStanje(racunPrvi.getStanje() - prijenosIznosa.doubleValue());
        }
        else {
            racunDrugi.setStanje(racunPrvi.getStanje() + prijenosIznosa.doubleValue());
            racunDrugi.setStanje(uvecajZaKamatu().doubleValue());
            racunPrvi.setStanje(racunPrvi.getStanje() - prijenosIznosa.doubleValue());
        }
    }

    @Override
    public BigDecimal uvecajZaKamatu() {
        return BigDecimal.valueOf(racunDrugi.getStanje()*kamatna.doubleValue());
    }

}
