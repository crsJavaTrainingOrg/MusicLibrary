package hu.adam.sipos.music.domain.repository;

import hu.adam.sipos.music.domain.Library;

public interface LibraryRepository {
    Library load();

    void save(Library library);
}
