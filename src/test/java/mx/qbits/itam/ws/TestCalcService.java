package mx.qbits.itam.ws;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestCalcService {
    private CalcService cs = new CalcService();
    //hola
    @Test
    public void factorialTest() {
        int val = 5;
        int result = cs.fact(val);
        assertTrue("Probando que el factorial de "+val+" es:", 120==result);
        
        val = 1;
        result = cs.fact(val);
        assertTrue("Probando que el factorial de "+val+" es:", 1==result);        
    }
}


