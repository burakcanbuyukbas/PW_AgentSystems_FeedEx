package Models;

public interface MapsEvent<T> {
    void handle(T event);
}
