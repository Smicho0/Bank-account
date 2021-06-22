package tvz.hr.priprema.x;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException, RacunVecPostojiException, NedozvoljenoStanjeStednogRacunaException {

        Scanner unos = new Scanner(System.in);

        double ukupnoStanjePrvo = 0;
        double ukupnoStanjeDrugo = 0;

        Osoba klijentPrvi = new Osoba();
        Osoba klijentDrugi = new Osoba();
        TekuciRacun tekuciRacunPrvi = new TekuciRacun(1,"xxx1" , "HRK", false, "12/01/2012", 1000);
        TekuciRacun tekuciRacunDrugi = new TekuciRacun(1,"xxx2" ,"EUR", false, "01/01/1998", 2000);
        StedniRacun stedniRacunPrvi = new StedniRacun(1,"xxx3","HRK", true, "09/09/2009", 1.1, 14);
        StedniRacun stedniRacunDrugi = new StedniRacun(1,"xxx4" ,"EUR", true, "11/11/2011", 1.2, 16);

        System.out.println("Unesite informacije o prvoj osobi");
        noviKlijent(klijentPrvi, tekuciRacunPrvi, stedniRacunPrvi);

        System.out.println("\nUnesite informacije o drugoj osobi");
        noviKlijent(klijentDrugi, tekuciRacunDrugi, stedniRacunDrugi);

        boolean provjera = false;
        BigDecimal prijenosIznosaPlace = null;
        do {
            try {
                provjera = false;
                System.out.print("Unesite iznos place: ");
                prijenosIznosaPlace = unos.nextBigDecimal();
                if (prijenosIznosaPlace.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new NeispravanIznosTransakcijeException("Samo brojevi veci od 0!");
                }
                else {
                    prijenosIznosaPlace = prijenosIznosaPlace;
                }
            }
            catch (NeispravanIznosTransakcijeException e) {
                System.out.println(e.getMessage());
                provjera = true;
            }
        } while(provjera && prijenosIznosaPlace.compareTo(BigDecimal.ZERO) <= 0);


        IsplataPlace ip = new IsplataPlace(tekuciRacunPrvi, tekuciRacunDrugi, prijenosIznosaPlace);
        ip.izvrsi();

        BigDecimal prijenosIznosaStednje = null;
        do {
            try {
                provjera = false;
                System.out.print("Unesite iznos stednje: ");
                prijenosIznosaStednje = unos.nextBigDecimal();
                if (prijenosIznosaStednje.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new NeispravanIznosTransakcijeException("Samo brojevi veci od 0!");
                }
                else {
                    prijenosIznosaStednje = prijenosIznosaStednje;
                }
            }
            catch (NeispravanIznosTransakcijeException e) {
                System.out.println(e.getMessage());
                provjera = true;
            }
        } while(provjera && prijenosIznosaStednje.compareTo(BigDecimal.ZERO) <= 0);

        BigDecimal kamata = null;
        do {
            try {
                provjera = false;
                System.out.print("Unesite iznos kamate: ");
                kamata = unos.nextBigDecimal();
                if (kamata.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new NeispravanIznosTransakcijeException("Samo brojevi veci od 0!");
                }
                else {
                    kamata = kamata;
                }
            }
            catch (NeispravanIznosTransakcijeException e) {
                System.out.println(e.getMessage());
                provjera = true;
            }
        } while(provjera && kamata.compareTo(BigDecimal.ZERO) <= 0);


        UplataStednje up = new UplataStednje(stedniRacunPrvi, stedniRacunDrugi, prijenosIznosaStednje, kamata);
        up.izvrsi();


        for(int i = 0; i < klijentPrvi.getRacuni().size(); i++) {
            if (tekuciRacunPrvi.getValuta().contains("EUR") && stedniRacunPrvi.getValuta().contains("EUR")) {
                ukupnoStanjePrvo = ukupnoStanjePrvo + klijentPrvi.getRacuni().get(i).getStanje() * 7.54;
            }
            else if (tekuciRacunPrvi.getValuta().contains("HRK") && stedniRacunPrvi.getValuta().contains("HRK")) {
                ukupnoStanjePrvo = ukupnoStanjePrvo + klijentPrvi.getRacuni().get(i).getStanje();
            }
            else {
                System.out.println("Rijesenje privremeno nije moguce izracunati jer valute racuna nistu iste.");
            }

        }

        for (int i = 0; i < klijentDrugi.getRacuni().size(); i++) {
            if (tekuciRacunDrugi.getValuta().contains("EUR") && stedniRacunDrugi.getValuta().contains("EUR")) {
                ukupnoStanjeDrugo = ukupnoStanjeDrugo + klijentDrugi.getRacuni().get(i).getStanje() * 7.54;
            } else if (tekuciRacunDrugi.getValuta().contains("HRK") && stedniRacunDrugi.getValuta().contains("HRK")) {
                ukupnoStanjeDrugo = ukupnoStanjeDrugo + klijentDrugi.getRacuni().get(i).getStanje();
            } else {
                System.out.println("Rijesenje privremeno nije moguce izracunati jer valute racuna nistu iste.");
            }
        }

        System.out.println("Informacije o prvom klijentu: " + klijentPrvi);
        System.out.println("  " + tekuciRacunPrvi);
        System.out.println("  " + stedniRacunPrvi);
        String ukupnoStanjePrvoFormatirano = String.format("%.2f", ukupnoStanjePrvo);
        System.out.println("  Osoba ima ukupno " + ukupnoStanjePrvoFormatirano + " kuna na racunima.");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Informacije o drugom klijentu: " + klijentDrugi);
        System.out.println("  " + tekuciRacunDrugi);
        System.out.println("  " + stedniRacunDrugi);
        String ukupnoStanjeDrugoFormatirano = String.format("%.2f", ukupnoStanjeDrugo);
        System.out.println("  Osoba ima ukupno " + ukupnoStanjeDrugoFormatirano + " kuna na racunima.\n");

        if (ukupnoStanjePrvo > ukupnoStanjeDrugo) {
            System.out.println("Prva osoba ima vise sredstava na racunu.");
        } else if (ukupnoStanjeDrugo > ukupnoStanjePrvo) {
            System.out.println("Druga osoba ima vise sredstava na racunu");
        } else {
            System.out.println("Imaju jednaka sredstva na racunu.");
        }
    }

    private static void noviKlijent(Osoba klijent, TekuciRacun tekuciRacun, StedniRacun stedniRacun) throws ParseException, RacunVecPostojiException {
        Scanner unos = new Scanner(System.in);
        klijent.InfoOsoba(klijent.getImeOsobe(), klijent.getPrezimeOsobe());
        klijent.addOsoba(klijent);

        tekuciRacun.NewAccount(tekuciRacun.getBrojRacuna(), tekuciRacun.getStanje(), tekuciRacun.getValuta(), tekuciRacun.isInvesticijski());
        if(tekuciRacun.isInvesticijski()) {
            System.out.print("Unesite datum: ");
            tekuciRacun.promijeniImovinu(unos.nextLine(), tekuciRacun.getStanje());
        }
        klijent.addAccount(tekuciRacun);

        stedniRacun.NewAccount(stedniRacun.getBrojRacuna(), stedniRacun.getStanje(), stedniRacun.getValuta(), tekuciRacun.isInvesticijski());
        if(stedniRacun.isInvesticijski()) {
            System.out.print("Unesite datum: ");
            tekuciRacun.promijeniImovinu(unos.next(), stedniRacun.getStanje());
        }
        klijent.addAccount(stedniRacun);
    }
}