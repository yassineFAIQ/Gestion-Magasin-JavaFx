package Cheque;
import java.util.List;

public interface ChequeDAO<T> {
public int create(T p);
public T getOne(long id);
public boolean delete(long id);
public boolean update(T p);
public List<T> getAll();
public List<T> getAll(String des);
Cheque getLast();

}
