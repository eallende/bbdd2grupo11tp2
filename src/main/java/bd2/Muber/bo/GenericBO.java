package bd2.Muber.bo;

import java.util.List;


public interface GenericBO<T> {
	
	public T get(Long id);
	public T save(T entity);
	public T update(T entity);
	public List<T> getAll();

}
