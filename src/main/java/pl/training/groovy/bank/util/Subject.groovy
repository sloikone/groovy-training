package pl.training.groovy.bank.util

trait Subject<E> {

    private Set<Observer> observers = []

    void addObserver(Observer<E> observer) {
        observers << observer
    }

    void removeObserver(Observer<E> observer) {
        observers.remove(observer)
    }

    void notifyObservers(E event) {
        observers.each { it.onEvent(event) }
    }

}
