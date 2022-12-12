package mate.linearEquations;

import mate.matrix.RectangularMatrix;
import mate.matrix.vector.ColVector;

public class SystemLinearEquations {
	//
	RectangularMatrix a;
	ColVector x;
	ColVector b;

	public SystemLinearEquations(RectangularMatrix a, ColVector x, ColVector b) {
		this.a = a;
		this.x = x;
		this.b = b;
	}

	public int[] getRanks() {
		int ra = a.getRank();
		int rab = a
	}

}
