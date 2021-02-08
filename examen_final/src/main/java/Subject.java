public interface Subject {
    void addAbonat (Observer observer);
    void addAntrenor (Observer observer);
    void removeAbonat (Observer observer);
    void removeAntrenor(Observer observer);
    void notifyObservers (String news);
}
