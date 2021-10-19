import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Antrenor extends Persoana implements Observer{

    private int id=0;
    private int nrMaxCursanti;
    private List<Abonat>abonatiAntrenor;
    private String password;
    private static int increment = 0;


    public Antrenor(String email, String nume, int nrMaxCursanti, String password) {
        super(email,nume);
        this.nrMaxCursanti = nrMaxCursanti;
        this.id = increment++;
        this.password = password;
        this.abonatiAntrenor = new ArrayList<>();
        this.intrariSala = new ArrayList<>();
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

    public void setId(int id) {
        this.id = id;
    }

    public int getNrMaxCursanti() {
        return nrMaxCursanti;
    }

    public List<Abonat> getAbonatiAntrenor() {
        return abonatiAntrenor;
    }

    public void setAbonatiAntrenor(List<Abonat> abonati) {
        this.abonatiAntrenor = abonati;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Antrenor{" +
                "email='" + email +
                ", nume='" + nume + '\'' +
                ", id=" + id + '\'' +
                ", nrMaxCursanti=" + nrMaxCursanti +
                ", abonatiAntrenor=" + abonatiAntrenor +
                ", password='" + password + '\'' +

                ", intrariSala=" + intrariSala +
                '}';
    }

    @Override
    public void subscribe(Subject subject) {
        subject.addAntrenor(this);
    }


    @Override
    public void update(String news) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            bufferedWriter.write("A fost notificata persoana cu adresa de email "+this.getEmail()+"de stirea "+news);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
