public final class Complex {
    
    private final double real;
    private final double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double real() {
        return real;
    }

    public double imaginary() {
        return imaginary;
    }

    public Complex add(Complex b) {
        return new Complex(this.real + b.real(), this.imaginary + b.imaginary());
    }

    public Complex sub(Complex b) {
        return new Complex(this.real - b.real(), this.imaginary - b.imaginary());
    }

    public Complex mul(Complex b) {
        return new Complex(this.real * b.real() - this.imaginary * b.imaginary(), 
                           this.real * b.imaginary() + this.imaginary * b.real());
    }

    public Complex div(Complex b) {
        return new Complex((this.real * b.real() + this.imaginary * b.imaginary())/(b.real() * b.real() + b.imaginary() * b.imaginary()), 
                            (this.imaginary * b.real() - this.real * b.imaginary())/(b.real() * b.real() + b.imaginary() * b.imaginary()));
    }

    public String toString() {
        return real + " + " + imaginary + "i";
    }
}
