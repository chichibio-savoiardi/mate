package mate;

import lombok.Getter;

/**
 * Matrix
 */
public class Matrix {

	private double[][] mat;
	@Getter
	private final int rowLength;
	@Getter
	private final int colLength;
	@Getter
	private final boolean square;

	public Matrix(int n) {
		mat = new double[n][n];
		rowLength = colLength = n;
		square = true;
	}
	
	public Matrix(int rows, int cols) {
		mat = new double[rows][cols];
		rowLength = rows;
		colLength = cols;
		square = false;
	}
}