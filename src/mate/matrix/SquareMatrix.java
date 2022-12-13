package mate.matrix;

import utils.result.Panic;

public class SquareMatrix extends RectangularMatrix {

	public SquareMatrix(int n) {
		super(n, n);
	}

	public SquareMatrix(double[][] mat) throws Panic {
		super(mat);
		if (mat.length != mat[0].length) {
			throw new Panic("Matrix is not squared");
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
				return laplace();
		}
	}

	private double laplace() {
		double out = 0;

		for (int i = 0; i < mat[0].length; i++) {
			SquareMatrix s1 = this.getBiggestSquareMatrix().getContent();
			double det = s1.getDeterminant();
			out += Math.pow(-1, i) * mat[0][i] * det;
		}

		return out;
	}

	@Override
	public String toString() {
		return super.toString().replaceFirst("\n\tRank:", String.format("\n\tDet: %f\n\tRank:", getDeterminant()));
	}
}
