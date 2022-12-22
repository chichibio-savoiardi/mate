package mate.matrix;

import lombok.Getter;
import java.util.Random;
import utils.result.Result;
import mate.matrix.vector.ColVector;
import mate.matrix.vector.RowVector;

/**
 * Matrix
 */
@Getter
public abstract class Matrix implements Comparable<Matrix> {
	protected double[][] mat;
	protected final int rowLength;
	protected final int colLength;

	protected Matrix(double[][] matrix) {
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
				newmat[k][j] = mat[i][j];
			}

			k++;
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
		Result<SquareMatrix> sq = this.asSquareMatrix();
		if (sq.getError() == 0 && sq.getContent().getDeterminant() != 0) {
			return rowLength;
		}

		return getBiggestSquareMatrix().getRank();
	}

	public SquareMatrix getBiggestSquareMatrix() {
		RectangularMatrix rect = new RectangularMatrix(mat.clone());

		do {
			if (rect.getRowLength() < rect.getColLength()) {
				rect = rect.remCol(rect.getColLength() - 1).getContent();
			} else if (rect.getRowLength() > rect.getColLength()) {
				rect = rect.remRow(rect.getRowLength() - 1).getContent();
			} else if (rect.getRowLength() > 1) {
				rect = rect.remRow(rect.getRowLength() - 1).getContent();
				rect = rect.remCol(rect.getColLength() - 1).getContent();
			}
		} while (rect.getRowLength() != rect.getColLength());

		return new SquareMatrix(rect.getMat());
	}

	public Result<SquareMatrix> asSquareMatrix() {
		if (rowLength != colLength) {
			return new Result<>(1, "Cannot convert to square, rowLength do not equals colLength");
		}

		return new Result<>(new SquareMatrix(mat));
	}

	public RectangularMatrix append(RowVector vec) {
		double[][] newmat = new double[rowLength + 1][colLength];

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				newmat[i][j] = mat[i][j];
			}
		}

		newmat[rowLength] = vec.getMat()[0];

		return new RectangularMatrix(newmat);
	}

	public RectangularMatrix append(ColVector vec) {
		double[][] newmat = new double[rowLength][colLength + 1];

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				newmat[i][j] = mat[i][j];
			}
		}

		for (int i = 0; i < newmat.length; i++) {
			newmat[i][colLength] = vec.get(i);
		}

		return new RectangularMatrix(newmat);
	}
	
	/**
	 * Calculates and returns the sum of all squared elements.
	 * @return The sum of each squared element.
	 */
	public double getSumOfSquaredElements() {
		double acc = 0;

		for (int i = 0; i < getRowLength(); i++) {
			for (int j = 0; j < getColLength(); j++) {
				acc += Math.pow(mat[i][j], 2);
			}
		}
		
		return acc;
	}
	
	public RectangularMatrix getRowEchelonForm() {
		// TODO
		return null;
	}

	/**
	 * Implementation of the Comparable interface.
	 * 
	 * This implementation return the difference of {@link mate.matrix.Matrix#getSumOfSquaredElements()} of other minus this.
	 * 
	 * @see Comparable#compareTo(Object)
	 * @param other Matrix to compare against.
	 * @return An integer representing the difference between this and other.
	 */
	@Override
	public int compareTo(Matrix other) {
		return (int) Math.round(other.getSumOfSquaredElements() - this.getSumOfSquaredElements());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Matrix m) {
			return compareTo(m) == 0;
		}

		return false;
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
