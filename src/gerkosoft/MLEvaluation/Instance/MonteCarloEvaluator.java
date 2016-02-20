package gerkosoft.MLEvaluation.Instance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import gerkosoft.MLEvaluation.Interfaces.Instance;
import gerkosoft.MLEvaluation.Interfaces.Learner;

public class MonteCarloEvaluator<TLearner extends Learner<TInstance, TLabel>, TInstance extends Instance<TLabel>, TLabel>
extends AbstractFoldEvaluator<TLearner, TInstance, TLabel>{
	protected List<List<TInstance>> generateFolds(Collection<TInstance> dataset, int quantityOfFolds){
		Random r=new Random();
		List<List<TInstance>> folds=new ArrayList<List<TInstance>>();
		for(int i=0;i<quantityOfFolds;i++){
			folds.add(new ArrayList<TInstance>());
		}
		for(TInstance instance : dataset){
			folds.get(r.nextInt(quantityOfFolds)).add(instance);
		}
		return folds;
	}
}
