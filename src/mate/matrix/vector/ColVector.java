package mate.matrix.vector;

import mate.matrix.RectangularMatrix;

public class ColVector extends RectangularMatrix {
	public ColVector(int n) {
		super(n, 1);
	}

	public double get(int i) throws IndexOutOfBoundsException {
		return super.get(i, 0);
	}
}
