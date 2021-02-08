import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //SalaFitness.getInstance().signUpAbonat("be.obreja@gmail.com", "Bogdan", 5, "Numaivreaupot", "Numaivreaupot");
        //SalaFitness.getInstance().loginAbonat("be.obreja@gmail.com", "Numaivreaupot");
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/in.txt"));
            String read = br.readLine();
            while (read !=null){
                String[] cuvinte = read.split(" ");
                switch (cuvinte[0]){
                    case "SIGNUP_ABONAT": SalaFitness.getInstance().signUpAbonat(cuvinte[1],cuvinte[2], Integer.parseInt(cuvinte[3]), cuvinte[4], cuvinte[5] );
                    break;
                    case "SIGNUP_ANTRENOR": SalaFitness.getInstance().signUpAntrenor(cuvinte[1],cuvinte[2], Integer.parseInt(cuvinte[3]), cuvinte[4], cuvinte[5] );
                    break;
                    case "LOGIN_ABONAT": SalaFitness.getInstance().loginAbonat(cuvinte[1], cuvinte[2]);
                    break;
                    case "LOGIN_ANTRENOR": SalaFitness.getInstance().loginAntrenor(cuvinte[1], cuvinte[2]);
                    break;
                    case "LOGOUT_ABONAT": SalaFitness.getInstance().logoutAbonat(cuvinte[1]);
                    break;
                    case "LOGOUT_ANTRENOR": SalaFitness.getInstance().logoutAntrenor(cuvinte[1]);
                    break;
                    case "INCREMENT_PROGRES": SalaFitness.getInstance().incrementProgres(Integer.parseInt(cuvinte[1]));
                    break;
                    case "DECREMENT_PROGRES": SalaFitness.getInstance().decrementProgres(Integer.parseInt(cuvinte[1]));
                    break;
                    case "ADAUGA_ANTRENOR": SalaFitness.getInstance().adaugaAntrenor(cuvinte[1]);
                    break;
                    case "VIZUALIZARE_ABONATII_MEI": SalaFitness.getInstance().vizualizareAbonatiiMei();
                    break;
                    case "SUBSCRIBE_ABONAT": SalaFitness.getInstance().subscribeAbonat();
                    break;
                    case "SUBSCRIBE_ANTRENOR": SalaFitness.getInstance().subscribeAntrenor();
                    break;
                    case "ADAUGA_NEWS": SalaFitness.getInstance().adaugaNews(cuvinte[1]);
                    break;
                    case "INTRA_IN_SALA": SalaFitness.getInstance().intraInSala(cuvinte[1]);
                    break;
                    case "IESE_DIN_SALA": SalaFitness.getInstance().ieseDinSala(cuvinte[1]);
                    break;
                    case "VIZUALIZARE_PERSOANE_CU_ANTRENOR": SalaFitness.getInstance().vizualizarePersoaneAntrenor();
                    break;
                    case "VIZUALIZARE_ABONATI": SalaFitness.getInstance().vizualizareAbonati();
                    break;
                    case "VIZUALIZARE_ANTRENORI": SalaFitness.getInstance().vizualizareAntrenori();
                    break;
                    case "PERSISTA_ABONATI": SalaFitness.getInstance().persistaAbonati();
                    break;
                    case "PERSISTA_ANTRENORI": SalaFitness.getInstance().persistaAntrenori();
                    break;
                    default:
                        System.out.println("Comanda indisponibila");

                }
                read = br.readLine();
            }
            br.close();
    } catch (IOException e) {
            e.printStackTrace();
        }
    }}
