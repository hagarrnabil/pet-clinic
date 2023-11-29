package guru.springframework.petclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findAll();

    T save(T object);

    T findByID(ID id);

    void delete(T object);

    void deleteByID(ID id);

}
