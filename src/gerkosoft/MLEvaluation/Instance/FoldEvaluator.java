package gerkosoft.MLEvaluation.Instance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gerkosoft.MLEvaluation.Interfaces.Instance;
import gerkosoft.MLEvaluation.Interfaces.Learner;

public class FoldEvaluator<TLearner extends Learner<TInstance, TLabel>, TInstance extends Instance<TLabel>, TLabel>
extends AbstractFoldEvaluator<TLearner, TInstance, TLabel> {
	@Override
	protected List<List<TInstance>> generateFolds(Collection<TInstance> dataset, int quantityOfFolds) {
		List<TInstance> randomOrderDataset=new ArrayList<TInstance>(dataset);
		java.util.Collections.shuffle(randomOrderDataset);
		List<List<TInstance>> folds=new ArrayList<List<TInstance>>();
		for(int i=0;i<quantityOfFolds;i++){
			folds.add(new ArrayList<TInstance>());
		}
		for(int instanceIndex=0;instanceIndex<randomOrderDataset.size();instanceIndex++){
			folds.get(instanceIndex%quantityOfFolds).add(randomOrderDataset.get(instanceIndex));
		}
		return folds;
	}

}
