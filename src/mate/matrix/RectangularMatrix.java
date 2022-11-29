package mate.matrix;

import java.util.Random;

import lombok.Getter;
import mate.result.Result;

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

	public void initRandom(double min, double max) {
		Random rng = new Random();
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				mat[i][j] = rng.nextDouble(min, max);
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

	public double get(int i, int j) {
		return mat[i][j];
	}

	public void set(double v, int i, int j) {
		mat[i][j] = v;
	}

	public Result<double[][]> sum(RectangularMatrix other) {
		if (this.rowLength != other.getRowLength() || this.colLength != other.getColLength()) {
			return new Result<>(1, "Matrixes are not equally sized");
		}

		double[][] out = new double[rowLength][colLength];

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				out[i][j] = mat[i][j] + other.get(i, j);
			}
		}

		return new Result<>(out);
	}

	public Result<double[][]> product(RectangularMatrix other) {
		if (colLength != other.getRowLength()) {
			return new Result<>(1, "Number of columns does not equal number of rows");
		}
		int newRows = Math.min(rowLength, other.getRowLength());
		int newCols = Math.min(colLength, other.getColLength());

		double[][] res = new double[newRows][newCols];

		for (int i = 0; i < newRows; i++) {
			for (int j = 0; j < newCols; j++) {
				for (int k = 0; k < rowLength; k++) {
					res[i][j] += mat[i][k] * other.get(k, j);
				}
			}
		}

		return new Result<>(res);
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(String.format("%s (\n\tRows: %d\n\tCols: %d\n\tMatrice (", this.getClass(), rowLength, colLength));
		for (double[] ds : mat) {
			out.append("\n\t\t[ ");
			for (double d : ds) {
				out.append(String.format("%.2f ", d));
			}
			out.append("]");
		}
		out.append("\n\t)\n)");
		return out.toString();
	}
}
