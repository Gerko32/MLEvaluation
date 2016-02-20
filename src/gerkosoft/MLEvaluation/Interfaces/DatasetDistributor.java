package gerkosoft.MLEvaluation.Interfaces;

import java.util.Collection;
import java.util.List;

public interface DatasetDistributor<T> {
	List<List<T>> distribute(Collection<T> dataset, int quantityOfBuckets);
}
