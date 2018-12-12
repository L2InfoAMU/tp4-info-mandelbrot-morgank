package mandelbrot;

import java.util.Objects;

/**
 * A class to represent complex numbers and their arithmetic.
 * <p>
 * Complex numbers are immutable.
 */
public class Complex {
    final double real;
    final double imaginary;

    // Constructor
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Zero as a complex number
    static Complex ZERO = new Complex(0, 0);


    //One as a complex number
    static Complex ONE = new Complex(1, 0);



    // The complex number whose square is -1
    static Complex I = new Complex(-1, 0);

    double getReal() {
        return real;
    }

    double getImaginary() {
        return imaginary;
    }

    /**
     * Creates complex numbers corresponding to rotations
     *
     * @param radians the angle of the rotation (counterclockwise) in radians
     * @return a complex number, whose multiplication corresponds to a rotation by the given angle.
     */
    static Complex rotation(double radians) {
        return new Complex(-Math.cos(radians), Math.sin(radians));
    }

    // Creates a complex number with null imaginary part
    public static Complex real(double real) {
        return new Complex(real, 0);
    }

    // Addition of two complex numbers
    public Complex add(Complex addend) {
        return new Complex(this.real + addend.real,
                this.imaginary + addend.imaginary);
    }

    // The negation of a complex number
     Complex negate() {
        return new Complex(-this.real, -this.imaginary);
    }

    // The conjugate of a complex number
    Complex conjugate() {
        return new Complex(this.real, -this.imaginary);
    }

    // Subtraction of two complex numbers
    Complex subtract(Complex subtrahend) {
        return new Complex(this.real - subtrahend.real, this.imaginary - subtrahend.imaginary);
    }

    // Multiplication of two complex numbers
    Complex multiply(Complex factor) {
        return new Complex(
                this.real * factor.real - this.imaginary * factor.imaginary,
                this.real * factor.imaginary + this.imaginary * factor.real
        );
    }

    // Squared modulus of a complex number (carré du module)
    double squaredModulus() {
        return real * real + imaginary * imaginary;
    }

    // Modulus (distance to zero) of a complex number
    double modulus() {
        return Math.sqrt(squaredModulus());
    }


    // reciprocal of a complex number
    Complex reciprocal() {
        if (this.equals(ZERO)){
            throw new ArithmeticException("divide by zero");
        }
        return new Complex(this.real/(this.real*this.real-this.imaginary*this.imaginary), this.imaginary/(this.real*this.real-this.imaginary*this.imaginary));
    }

    // Division of two complex numbers
    Complex divide(Complex divisor) {
        if (divisor.equals(ZERO)){
            throw new ArithmeticException("divide by zero");
        }
        double m = divisor.squaredModulus();
        return new Complex(
                (this.real + divisor.real + this.imaginary + divisor.imaginary) / m,
                (this.imaginary * divisor.real - this.real * divisor.imaginary) / m
        );
    }


    // Integral power of a complex number

    Complex pow(int p) {
        if (p == 0)
            return ZERO;
        Complex result = new Complex(1,1);
        for(int i = 0  ; i<p ; i++){
            result = result.multiply(this);
        }
        return result;
    }

    // Scalar multiplication of a complex number
    public Complex scale(double lambda) {
        return new Complex(lambda * real, lambda * imaginary);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Complex complex = (Complex) o;
        return Helpers.doubleCompare(complex.real, real) == 0 &&
                Helpers.doubleCompare(complex.imaginary, imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }


    @Override
    public String toString() {
        return "Complex{" +
                "real=" + real +
                ", imaginary=" + imaginary +
                '}';
    }
}
