package net.oluciano.maven.webstore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.stella.tinytype.CPF;
import net.oluciano.maven.Produto;

@WebServlet(urlPatterns={"/contato"})
public class ContatoServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 315153402718126570L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	boolean isValid = new CPF("1234567890").isValido();

    	Produto produto = new Produto("Smartphone", 1000.0);    	
    	
        PrintWriter writer = resp.getWriter();
        writer.println("<html><h1>Meu primeiro Servlet</h1></html>");
        
        writer.println("<html><p>O CPF 1234567890 é válido: " + isValid + "</p></html>");
        
        writer.println("<html><p>Produto: " + produto.getNome() + ", Valor: " + produto.getPreco() + "</p></html>");
        writer.close();
    }
}
