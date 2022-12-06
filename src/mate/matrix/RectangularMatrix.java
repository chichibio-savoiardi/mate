package mate.matrix;

import java.util.Random;
import lombok.Getter;
import utils.result.Result;

/**
 * RectangularMatrix
 */
public class RectangularMatrix {

	@Getter
	protected double[][] mat;
	@Getter
	protected final int rowLength;
	@Getter
	protected final int colLength;

	public RectangularMatrix(int rows, int cols) {
		mat = new double[rows][cols];
		rowLength = rows;
		colLength = cols;
	}

	public RectangularMatrix(double[][] matrix) {
		mat = matrix;
		rowLength = matrix.length;
		colLength = matrix[0].length;
	}

	public void initRandom(int min, int max) {
		Random rng = new Random();
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				mat[i][j] = rng.nextInt(min, max);
			}
		}
	}

	public void initConst(double val) {
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				mat[i][j] = val;
			}
		}
	}

	public void initIdent() {
		int dim = Math.min(rowLength, colLength);
		for (int i = 0; i < dim; i++) {
			mat[i][i] = 1;
		}
	}

	public double get(int i, int j) throws IndexOutOfBoundsException {
		return mat[i][j];
	}

	public void set(double v, int i, int j) throws IndexOutOfBoundsException {
		mat[i][j] = v;
	}

	public Result<RectangularMatrix> sum(RectangularMatrix other) {
		if (this.rowLength != other.getRowLength() || this.colLength != other.getColLength()) {
			return new Result<>(1, "Matrixes are not equally sized");
		}

		double[][] out = new double[rowLength][colLength];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				out[i][j] = mat[i][j] + other.get(i, j);
			}
		}

		return new Result<>(new RectangularMatrix(out));
	}

	public Result<RectangularMatrix> product(RectangularMatrix other) {
		if (colLength != other.getRowLength()) {
			return new Result<>(1, "Number of columns does not equal number of rows");
		}
		int newRows = Math.min(rowLength, other.getRowLength());
		int newCols = Math.min(colLength, other.getColLength());

		double[][] res = new double[newRows][newCols];

		for (int i = 0; i < newRows; i++) {
			for (int j = 0; j < newCols; j++) {
				for (int k = 0; k < colLength; k++) {
					res[i][j] += mat[i][k] * other.get(k, j);
				}
			}
		}

		return new Result<>(new RectangularMatrix(res));
	}

	public RectangularMatrix transpose() {
		double[][] res = new double[colLength][rowLength];

		for (int i = 0; i < colLength; i++) {
			for (int j = 0; j < rowLength; j++) {
				res[i][j] = mat[j][i];
			}
		}

		return new RectangularMatrix(res);
	}

	public Result<RectangularMatrix> remRow(int row) {
		if (row < 0 || row >= rowLength) {
			return new Result<>(1, "Row to delete was out of range");
		}

		double[][] newmat = new double[rowLength - 1][colLength];

		for (int i = 0, k = 0; i < mat.length; i++) {
			if (i == row) {
				continue;
			}

			for (int j = 0; j < mat[i].length; j++) {
				newmat[k++][j] = mat[i][j];
			}

		}

		return new Result<>(new RectangularMatrix(newmat));
	}

	public Result<RectangularMatrix> remCol(int col) {
		if (col < 0 || col >= colLength) {
			return new Result<>(1, "Col to delete was out of range");
		}

		double[][] newmat = new double[rowLength][colLength - 1];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0, k = 0; j < mat[i].length; j++) {
				if (j == col) {
					continue;
				}

				newmat[i][k++] = mat[i][j];
			}
		}

		return new Result<>(new RectangularMatrix(newmat));
	}

	public int getRank() {
		if (rowLength == colLength) {
			SquareMatrix sq = new SquareMatrix(mat);
			if (sq.getDeterminant() != 0) {
				return rowLength;
			}
		}

		return getBiggestSquareMatrix().getContent().getRank();
	}

	public Result<SquareMatrix> getBiggestSquareMatrix() {
		RectangularMatrix rect = new RectangularMatrix(mat.clone());

		do {
			if (rect.getRowLength() < rect.getColLength()) {
				rect = rect.remCol(rect.getColLength() - 1).getContent();
			} else if (rect.getRowLength() > rect.getColLength()) {
				rect = rect.remRow(rect.getRowLength() - 1).getContent();
			} else {
				rect = rect.remRow(rect.getRowLength() - 1).getContent();
				rect = rect.remCol(rect.getColLength() - 1).getContent();
			}
		} while (rect.getRowLength() != rect.getColLength());

		return new Result<>(new SquareMatrix(rect.getMat()));
	}

	public Result<SquareMatrix> asSquareMatrix() {
		if (rowLength != colLength) {
			return new Result<>(1, "Cannot convert to square, rowLength do not equals colLength");
		}

		return new Result<>(new SquareMatrix(mat));
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(String.format("%s (\n\tRows: %d\n\tCols: %d\n\tRank: %d\n\tMatrix: (", this.getClass(), rowLength, colLength, getRank()));
		for (double[] ds : mat) {
			out.append("\n\t\t[ ");
			for (double d : ds) {
				out.append(String.format("%.1f ", d));
			}
			out.append("]");
		}
		out.append("\n\t)\n)");
		return out.toString();
	}
}
