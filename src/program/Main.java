package program;

import mate.matrix.RectangularMatrix;
import mate.matrix.SquareMatrix;

public class Main {
    public static void main(String[] args) throws Exception {
        RectangularMatrix r1 = new RectangularMatrix(2, 3);
        RectangularMatrix r2 = new RectangularMatrix(3, 1);
		r1.initRandom(0, 9);
		r2.initRandom(0, 9);

		RectangularMatrix r3 = r1.product(r2).getContent();

		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		
		SquareMatrix s1 = new SquareMatrix(4);
		s1.initRandom(0, 9);

		System.out.printf("Determinant: %f\n", s1.getDeterminant());
    }
}
