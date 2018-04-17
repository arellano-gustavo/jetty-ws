package mx.qbits.itam.ws;

public class CalcService {
    public int fact(int n) {
        return (n<2)?1:n*fact(n-1);
    }
    public double divide(int a, int b) {
        return a/b;
    }
}
