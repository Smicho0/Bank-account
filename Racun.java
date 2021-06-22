package tvz.hr.priprema.x;



import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Racun implements Devizni, Investicijski {
    private double stanje;
    private String brojRacuna;
    private String valuta;
    private boolean investicijski;
    private String datum;

    public Racun(double stanje, String brojRacuna, String valuta, boolean investicijski, String datum) throws RacunVecPostojiException  {
        if (stanje < 0) {
            throw new IllegalArgumentException("Nije moguce unijeti negativan iznos!");
        }
        else {
            this.stanje = stanje;
        }

        if(RacunVecPostojiException.provjeraPostojecegRacuna(brojRacuna)) {
            this.brojRacuna = brojRacuna;
            RacunVecPostojiException.noviRacun(brojRacuna);
        }
        else {
            throw new RacunVecPostojiException("Racun vec postoji!");
        }
        this.valuta = valuta;
        this.investicijski = investicijski;
        this.datum = datum;
    }

    public boolean isInvesticijski() {
        return investicijski;
    }

    public void setInvesticijski(boolean investicijski) {
        this.investicijski = investicijski;
    }

    public double getStanje() {
        return stanje;
    }

    public void setStanje(double stanje) {
        this.stanje = stanje;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }



    public void NewAccount(String brojRacuna, double stanje, String valuta, boolean investicijski) throws RacunVecPostojiException {
        Scanner unos = new Scanner(System.in);

        System.out.print("Unesite (true) za investicijki tip racuna, ako ne zelite taj tip racuna upisite (false): ");
        this.investicijski = unos.nextBoolean();
        unos.nextLine();

        if (!this.investicijski && TekuciRacun.class.isAssignableFrom(getClass())) {
            System.out.println("Unesite informacije o tekucem racunu");
        }
        if (!this.investicijski && StedniRacun.class.isAssignableFrom(getClass())) {
            System.out.println("Unesite informacije o stednom racunu");
        }

        if (this.investicijski && TekuciRacun.class.isAssignableFrom(getClass())) {
            System.out.println("Unesite informacije o investicijskom tekucem racunu");
        }

        if (this.investicijski && StedniRacun.class.isAssignableFrom(getClass())) {
            System.out.println("Unesite informacije o investicijskom stednom racunu");
        }

        boolean provjera = false;
        do {

            try {
                System.out.print("Unesite broj racuna: ");
                brojRacuna = unos.nextLine();

                if (RacunVecPostojiException.provjeraPostojecegRacuna(brojRacuna)) {
                    this.brojRacuna = brojRacuna;
                    RacunVecPostojiException.noviRacun(brojRacuna);
                    provjera = false;

                } else {
                    throw new RacunVecPostojiException("Racun vec postoji!");
                }
            }
                catch (RacunVecPostojiException e) {
                    System.out.println(e.getMessage());
                    provjera = true;
                }

        }while(provjera);

        provjera = false;
        do {
            try {
                provjera = false;
                System.out.print("Unesite stanje racuna: ");
                stanje = unos.nextDouble();
                if (stanje < 0) {
                    if (TekuciRacun.class.isAssignableFrom(getClass())) {
                        throw new IllegalArgumentException("Samo pozitivni brojevi!");
                    }
                    if (StedniRacun.class.isAssignableFrom(getClass())) {
                        throw new NedozvoljenoStanjeStednogRacunaException("Nedozvoljeno stanje racuna");
                    }
                }
                else {
                    this.stanje = stanje;
                }
            }
            catch (IllegalArgumentException | NedozvoljenoStanjeStednogRacunaException e) {
                System.out.println(e.getMessage());
                provjera = true;
            }
        } while(provjera && this.stanje > 0);
    }


    @Override
    public String toString() {
        return "Racun broj " + getBrojRacuna() + " sa stanjem " + getStanje();
    }

    @Override
    public BigDecimal konvertirajUKune(BigDecimal iznos) {
        return iznos.multiply(BigDecimal.valueOf(0.13));
    }

    @Override
    public BigDecimal konvertirajUDevize(BigDecimal stanjeBiD) {
        return BigDecimal.valueOf(stanjeBiD.doubleValue()/7.34);
    }

    @Override
    public void promijeniImovinu(String datum, double postotnaGlavnica) throws ParseException {
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        Date datumDan=format1.parse(datum);
        DateFormat formatDan=new SimpleDateFormat("EEE");
        String dan = formatDan.format(datumDan);

        if(dan.equals("Sun") || dan.equals("Sat")) {
            setStanje(postotnaGlavnica *= 1);
        }
        else if (dan.equals("Tue") || dan.equals("Thu")) {
            setStanje(postotnaGlavnica *= 1.01);
        }
        else {
            setStanje(postotnaGlavnica *= 0.99);
        }
    }
}
