package mate.matrix.vector;

import mate.matrix.RectangularMatrix;

public abstract class Vector extends RectangularMatrix {
	protected Vector(int n, int m) {
		super(n, m);
	}
	
	public abstract double get(int i) throws IndexOutOfBoundsException;
}
