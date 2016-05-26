package gerkosoft.MLEvaluation.Instance;

public class LabelPairCombination<TLabel> {
	private final TLabel gold;
	private final TLabel predicted;
	public LabelPairCombination(TLabel gold, TLabel predicted){
		this.gold=gold;
		this.predicted=predicted;
	}
	public TLabel getGoldLabel(){
		return this.gold;
	}
	public TLabel getPredictedLabel(){
		return this.predicted;
	}
	@Override
	public boolean equals(Object o){
		if(! (o instanceof LabelPairCombination<?>)) return false;
		LabelPairCombination<?> other=(LabelPairCombination<?>) o;
		return this.gold.equals(other.gold) && this.predicted.equals(other.predicted);
	}
	@Override
	public int hashCode(){
		return this.gold.hashCode();
	}
}
