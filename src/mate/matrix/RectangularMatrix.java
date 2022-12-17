package mate.matrix;

/**
 * RectangularMatrix
 */
public class RectangularMatrix extends Matrix {

	public RectangularMatrix(int rows, int cols) {
		super(new double[rows][cols]);
	}
	
	public RectangularMatrix(double[][] mat) {
		super(mat);
	}
}
