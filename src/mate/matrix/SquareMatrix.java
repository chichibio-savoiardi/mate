package mate.matrix;

public class SquareMatrix extends RectangularMatrix {

	public SquareMatrix(int n) {
		super(n, n);
	}

	public SquareMatrix(double[][] mat) {
		super(mat);
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
		return 0;
	}
}
