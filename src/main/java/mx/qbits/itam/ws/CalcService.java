package mx.qbits.itam.ws;

public class CalcService {
    public int fact(int n) {
        return (n<2)?1:n*fact(n-1);
    }
    // para checar que el revisor de estilo funciona bien, podemos
    // cambiar el nombre de este método de "divide" a "Divide" y
    // revisar qué efecto tuvo en Jenkins
    public double divide(int a, int b) {
        return a/b;
    }
}
