package app.cupcake.persistence.daos;

import java.util.Set;

public interface iDAO<T> {
    public void create(T type);
    public T getById(int id);
    public Set<T> getAll();
    void update (int id);
}
