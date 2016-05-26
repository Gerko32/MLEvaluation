package gerkosoft.MLEvaluation.Instance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gerkosoft.MLEvaluation.Interfaces.Instance;

public class ConfusionMatrix<TLabel> {
	private final Map<LabelPairCombination<TLabel>, Integer> matrix;

	public ConfusionMatrix() {
		this.matrix = new HashMap<LabelPairCombination<TLabel>, Integer>();
	}
	protected ConfusionMatrix(ConfusionMatrix<TLabel>.ConfusionMatrixBuilder builder) {
		this.matrix = new HashMap<LabelPairCombination<TLabel>, Integer>(builder.matrix);
	}
	public int getValue(TLabel gold, TLabel predicted) {
		LabelPairCombination<TLabel> key=new LabelPairCombination<TLabel>(gold, predicted);
		if (!this.matrix.containsKey(key)) {
			return 0;
		}
		return this.matrix.get(key);
	}

	@Override
	public String toString() {
		Set<TLabel> labels = new HashSet<TLabel>();
		StringBuilder builder = new StringBuilder();
		for (LabelPairCombination<TLabel> labelPair : this.matrix.keySet()) {
			labels.add(labelPair.getGoldLabel());
			labels.add(labelPair.getPredictedLabel());
		}
		// headers
		for (TLabel predictedLabel : labels) {
			builder.append('\t');
			builder.append(predictedLabel);
		}
		// table
		for (TLabel actualLabel : labels) {
			builder.append('\n');
			builder.append(actualLabel);
			for (TLabel predictedLabel : labels) {
				builder.append('\t');
				builder.append(getValue(actualLabel, predictedLabel));
			}
		}
		return builder.toString();
	}
	public class ConfusionMatrixBuilder {
		protected Map<LabelPairCombination<TLabel>, Integer> matrix;

		public ConfusionMatrixBuilder() {
			this.matrix = new HashMap<LabelPairCombination<TLabel>, Integer>();
		}

		public <TInstance extends Instance<TLabel>> void addToMatrix(TInstance instance, TLabel predictedLabel) {
			addToMatrix(instance.getLabel(), predictedLabel);
		}
		
		public void addToMatrix(TLabel gold, TLabel predicted) {
			LabelPairCombination<TLabel> key=new LabelPairCombination<TLabel>(gold, predicted);
			if (!this.matrix.containsKey(key)) {
				this.matrix.put(key, 0);
			}
			this.matrix.put(key, this.matrix.get(key) + 1);
		}
		public ConfusionMatrix<TLabel> build(){
			return new ConfusionMatrix<TLabel>(this);
		}
	}
}
