package mate.matrix;

import lombok.Getter;
import mate.result.Result;

/**
 * Matrix
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

	public double get(int i, int j) {
		return mat[i][j];
	}
	
	public void set(double v, int i, int j) {
		mat[i][j] = v;
	}
	
	public Result<double[][]> rowsTimesCols(RectangularMatrix other) {
		if (this.rowLength != other.getColLength() || this.colLength != other.getRowLength()) {
			return new Result<>(1, "Incompatible lengths");
		}
		
		double[][] res = new double[rowLength][colLength];

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				res[i][j] += mat[i][j] * other.get(j, i);
			}
		}

		return new Result<>(res);
	}
}