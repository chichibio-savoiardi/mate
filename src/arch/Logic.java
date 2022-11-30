package arch;

public class Logic {

	public static Bit not(Bit b) {
		switch (b) {
			case HI:
				return Bit.LO;

			case LO:
				return Bit.HI;

			default:
				return Bit.Z;
		}
	}

	public static Bit tri(Bit b, Bit e) {
		return Bit.Z;
	}

	public static Bit and(Bit b1, Bit b2) {
		if (b1 == Bit.Z || b2 == Bit.Z) {
			return Bit.LO;
		}

		if (b1 == b2) {
			return Bit.HI;
		}

		return Bit.LO;
	}

	public static Bit or(Bit b1, Bit b2) {
		if (b1 == Bit.Z || b2 == Bit.Z) {
			return Bit.LO;
		}

		if (b1 == Bit.LO && b2 == Bit.LO) {
			return Bit.LO;
		}

		return Bit.HI;
	}

	public static Bit xor(Bit b1, Bit b2) {
		if (b1 == Bit.Z || b2 == Bit.Z) {
			return Bit.LO;
		}

		if (b1 != b2) {
			return Bit.LO;
		}

		return Bit.HI;
	}

	public static Bit nxor(Bit b1, Bit b2) {
		return not(xor(b1, b2));
	}
}
