package hu.adam.sipos.music.domain.repository;

public interface AbstractRepository<T> {
    T load();

    void save(T object);
}
