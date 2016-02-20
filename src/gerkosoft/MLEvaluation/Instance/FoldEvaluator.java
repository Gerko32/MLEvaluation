package gerkosoft.MLEvaluation.Instance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gerkosoft.MLEvaluation.Interfaces.DatasetDistributor;
import gerkosoft.MLEvaluation.Interfaces.Evaluator;
import gerkosoft.MLEvaluation.Interfaces.Factory;
import gerkosoft.MLEvaluation.Interfaces.Instance;
import gerkosoft.MLEvaluation.Interfaces.Learner;

public class FoldEvaluator<TInstance extends Instance<TLabel>, TLabel>
implements Evaluator<TInstance, TLabel>{
	private DatasetDistributor<TInstance> distributor;
	public FoldEvaluator(DatasetDistributor<TInstance> distributor){
		this.distributor=distributor;
	}

	@Override
	public ConfusionMatrix<TInstance, TLabel> evaluate(Factory<Learner<TInstance, TLabel>> learnerFactory, Collection<TInstance> dataset, int quantityOfFolds) {
		List<List<TInstance>> folds=distributor.distribute(dataset, quantityOfFolds);
		ConfusionMatrix<TInstance,TLabel> confusionMatrix=new ConfusionMatrix<TInstance, TLabel>();
		for(int i=0;i<folds.size();i++){
			evaluateFold(learnerFactory, folds, i, confusionMatrix);
		}
		return confusionMatrix;
	}
	
	private void evaluateFold(Factory<Learner<TInstance, TLabel>> learnerFactory, List<List<TInstance>> folds, int selectedTestingFoldIndex, ConfusionMatrix<TInstance, TLabel> confusionMatrix){
		List<TInstance> trainingData=new ArrayList<TInstance>();
		List<TInstance> testingData=new ArrayList<TInstance>();
		Learner<TInstance, TLabel> learner=learnerFactory.generateNewInstance();
		for(int i=0;i<folds.size();i++) if(i!=selectedTestingFoldIndex){
			trainingData.addAll(folds.get(i));
		}
		testingData.addAll(folds.get(selectedTestingFoldIndex));
		learner.learn(trainingData);
		for(TInstance i : testingData){
			confusionMatrix.AddToMatrix(i, learner.predict(i));
		}
	}
}