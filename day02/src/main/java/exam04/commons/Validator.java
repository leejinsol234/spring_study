package exam04.commons;

public interface Validator<T> {
    void check(T t);
}