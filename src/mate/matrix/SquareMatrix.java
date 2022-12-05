package mate.matrix;

public class SquareMatrix extends RectangularMatrix {

	public SquareMatrix(int n) {
		super(n, n);
	}

	public SquareMatrix(double[][] mat) throws RuntimeException {
		super(mat);
		if (mat.length != mat[0].length) {
			throw new RuntimeException("Matrix is not squared");
		}
	}

	public double getDeterminant() {
		switch (rowLength) {
			case 0:
				return 0;

			case 1:
				return mat[0][0];

			case 2:
				return (mat[0][0] * mat[1][1]) - (mat[0][1] * mat[1][0]);

			case 3:
				return (
					(mat[0][0] * mat[1][1] * mat[2][2])
					+ (mat[0][1] * mat[1][2] * mat[2][0])
					+ (mat[0][2] * mat[1][0] * mat[2][1])
				) - (
					(mat[0][2] * mat[1][1] * mat[2][0])
					+ (mat[0][0] * mat[1][2] * mat[2][1])
					+ (mat[0][1] * mat[1][0] * mat[2][2])
				);

			default:
				break;
		}

		return laplace();
	}

	private double laplace() {
		double out = 0;

		for (int i = 0; i < mat[0].length; i++) {
			RectangularMatrix r1 = this.remRow(0).getContent();
			SquareMatrix s1 = new SquareMatrix(r1.remCol(i).getContent().getMat());
			double det = s1.getDeterminant();
			out += Math.pow(-1, i) * mat[0][i] * det;
		}
	
		return out;
	}
	
	@Override
	public String toString() {
		String old = super.toString();
		String newstr = old.replaceFirst("\n\tRank:", String.format("\n\tDet: %f\n\tRank:", getDeterminant()));
		return newstr;
	}
}
