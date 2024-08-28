package app.cupcake.persistence;

import java.util.Set;

public interface iDAO<T> {
    public void create(T type);
    void delete(T type);
    public T getById(int id);
    public Set<T> getAll();
    void update (T type);
}
