package net.oluciano.maven;

public class App {
    public static void main( String[] args ){

    	Produto produto  = new Produto ("Notebook", 1500.0);
        System.out.printf("Produto: %s, Preço: %.2f", 
        	produto.getNome(), produto.getPreco());
    }
}
