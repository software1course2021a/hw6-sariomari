package il.ac.tau.cs.sw1.hw6;

public class Polynomial {
	private double[] coeffs;
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial()
	{
		double[] zero = new double[1];
		zero[0] = 0;
		this.coeffs = zero;
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		this.coeffs = coefficients;
	}
	/*
	 * Adds this polynomial to the given one
	 *  and returns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{
		int d1 = this.coeffs.length;
		int d2 = polynomial.coeffs.length;
		double[] res;
		if (d1 >= d2) {
			res = new double[d1];
			for (int i=0; i<d1; i++) {
				double temp = 0;
				if (i<d2)
					temp += polynomial.coeffs[i];
				temp += this.coeffs[i];
				res[i] = temp;
			}
		}
		else {
			res = new double[d2];
			for (int i=0; i<d2; i++) {
				double temp = 0;
				if (i<d1)
					temp += this.coeffs[i];
				temp += polynomial.coeffs[i];
				res[i] = temp;
			}
			
		}
		Polynomial result = new Polynomial(res);
		return result;
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		int n = this.coeffs.length;
		double[] res = new double[n];
		for (int i=0; i<n; i++) {
			res[i] = a*this.coeffs[i];
		}
		Polynomial result = new Polynomial(res);
		return result;
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		return this.coeffs.length-1;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		if (n < this.coeffs.length)
			return this.coeffs[n];
		return 0.00;
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{
		int d = this.getDegree();
		if (d >= n) {
			this.coeffs[n] = c;
		}
		else {
			double[] res = new double[n+1];
			res[n] = c;
			for (int i=0; i<n-1; i++) {
				if (i<=d) {
					res[i] = this.coeffs[i];
				}
				else {
					res[i] = 0;
				}
			}
			this.coeffs = res;
		}
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		int n = this.coeffs.length;
		double[] deriv = new double[n-1];
		for (int i=0; i<n-1; i++) {
			deriv[i] = (this.coeffs[i+1])*(i+1);
		}
		Polynomial result = new Polynomial(deriv);
		return result;
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{
		double[] coeffs = this.coeffs;
		double result = 0;
		int n = this.coeffs.length;
		for (int i=0; i<n; i++) {
			result += coeffs[i]*(Math.pow(x, i));
		}
		return result;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial deriv = this.getFirstDerivation();
		Polynomial second_deriv = deriv.getFirstDerivation();
		if (deriv.computePolynomial(x) == 0 && second_deriv.computePolynomial(x) != 0) {
			return true;
		}
		return false;
	}
	
	
	
	

    
    

}
