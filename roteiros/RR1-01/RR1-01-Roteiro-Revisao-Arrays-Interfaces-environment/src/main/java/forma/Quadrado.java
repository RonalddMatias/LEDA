package forma;

public class Quadrado implements AreaI{
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double area() {
        return Math.pow(this.lado, 2);
    }


    
}
