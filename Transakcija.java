package tvz.hr.priprema.x;

import java.math.BigDecimal;

abstract class Transakcija {
    Racun racunPrvi;
    Racun racunDrugi;

    BigDecimal prijenosIznosa;

    public Transakcija(Racun racunPrvi, Racun racunDrugi, BigDecimal prijenosIznosa) {
        this.racunPrvi = racunPrvi;
        this.racunDrugi = racunDrugi;
        this.prijenosIznosa = prijenosIznosa;
    }

    public abstract void izvrsi();
}
