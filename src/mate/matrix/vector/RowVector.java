package mate.matrix.vector;

import mate.matrix.RectangularMatrix;

public class RowVector extends RectangularMatrix {
	public RowVector(int n) {
		super(1, n);
	}

	public double get(int j) throws IndexOutOfBoundsException {
		return super.get(0, j);
	}
}
