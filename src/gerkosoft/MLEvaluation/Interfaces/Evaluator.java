package gerkosoft.MLEvaluation.Interfaces;

import java.util.Collection;

import gerkosoft.MLEvaluation.Instance.ConfusionMatrix;

/***
 * It evaluates a Learner over a labeled dataset.
 * @author Gaston
 *
 * @param <TLearner> Type of the learner
 * @param <TInstance> Type of the instances in the dataset
 * @param <TLabel> Type of the label
 */
public interface Evaluator<TLearner extends Learner<TInstance, TLabel>, TInstance extends Instance<TLabel>, TLabel> {
	/***
	 * It a k-fold validation and it returns its ConfusionMatrix
	 * @param learnerFactory a Functor that createsa new instance of a learner every time.
	 * @param dataset list of labeled instances
	 * @param quantityOfFolds quantity of folds
	 * @return ConfusionMatrix over all folds.
	 */
	ConfusionMatrix<TInstance, TLabel> evaluate(Factory<TLearner> learnerFactory, Collection<TInstance> dataset, int quantityOfFolds);
}
