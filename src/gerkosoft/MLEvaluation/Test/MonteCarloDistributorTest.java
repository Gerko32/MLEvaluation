package gerkosoft.MLEvaluation.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import gerkosoft.MLEvaluation.Instance.MonteCarloDistributor;
import gerkosoft.MLEvaluation.Instance.RandomEvenDistributor;
import gerkosoft.MLEvaluation.Interfaces.DatasetDistributor;

public class MonteCarloDistributorTest {

	@Test
	public void EvenDistributionHasAllElements() {
		DatasetDistributor<Integer> d=new MonteCarloDistributor<Integer>();
		List<Integer> dataset=generateListOfUniqueNumbers(100);
		for(List<Integer> fold : d.distribute(dataset, 5)){
			for(Integer i : fold){
				if(!dataset.contains(i)) Assert.fail();
			}
			dataset.removeAll(fold);
		}
		Assert.assertEquals(0, dataset.size());
	}
	
	@Test
	public void HasTheExpectedQuantityOfFolds() {
		DatasetDistributor<Integer> d=new MonteCarloDistributor<Integer>();
		List<Integer> dataset=generateListOfUniqueNumbers(100);
		Assert.assertEquals(5, d.distribute(dataset, 5).size());
	}
	
	private List<Integer> generateListOfUniqueNumbers(int size){
		List<Integer> list=new ArrayList<Integer>();
		while(size>0){
			list.add(size);
			size--;
		}
		return list;
	}
}
