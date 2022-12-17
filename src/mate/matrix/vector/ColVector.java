package mate.matrix.vector;


public class ColVector extends Vector {
	public ColVector(int n) {
		super(n, 1);
	}

	@Override
	public double get(int i) throws IndexOutOfBoundsException {
		return super.get(i, 0);
	}
}
