package gerkosoft.MLEvaluation.Instance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gerkosoft.MLEvaluation.Interfaces.Evaluator;
import gerkosoft.MLEvaluation.Interfaces.Factory;
import gerkosoft.MLEvaluation.Interfaces.Instance;
import gerkosoft.MLEvaluation.Interfaces.Learner;

public abstract class AbstractFoldEvaluator<TLearner extends Learner<TInstance, TLabel>, TInstance extends Instance<TLabel>, TLabel>
implements Evaluator<TLearner, TInstance, TLabel>{

	@Override
	public ConfusionMatrix<TInstance, TLabel> evaluate(Factory<TLearner> learnerFactory, Collection<TInstance> dataset, int quantityOfFolds) {
		List<List<TInstance>> folds=generateFolds(dataset, quantityOfFolds);
		ConfusionMatrix<TInstance,TLabel> confusionMatrix=new ConfusionMatrix<TInstance, TLabel>();
		for(int i=0;i<folds.size();i++){
			evaluateFold(learnerFactory, folds, i, confusionMatrix);
		}
		return confusionMatrix;
	}
	
	protected abstract List<List<TInstance>> generateFolds(Collection<TInstance> dataset, int quantityOfFolds);
	
	private void evaluateFold(Factory<TLearner> learnerFactory, List<List<TInstance>> folds, int selectedTestingFoldIndex, ConfusionMatrix<TInstance, TLabel> confusionMatrix){
		List<TInstance> trainingData=new ArrayList<TInstance>();
		List<TInstance> testingData=new ArrayList<TInstance>();
		TLearner learner=learnerFactory.generateNewInstance();
		for(int i=0;i<folds.size();i++) if(i!=selectedTestingFoldIndex){
			trainingData.addAll(folds.get(i));
		}
		testingData.addAll(testingData);
		learner.learn(trainingData);
		for(TInstance i : testingData){
			confusionMatrix.AddToMatrix(i, learner.predict(i));
		}
	}
}