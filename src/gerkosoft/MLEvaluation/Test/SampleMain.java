package gerkosoft.MLEvaluation.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import gerkosoft.MLEvaluation.Instance.FoldEvaluator;
import gerkosoft.MLEvaluation.Instance.MonteCarloDistributor;
import gerkosoft.MLEvaluation.Interfaces.Evaluator;
import gerkosoft.MLEvaluation.Interfaces.Factory;
import gerkosoft.MLEvaluation.Interfaces.Learner;

public class SampleMain {

	public static void main(String[] args) {
		int qtyOfFeatures = 100;
		Evaluator<BasicInstance, Boolean> evaluator = new FoldEvaluator<BasicInstance, Boolean>(
				new MonteCarloDistributor<BasicInstance>());
		System.out.println(evaluator.evaluate(new Factory<Learner<BasicInstance, Boolean>>() {
			@Override
			public BasicInstanceWinnow generateNewInstance() {
				return new BasicInstanceWinnow(qtyOfFeatures);
			}
		}, generateDataset(1000, qtyOfFeatures), 5).toString());
		System.out.println(evaluator.evaluate(new Factory<Learner<BasicInstance, Boolean>>() {
			@Override
			public GuessClassifier<BasicInstance> generateNewInstance() {
				return new GuessClassifier<BasicInstance>();
			}
		}, generateDataset(1000, qtyOfFeatures), 5).toString());
	}

	private static Collection<BasicInstance> generateDataset(int qty, int size) {
		List<BasicInstance> dataset = new ArrayList<BasicInstance>();
		Random r = new Random();
		while (qty > 0) {
			boolean[] features = new boolean[size];
			for (int i = 0; i < size; i++) {
				features[i] = r.nextBoolean();
			}
			dataset.add(new BasicInstance(features));
			qty--;
		}
		return dataset;
	}
}
