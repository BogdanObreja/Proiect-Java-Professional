import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Abonat extends Persoana implements Observer{

    private List<LocalDateTime> intrariSala;
    private int id;
    private int progres;
    private String password;
    private static int increment = 0;

    public String getPassword() {
        return password;
    }


    public Abonat(String email, String nume, int progres, String password) {
        super(email, nume);
        this.progres = progres;
        this.id = increment++;
        this.password = password;
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

    public int getProgres() {
        return progres;
    }

    public void setProgres(int progres) {
        this.progres = progres;
    }


    @Override
    public String toString() {
        return "Abonat{" +
                "email='" + email + '\'' +
                ", nume='" + nume + '\'' +
                ", intrariSala=" + intrariSala +
                ", id=" + id +
                ", progres=" + progres +
                '}';
    }


    @Override
    public void subscribe(Subject subject) {
        subject.addAbonat(this);
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
