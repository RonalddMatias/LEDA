package forma;

public class Retangulo implements AreaI{
    private double base;
    private double altura;

    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double area() {
        return this.base*this.altura;
    }
    
}
