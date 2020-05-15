package net.oluciano.maven;

import static org.junit.Assert.*;
import org.junit.Test;

public class ProdutoTest {

    @Test
    public void verificaPrecoComImposto() {
        Produto bala = new Produto("Cadeira", 500);
        assertEquals(550.0, bala.getPrecoComImposto(), 0.00001);
    }
    
    @Test
    public void verificaNome(){
    	Produto carro = new Produto("Carro", 50000.0);
    	assertEquals(null, carro.getNome(), "Carro");
    }
}