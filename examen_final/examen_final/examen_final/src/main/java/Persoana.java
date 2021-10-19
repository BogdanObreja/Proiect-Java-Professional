import java.time.LocalDateTime;
import java.util.List;

public class Persoana {
    protected String email;
    protected String nume;
    protected List<LocalDateTime> intrariSala;
    protected int id;


    public Persoana(String email, String nume) {
        this.email = email;
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public String getNume() {
        return nume;
    }

    public List<LocalDateTime> getIntrariSala() {
        return intrariSala;
    }

    public void setIntrariSala(List<LocalDateTime> intrariSala) {
        this.intrariSala = intrariSala;
    }

    public int getId() {
        return id;
    }
}
