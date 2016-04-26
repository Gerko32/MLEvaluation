package gerkosoft.MLEvaluation.Instance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gerkosoft.MLEvaluation.Interfaces.Instance;

public class ConfusionMatrix<TInstance extends Instance<TLabel>, TLabel> {
	private Map<TLabel, Map<TLabel, Integer>> matrix;

	public ConfusionMatrix() {
		this.matrix = new HashMap<TLabel, Map<TLabel, Integer>>();
	}

	public void addToMatrix(TInstance instance, TLabel predictedLabel) {
		addToMatrix(instance.getLabel(), predictedLabel);
	}

	public void addToMatrix(TLabel actualLabel, TLabel predictedLabel) {
		if (!this.matrix.containsKey(actualLabel)) {
			this.matrix.put(actualLabel, new HashMap<TLabel, Integer>());
		}
		if (!this.matrix.get(actualLabel).containsKey(predictedLabel)) {
			this.matrix.get(actualLabel).put(predictedLabel, 0);
		}
		this.matrix.get(actualLabel).put(predictedLabel, this.matrix.get(actualLabel).get(predictedLabel) + 1);
	}

	public int getValue(TLabel actualLabel, TLabel predictedLabel) {
		if (!this.matrix.containsKey(actualLabel)) {
			return 0;
		}
		if (!this.matrix.get(actualLabel).containsKey(predictedLabel)) {
			return 0;
		}
		return this.matrix.get(actualLabel).get(predictedLabel);
	}

	@Override
	public String toString() {
		Set<TLabel> labels = new HashSet<TLabel>(this.matrix.keySet());
		StringBuilder builder = new StringBuilder();
		for (Map<TLabel, Integer> secondLayer : this.matrix.values()) {
			labels.addAll(secondLayer.keySet());
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
}
