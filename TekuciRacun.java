package tvz.hr.priprema.x;

public class TekuciRacun extends Racun {
    private double dozvoljeniMinus;

    public TekuciRacun(double stanje, String brojRacuna, String valuta, boolean investicijski, String datum, double dozvoljeniMinus) throws RacunVecPostojiException {
        super(stanje, brojRacuna, valuta, investicijski, datum);
        this.dozvoljeniMinus = dozvoljeniMinus;
    }

    public double getDozvoljeniMinus() {
        return dozvoljeniMinus;
    }

    public void setDozvoljeniMinus(double minus) {
        this.dozvoljeniMinus = minus;
    }

    @Override
    public String toString() {
        String stanjeFormatirano = String.format("%.2f", getStanje());
        return "Tekuci racun broj " + super.getBrojRacuna() + " ima ukupno stanje " + stanjeFormatirano + " " + super.getValuta() +".";
    }
}
