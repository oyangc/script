package script;

public class BoundingRectangle extends java.awt.geom.Rectangle2D.Float {

	public BoundingRectangle() {
		super();
	}

	public BoundingRectangle(double x, double y, double w, double h) {
		super((float) x, (float) y, (float) w, (float) h);
	}

	@Override
	public int outcode(double x, double y) {

		/*
		 * Note on casts to double below.  If the arithmetic of
		 * x+w or y+h is done in float, then some bits may be
		 * lost if the binary exponents of x/y and w/h are not
		 * similar.  By converting to double before the addition
		 * we force the addition to be carried out in double to
		 * avoid rounding error in the comparison.
		 *
		 * See bug 4320890 for problems that this inaccuracy causes.
		 */
		int out = 0;

		if (this.width <= 0) {
			out |= OUT_LEFT | OUT_RIGHT;
		} else if (x < this.x) {
			out |= OUT_LEFT;
		} else if (x > this.x + (double) this.width) {
			out |= OUT_RIGHT;
		}
		if (this.height <= 0) {
			out |= OUT_TOP | OUT_BOTTOM;
		} else if (y < this.y) {
			out |= OUT_TOP;
		} else if (y > this.y + (double) this.height) {
			out |= OUT_BOTTOM;
		}
		return out;
	}
}
