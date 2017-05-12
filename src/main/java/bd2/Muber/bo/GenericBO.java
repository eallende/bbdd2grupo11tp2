package bd2.Muber.bo;

public interface GenericBO<T> {
	
	public T get(Long id);
	public T save(T entity);
	public T update(T entity);

}
