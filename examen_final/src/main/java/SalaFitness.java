import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalaFitness extends Newsletter {
    private static SalaFitness instance;
    private static Newsletter newsletter = new Newsletter();

    public static List<Antrenor> antrenori = new ArrayList<>();
    private static List<Abonat> abonati = new ArrayList<>();
    private static List<Persoana> sala = new ArrayList<>();
    private static Queue<Persoana> asteptare = new ArrayDeque<>();

    public static Antrenor antrenorCurent = null;
    private static Abonat abonatCurent = null;

    private SalaFitness() {

    }

    public static SalaFitness getInstance() {
        synchronized (SalaFitness.class) {
            if (instance == null) {
                instance = new SalaFitness();
            }
        }
        return instance;
    }

    public void signUpAbonat(String email, String nume, int progres, String password, String confirmationPassword) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (abonatDejaExistent(email, nume)) {
                bufferedWriter.write("Abonat deja existent");
                bufferedWriter.newLine();
            } else if (parolaDiferita(password, confirmationPassword)) {
                bufferedWriter.write("Parole diferite, nu se poate face adaugarea");
                bufferedWriter.newLine();
            } else if (parolaScurta(password)) {
                bufferedWriter.write("Parola prea scurta");
                bufferedWriter.newLine();
            } else if (!emailValid(email)) {
                bufferedWriter.write("Adresa de email invalida");
                bufferedWriter.newLine();
            } else if (!emailUnicAbonat(email)) {
                bufferedWriter.write("Adresa de email este deja utilizata");
                bufferedWriter.newLine();
            } else {
                Abonat abonat = new Abonat(email, nume, progres, password);
                abonati.add(abonat);
                bufferedWriter.write("Abonatul " + nume + " a fost adaugat!");
                bufferedWriter.newLine();

            }
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signUpAntrenor(String email, String nume, int max_abonati, String password, String confirmationPassword) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (antrenorDejaExistent(email, nume)) {
                bufferedWriter.write("Antrenor deja existent");
                bufferedWriter.newLine();
            } else if (parolaDiferita(password, confirmationPassword)) {
                bufferedWriter.write("Parole diferite, nu se poate face adaugarea");
                bufferedWriter.newLine();
            } else if (parolaScurta(password)) {
                bufferedWriter.write("Parola prea scurta");
                bufferedWriter.newLine();
            } else if (!emailValid(email)) {
                bufferedWriter.write("Adresa de email invalida");
                bufferedWriter.newLine();
            } else if (!emailUnicAntrenor(email)) {
                bufferedWriter.write("Adresa de email este deja utilizata");
                bufferedWriter.newLine();
            } else {
                Antrenor antrenor = new Antrenor(email, nume, max_abonati, password);
                antrenori.add(antrenor);
                bufferedWriter.write("Antrenorul " + nume + " a fost adaugat!");
                bufferedWriter.newLine();


            }
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginAbonat(String email, String password) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (abonatCurent != null) {
                bufferedWriter.write("Alt abonat deja conectat");
                bufferedWriter.newLine();
            } else if (emailUnicAbonat(email)) {
                bufferedWriter.write("Abonatul nu exista");
                bufferedWriter.newLine();
            } else {
                if (!parolaCorectaAbonat(email, password)) {
                    bufferedWriter.write("Parola incorecta");
                    bufferedWriter.newLine();
                } else {
                    for (Abonat x : abonati) {
                        if (x.getEmail().equals(email)) {
                            abonatCurent = x;
                        }
                    }
                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loginAntrenor(String email, String password) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (antrenorCurent != null) {
                bufferedWriter.write("Alt antrenor deja conectat");
                bufferedWriter.newLine();
            } else if (emailUnicAntrenor(email)) {
                bufferedWriter.write("Antrenorul nu exista");
                bufferedWriter.newLine();
            } else {
                if (!parolaCorectaAntrenor(email, password)) {
                    bufferedWriter.write("Parola incorecta");
                    bufferedWriter.newLine();
                } else {
                    for (Antrenor x : antrenori) {
                        if (x.getEmail().equals(email)) {
                            antrenorCurent = x;
                        }
                    }

                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logoutAbonat(String email) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (abonatCurent==null||!(abonatCurent.getEmail().equals(email))) {
                bufferedWriter.write("Abonatul nu era conectat");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }else abonatCurent = null;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logoutAntrenor(String email) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (antrenorCurent==null||!(antrenorCurent.getEmail().equals(email))) {
                bufferedWriter.write("Antrenorul nu era conectat");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }else antrenorCurent = null;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void incrementProgres(int value) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (abonatCurent == null) {
                bufferedWriter.write("Nu exista niciun abonat logat");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } else {
                for (Abonat x : abonati) {
                    if (x.getEmail().equals(abonatCurent.getEmail()) && x.getProgres() + value <= 10) {
                        x.setProgres(x.getProgres() + value);
                    } else {
                        int y = x.getProgres() + value;
                        bufferedWriter.write("Nu se poate face incrementarea. Progresul total ar fi " + y);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void decrementProgres(int value) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (abonatCurent == null) {
                bufferedWriter.write("Nu exista niciun abonat logat");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } else {
                for (Abonat x : abonati) {
                    if (x.getEmail().equals(abonatCurent.getEmail()) && x.getProgres() - value >= 0) {
                        x.setProgres(x.getProgres() - value);
                    } else {
                        int y = x.getProgres() - value;
                        bufferedWriter.write("Nu se poate face decrementarea. Progresul total ar fi " + y);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adaugaAntrenor(String email) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (abonatCurent == null) {
                bufferedWriter.write("Nu exista niciun abonat logat");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                return;
            }
            if (emailUnicAntrenor(email)) {
                bufferedWriter.write("Nu exista antrenorul cu emailul " + email);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                return;
            }

            for (Antrenor x : antrenori) {
                if (x.getEmail().equals(email)) {
                    if (x.getNrMaxCursanti() == x.getAbonatiAntrenor().size()) {
                        bufferedWriter.write("Antrenorul nu mai are locuri libere");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else {

                        List<Abonat> abonatiAntrenor = x.getAbonatiAntrenor();
                        abonatiAntrenor.add(abonatCurent);
                        x.setAbonatiAntrenor(abonatiAntrenor);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void vizualizareAbonatiiMei() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (antrenorCurent == null) {
                bufferedWriter.write("Nu exista niciun antrenor logat");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                return;
            }
            antrenorCurent.getAbonatiAntrenor().stream()
                    .forEach(el -> {
                        try {
                            bufferedWriter.write(el.toString());
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean abonatDejaExistent(String email, String nume) {
        if (abonati.stream().anyMatch(value -> value.getNume().equals(nume)) && abonati.stream().anyMatch(value -> value.getEmail().equals(email))) {
            return true;
        } else return false;
    }

    public boolean antrenorDejaExistent(String email, String nume) {
        if (antrenori.stream().anyMatch(value -> value.getNume().equals(nume)) && antrenori.stream().anyMatch(value -> value.getEmail().equals(email))) {
            return true;
        } else return false;
    }

    public boolean parolaDiferita(String password, String confirmationPassword) {
        if (password.equals(confirmationPassword)) {
            return false;
        } else return true;
    }

    public boolean parolaScurta(String password) {
        if (password.length() < 8) {
            return true;
        } else return false;
    }

    public boolean emailValid(String email) {
        String[] split = email.split("@");
        if (!(split.length == 2)) {
            return false;
        } else {
            String verificare2 = split[1];
            if (!(verificare2.contains("."))) {
                return false;
            }
        }
        return true;
    }

    private boolean emailUnicAbonat(String email) {
        if (abonati.stream().anyMatch(value -> value.getEmail().equals(email))) {
            return false;
        } else return true;
    }

    private boolean emailUnicAntrenor(String email) {
        if (antrenori.stream().anyMatch(value -> value.getEmail().equals(email))) {
            return false;
        } else return true;
    }

    private boolean parolaCorectaAbonat(String email, String password) {

        for (Abonat x : abonati) {
            if (x.getEmail().equals(email)) {
                if(x.getPassword().equals(password)){
                    return true;
                }else return false;
            }
        }
        System.out.println("aici se ajunge?");
        return false;
    }


    private boolean parolaCorectaAntrenor(String email, String password) {
        for (Antrenor x : antrenori) {
            if (x.getEmail().equals(email) && x.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void subscribeAbonat() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (abonatCurent == null) {
                bufferedWriter.write("Nu exista niciun abonat logat");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } else {
                abonatCurent.subscribe(newsletter);
                bufferedWriter.write("Abonatul cu adresa de email " + abonatCurent.getEmail() + "a fost abonat la newsletter");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void subscribeAntrenor() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            if (antrenorCurent == null) {
                bufferedWriter.write("Nu exista niciun antrenor logat");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } else {
                antrenorCurent.subscribe(newsletter);
                bufferedWriter.write("Antrenorul cu adresa de email " + antrenorCurent.getEmail() + "a fost abonat la newsletter");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void adaugaNews(String news) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            bufferedWriter.write("A fost adaugata stirea cu mesajul " + news);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            newsletter.notifyObservers(news);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void intraInSala(String email) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            for (Persoana x : sala) {
                if (x.email.equals(email)) {
                    bufferedWriter.write("Persoana cu adresa de email " + email + " este deja in sala");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    return;
                }
            }
            for (Abonat x : abonati) {
                if (x.getEmail().equals(email)) {
                    if (sala.size() < 10) {
                        sala.add(x);
                        List<LocalDateTime> intrariSala = x.getIntrariSala();
                        intrariSala.add(LocalDateTime.now());
                        x.setIntrariSala(intrariSala);
                    } else {
                        bufferedWriter.write("Sala este deja plina! Sunteti pus in coada de asteptare");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        asteptare.add(x);
                    }
                    return;
                }

            }
            for (Antrenor x : antrenori) {
                if (x.getEmail().equals(email)) {
                    if (sala.size() < 10) {
                        sala.add(x);
                        List<LocalDateTime> intrariSala = x.getIntrariSala();
                        intrariSala.add(LocalDateTime.now());
                        x.setIntrariSala(intrariSala);
                    } else {
                        bufferedWriter.write("Sala este deja plina! Sunteti pus in coada de asteptare");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        asteptare.add(x);
                    }
                    return;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void ieseDinSala(String email) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            int i = sala.size();
            int z = 0;
            while (z<=i){
                if (sala.get(z).email.equals(email)){
                    sala.remove(z);
                    z=i;
                    if (asteptare.size() > 0) {
                        Persoana a = asteptare.poll();
                        sala.add(a);
                    }
                    return;
                }  else z++;
            }
                    bufferedWriter.write("Persoana cu adresa de email " + email + " nu este in sala" + '\'');
                    bufferedWriter.flush();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void vizualizarePersoaneAntrenor() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));

            for(Antrenor x:antrenori){
                bufferedWriter.write(x.email+": ");
                String collect = x.getAbonatiAntrenor().stream()
                        .map(el -> el.getEmail())
                        .collect(Collectors.joining(","));
                bufferedWriter.write(collect);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void vizualizareAbonati() {
        Comparator<Abonat> comparator1 = (el1,el2)->(el2.getProgres()-el1.getProgres());
        Comparator<Abonat> comparator2 = (el1,el2)->(el1.getNume().compareTo(el2.getNume()));
        Comparator<Abonat> comparator = comparator1.thenComparing(comparator2);

        Stream<Abonat> sorted = abonati.stream()
                .sorted(comparator);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            sorted.forEach(el-> {
                try {
                    bufferedWriter.write(el.toString());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void vizualizareAntrenori() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));


        Comparator<Antrenor> comparator1 = (el1,el2)->(el1.getAbonatiAntrenor().size()-el2.getAbonatiAntrenor().size());
        Comparator<Antrenor> comparator2 = (el1,el2)->(el1.getNume().compareTo(el2.getNume()));
        Comparator<Antrenor> comparator = comparator1.thenComparing(comparator2);

        Stream<Antrenor> sorted = antrenori.stream()
                .sorted(comparator);

        sorted.forEach(el-> {
            try {
                bufferedWriter.write(el.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistaAbonati(){
        String ulr = "jdbc:mysql://localhost:3306/examen_final";
        try (Connection connection = DriverManager.getConnection(ulr, "root", "")) {
            Statement statement = connection.createStatement();
            for (Abonat x:abonati){
                String insertStatement = "INSERT INTO abonati VALUES  ("+x.getId()+",'"+x.getEmail()+"','"+x.getNume()+"','"+x.getPassword()+"',"+x.getProgres()+")";
                statement.executeUpdate(insertStatement);
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            bufferedWriter.write("Abonatii au fost salvati in baza de date");
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void persistaAntrenori(){
        String ulr = "jdbc:mysql://localhost:3306/examen_final";
        try (Connection connection = DriverManager.getConnection(ulr, "root", "")) {
            Statement statement = connection.createStatement();
            for (Antrenor x:antrenori){
                String insertStatement = "INSERT INTO antrenori VALUES  ("+x.getId()+",'"+x.getEmail()+"','"+x.getNume()+"','"+x.getPassword()+"',"+x.getAbonatiAntrenor().size()+")";
                statement.executeUpdate(insertStatement);
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/out2.txt", true));
            bufferedWriter.write("Antrenorii au fost salvati in baza de date");
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}