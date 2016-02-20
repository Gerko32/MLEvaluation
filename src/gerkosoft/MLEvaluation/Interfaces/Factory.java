package gerkosoft.MLEvaluation.Interfaces;

/***
 * It is in charge of generating new instances of type T every time.
 * @author Gaston
 *
 * @param <T>
 */
public interface Factory<T> {
	T generateNewInstance();
}
