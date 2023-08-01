package forma;

public class Circulo implements AreaI{
    private double raio;

        
    public Circulo(double raio) {
        this.raio = raio;
    }
    
    @Override
    public double area() {
        return Math.PI * Math.pow(raio, raio);
    }
    
}
