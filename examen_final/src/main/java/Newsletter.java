import java.util.HashSet;
import java.util.Set;

public class Newsletter implements Subject{

    private Set<Observer> abonatiNewsletter = new HashSet<>();
    private Set<Observer> antrenoriNewsletter = new HashSet<>();

    @Override
    public void addAbonat(Observer observer) {
        abonatiNewsletter.add(observer);
    }

    @Override
    public void addAntrenor(Observer observer) {
        antrenoriNewsletter.add(observer);
    }

    @Override
    public void removeAbonat(Observer observer) {
        abonatiNewsletter.remove(observer);
    }

    @Override
    public void removeAntrenor(Observer observer) {
        antrenoriNewsletter.remove(observer);
    }

    @Override
    public void notifyObservers(String news) {
        antrenoriNewsletter.forEach(el->el.update(news));
        abonatiNewsletter.forEach(el->el.update(news));
    }
}
