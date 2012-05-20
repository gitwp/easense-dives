package org.easense.chapterone;

/**
 * @author Easense
 * 
 */
public class Rational implements Comparable<Rational> {
	private long numerator;

	private long denominator;

	public static final Rational ZERO = new Rational(0, 1);

	public Rational(long numerator, long denominator) {

		if (denominator == 0) {
			throw new IllegalArgumentException("denominator can's be zero");
		}
		
		// make sure zero is represented by 0/1
		if (numerator == 0) {
			this.numerator = 0;
			this.denominator = 1;
			return;
		}
		
		// ensure numerator and denominator have no common factors
		long commonFactor = computeCommonFactor(numerator, denominator);

		if (commonFactor > 1) {
			numerator /= commonFactor;
			denominator /= commonFactor;
		}

		// denominator should always be positive numbers
		if (denominator < 0) {
			denominator = -denominator;
			numerator = -numerator;
		}
		
		this.numerator = numerator;
		this.denominator = denominator;
	}

	private long computeCommonFactor(long p, long q) {
		if (p < q) {
			return computeCommonFactor(q, p);
		}

		long r;
		while (q != 0) {
			r = p % q;
			p = q;
			q = r;
		}

		return p;
	}

	public Rational negative() {
		return new Rational(-numerator, denominator);
	}

	public Rational reciprocal() {
		return new Rational(denominator, numerator);
	}

	public Rational plus(Rational r) {
		if (this.compareTo(ZERO) == 0) {
			return r;
		}
		if (r.compareTo(ZERO) == 0) {
			return this;
		}

		long newNumerator = this.numerator * r.denominator + r.numerator * this.denominator;
		long newDenominator = this.denominator * r.denominator;

		return new Rational(newNumerator, newDenominator);
	}

	public Rational minus(Rational r) {
		return plus(r.negative());
	}

	public Rational times(Rational r) {
		long newNumerator = this.numerator * r.numerator;
		long newDenominator = this.denominator * r.denominator;

		return new Rational(newNumerator, newDenominator);
	}

	public Rational divide(Rational r) {
		return times(r.reciprocal());
	}

	public long getNumerator() {
		return numerator;
	}

	public long getDenominator() {
		return denominator;
	}

	@Override
	public String toString() {
		return "Rational [numerator=" + numerator + ", denominator=" + denominator + "]";
	}

	public int compareTo(Rational that) {
		long lhs = this.numerator * that.denominator;
		long rhs = this.denominator * that.numerator;
		if (lhs < rhs)
			return - 1;
		if (lhs > rhs)
			return + 1;
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (denominator ^ (denominator >>> 32));
		result = prime * result + (int) (numerator ^ (numerator >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rational other = (Rational) obj;
		return this.compareTo(other) == 0;
	}

	public static void main(String[] args) {
		Rational rOne = new Rational(4, 6);
		Rational rTwo = new Rational(3, 4);
		System.out.println(rOne.plus(rTwo));
		System.out.println(rOne.minus(rTwo));
		System.out.println(rOne.times(rTwo));
		System.out.println(rOne.divide(rTwo));
		
		System.out.println(new Rational(0, 10).compareTo(Rational.ZERO));
	}

}
