package gerkosoft.MLEvaluation.Instance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gerkosoft.MLEvaluation.Interfaces.DatasetDistributor;
import gerkosoft.MLEvaluation.Interfaces.Instance;
import gerkosoft.MLEvaluation.Interfaces.Learner;

public class RandomEvenDistributor<T> implements DatasetDistributor<T> {
	@Override
	public List<List<T>> distribute(Collection<T> dataset, int quantityOfBuckets) {
		List<T> randomOrderDataset=new ArrayList<T>(dataset);
		java.util.Collections.shuffle(randomOrderDataset);
		List<List<T>> folds=new ArrayList<List<T>>();
		for(int i=0;i<quantityOfBuckets;i++){
			folds.add(new ArrayList<T>());
		}
		for(int instanceIndex=0;instanceIndex<randomOrderDataset.size();instanceIndex++){
			folds.get(instanceIndex%quantityOfBuckets).add(randomOrderDataset.get(instanceIndex));
		}
		return folds;
	}

}
