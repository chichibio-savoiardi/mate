package mate.linearEquations;

import mate.matrix.RectangularMatrix;
import mate.matrix.vector.ColVector;

public class SystemLinearEquations {
	RectangularMatrix a;
	ColVector x;
	ColVector b;

	public SystemLinearEquations(RectangularMatrix a, ColVector x, ColVector b) {
		this.a = a;
		this.x = x;
		this.b = b;
	}

	public boolean isCompatible() {
		int ra = a.getRank();
		int rab = a.append(b).getContent().getRank();
		return ra == rab;
	}

	public int getSolNumber() {
		int r = a.getRank();
		int n = x.getRowLength();
		return r - n;
	}
}
