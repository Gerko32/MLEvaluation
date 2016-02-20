package gerkosoft.MLEvaluation.Instance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import gerkosoft.MLEvaluation.Interfaces.DatasetDistributor;
import gerkosoft.MLEvaluation.Interfaces.Instance;
import gerkosoft.MLEvaluation.Interfaces.Learner;

public class MonteCarloDistributor<T> implements DatasetDistributor<T>{
	@Override
	public List<List<T>> distribute(Collection<T> dataset, int quantityOfBuckets) {
		Random r=new Random();
		List<List<T>> folds=new ArrayList<List<T>>();
		for(int i=0;i<quantityOfBuckets;i++){
			folds.add(new ArrayList<T>());
		}
		for(T instance : dataset){
			folds.get(r.nextInt(quantityOfBuckets)).add(instance);
		}
		return folds;
	}
}
