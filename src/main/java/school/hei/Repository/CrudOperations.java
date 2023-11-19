package school.hei.Repository;

import school.hei.Model.Author;

import java.util.List;

public interface CrudOperations<T> {
    List<T> findAll();

    List<T> saveAll(List<T> toSave);

    T save(T toSave);

    T delete(T toDelete);

    List<Author> saveAll(List<Author> toSave);
}
