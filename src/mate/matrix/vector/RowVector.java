package mate.matrix.vector;


public class RowVector extends Vector {
	public RowVector(int n) {
		super(1, n);
	}

	@Override
	public double get(int j) throws IndexOutOfBoundsException {
		return super.get(0, j);
	}
}
