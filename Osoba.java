package tvz.hr.priprema.x;



import java.util.ArrayList;
import java.util.Scanner;

public class Osoba {
    private String ime;
    private String prezime;
    private ArrayList<Racun> racuni = new ArrayList<>();
    private ArrayList<Osoba> osobe = new ArrayList<>();


    public String getImeOsobe() {
        return ime;
    }

    public String getPrezimeOsobe() {
        return prezime;
    }

    public ArrayList<Racun> getRacuni() {
        return racuni;
    }

    public ArrayList<Osoba> getOsobe() {
        return osobe;
    }

    public void InfoOsoba(String imeOsobe, String prezimeOsobe) {
        Scanner unos = new Scanner(System.in);

        System.out.print("Unesite ime osobe: ");
        this.ime = unos.nextLine();
        System.out.print("Unesite prezime osobe: ");
        this.prezime = unos.nextLine();

    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void addOsoba(Osoba osoba) {
        this.osobe.add(osoba);
    }

    public void addAccount(Racun racun) {
        this.racuni.add(racun);
    }

    @Override
    public String toString() {
        return "\n  Ime: " + getImeOsobe() + "\n  Prezime: " + getPrezimeOsobe();
    }
}
