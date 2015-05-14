package guiLayer;

import java.util.function.Supplier;

public class Wrapper<T> {

	private T obj;
	
	private Supplier<String> f;
	
	public Wrapper(T obj, Supplier<String> f) {
		this.obj = obj;
		this.f = f;
	}
	
	@Override
	public String toString() {
		return f.get();
	}
	
	public T getObject() {
		return obj;
	}
}
