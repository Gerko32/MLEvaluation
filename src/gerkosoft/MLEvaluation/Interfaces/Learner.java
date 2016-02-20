package gerkosoft.MLEvaluation.Interfaces;

import java.util.Collection;

public interface Learner<TInstance, TLabel> {
	void learn(Collection<TInstance> trainingData);
	TLabel predict(TInstance testInput);
}
