package gerkosoft.MLEvaluation.Test;

import java.util.Collection;

import gerkosoft.MLEvaluation.Interfaces.Learner;

public class BasicInstanceWinnow implements Learner<BasicInstance, Boolean>{
	private double[] w;
	private int length;
	public BasicInstanceWinnow(int size){
		this.w=new double[size+1];
		this.length=size;
	}
	@Override
	public void learn(Collection<BasicInstance> trainingData) {
		for(int i=0;i<this.w.length;i++){
			this.w[i]=1.0;
		}
		this.w[this.length]=-this.length;
		for(int t=0;t<500;t++){
			for(BasicInstance instance : trainingData){
				if(calculateValue(instance) > 0 !=instance.getLabel()) {
					updateVector(instance, instance.getLabel());
				}
			}
		}
	}

	@Override
	public Boolean predict(BasicInstance testInput) {
		return calculateValue(testInput) > 0;
	}
	
	private double calculateValue(BasicInstance instance){
		double value=this.w[this.length];
		for(int i=0;i<this.length;i++){
			if(instance.getB(i)) value+=this.w[i];
		}
		return value;
	}
	
	private void updateVector(BasicInstance instance, boolean correctLabel){
		double multiplier=correctLabel ? 2 : 0.5;
		for(int i=0;i<this.length;i++){
			if(instance.getB(i)) this.w[i]*=multiplier;
		}
	}
}
