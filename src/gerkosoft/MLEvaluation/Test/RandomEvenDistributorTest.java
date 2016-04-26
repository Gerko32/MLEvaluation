package gerkosoft.MLEvaluation.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import gerkosoft.MLEvaluation.Instance.RandomEvenDistributor;

public class RandomEvenDistributorTest {

	@Test
	public void EvenDistributionHasAllElements() {
		RandomEvenDistributor<Integer> d = new RandomEvenDistributor<Integer>();
		List<Integer> dataset = generateListOfUniqueNumbers(100);
		for (List<Integer> fold : d.distribute(dataset, 5)) {
			for (Integer i : fold) {
				if (!dataset.contains(i))
					Assert.fail();
			}
			dataset.removeAll(fold);
		}
		Assert.assertEquals(0, dataset.size());
	}

	@Test
	public void HasTheExpectedQuantityOfFolds() {
		RandomEvenDistributor<Integer> d = new RandomEvenDistributor<Integer>();
		List<Integer> dataset = generateListOfUniqueNumbers(100);
		Assert.assertEquals(5, d.distribute(dataset, 5).size());
	}

	@Test
	public void AllFoldsHaveSameSize() {
		RandomEvenDistributor<Integer> d = new RandomEvenDistributor<Integer>();
		List<Integer> dataset = generateListOfUniqueNumbers(100);
		for (List<Integer> fold : d.distribute(dataset, 5)) {
			Assert.assertEquals(20, fold.size());
		}
	}

	private List<Integer> generateListOfUniqueNumbers(int size) {
		List<Integer> list = new ArrayList<Integer>();
		while (size > 0) {
			list.add(size);
			size--;
		}
		return list;
	}
}
