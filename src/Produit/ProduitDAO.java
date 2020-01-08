package Produit;
import java.util.List;

public interface ProduitDAO<T> {
public boolean create(T p);
public T getOne(long id);
public boolean delete(long id);
public boolean update(T p);
public List<T> getAll();
public List<T> getAll(String des);

}
