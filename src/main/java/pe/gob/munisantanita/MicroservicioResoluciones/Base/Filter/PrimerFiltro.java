package pe.gob.munisantanita.MicroservicioResoluciones.Base.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Component;


@Component
public class PrimerFiltro implements Filter{
	
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		    HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
			String token = req.getHeader("token-CSRF");
			
			chain.doFilter(request, response);
			/*
			if(token != null) {
			    String Desencriptado = AES.decrypt(token);
			    String [] sep = Desencriptado.split("@");

			    if(sep.length == 3) {
			    	
			    	long tiempoObtenido = Long.parseLong(sep[2]);
			    	long tiempoActual = System.currentTimeMillis()/1000;
			    	
			    	if( tiempoActual - tiempoObtenido <= 60) {
			    		chain.doFilter(request, response);
			    	} else res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "La sesión del token ha caducado");
			    	
			    } else res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Parámetros del token son incorrectos");

			}
			else res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "El servicio requiere autorización");
			*/
	}

}
