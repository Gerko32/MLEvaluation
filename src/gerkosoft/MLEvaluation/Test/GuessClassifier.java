package gerkosoft.MLEvaluation.Test;

import java.util.Collection;

import gerkosoft.MLEvaluation.Interfaces.Learner;

public class GuessClassifier<TInstance> implements Learner<TInstance, Boolean> {
	public GuessClassifier() {
	}

	@Override
	public void learn(Collection<TInstance> trainingData) {
	}

	@Override
	public Boolean predict(TInstance testInput) {
		return true;
	}
}
