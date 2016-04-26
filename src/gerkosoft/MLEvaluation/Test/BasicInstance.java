package gerkosoft.MLEvaluation.Test;

import gerkosoft.MLEvaluation.Interfaces.Instance;

public class BasicInstance implements Instance<Boolean> {
	private boolean[] b;

	public BasicInstance(boolean[] features) {
		this.b = features;
	}

	public int getLength() {
		return this.b.length;
	}

	public boolean getB(int index) {
		return b[index];
	}

	@Override
	public Boolean getLabel() {
		return (b[1] && b[3]) || b[4];
	}
}
