package tvz.hr.priprema.x;

public class StedniRacun extends Racun {
    private double kamatnaStopa;
    private int brojMjeseciStednje;

    public StedniRacun(double stanje, String brojRacuna, String valuta, boolean investicijski, String datum, double kamatnaStopa, int brojMjeseciStednje) throws RacunVecPostojiException, NedozvoljenoStanjeStednogRacunaException{
        super(stanje, brojRacuna, valuta, investicijski, datum);
        if (stanje < 0) {
            throw new NedozvoljenoStanjeStednogRacunaException("Uneseno je nedozvoljeno stanje racuna, ");
        }
        this.kamatnaStopa = kamatnaStopa;
        this.brojMjeseciStednje = brojMjeseciStednje;
    }

    public double getKamatnaStopa() {
        return kamatnaStopa;
    }

    public int getBrojMjeseciStednje() {
        return brojMjeseciStednje;
    }

    public void setKamataPrinosa(double kamata) {
        this.kamatnaStopa = kamata;
    }

    public void setBrojMjeseciStednje(int mjeseci) {
        this.brojMjeseciStednje = mjeseci;
    }

    public double getStanje() {
        return super.getStanje() * getKamatnaStopa() * ((double) getBrojMjeseciStednje() / 12.0);
    }

    @Override
    public String toString() {
        String stanjeFormatirano = String.format("%.2f", getStanje());
        return "Stedni racun broj " + super.getBrojRacuna() + " ima ukupno stanje " + stanjeFormatirano + " " + super.getValuta() +".";
    }
}
