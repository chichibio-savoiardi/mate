package program;

import mate.matrix.RectangularMatrix;
import mate.matrix.SquareMatrix;

public class Test {
	public static void testMatrix() {
		RectangularMatrix r1 = new RectangularMatrix(2, 3);
        RectangularMatrix r2 = new RectangularMatrix(3, 1);
		r1.initRowEchelon(0, 9);
		r2.initRandom(0, 9);

		RectangularMatrix r3 = r1.product(r2).getContent().asRectangularMatrix();

		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);

		System.out.println(r1.remRow(0).getContent());
		System.out.println(r1.remCol(1).getContent());

		SquareMatrix s1 = new SquareMatrix(4);
		s1.initRowEchelon(1, 9);

		System.out.println(s1);
		System.out.println("Is Row Echelon: " + s1.isRowEchelonForm());
	}
	
	public static void testSystem() {
		//
	}
}
