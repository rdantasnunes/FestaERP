package br.com.rdantasnunes.festaerp.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.rdantasnunes.festaerp.modelo.Usuario;


public class FiltroDeSeguranca implements Filter {

	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) req).getSession();
		String url = ((HttpServletRequest) req).getRequestURI();
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		//System.out.println("filtro "+url);
		if (usuario == null && !url.equals("/") 
				&& !url.startsWith("/welcome") 
				&& !url.startsWith("/index") 
				&& !url.startsWith("/javax.faces.resource/")) {
			req.getRequestDispatcher("/welcome.jsf").forward(req, res);
		} else {
			chain.doFilter(req, res);
		}
	}

	public void destroy() {
	}
}