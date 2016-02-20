package gerkosoft.MLEvaluation.Interfaces;

/***
 * Labeled Instance
 * @author Gaston
 *
 * @param <TLabel> Type of the instance's label.
 */
public interface Instance<TLabel> {
	TLabel getLabel();
}
