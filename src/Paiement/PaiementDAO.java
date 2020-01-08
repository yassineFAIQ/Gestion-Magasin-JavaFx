package Paiement;
import java.util.List;

public interface PaiementDAO<T> {
public boolean create(T p);
public T getOne(long id);
public boolean delete(long id);
public boolean update(T p);
public List<T> getAll();
public List<T> getAll(String des);
List<Paiement> getAll(long id);

}
