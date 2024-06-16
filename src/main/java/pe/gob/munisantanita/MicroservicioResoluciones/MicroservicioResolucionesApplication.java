package pe.gob.munisantanita.MicroservicioResoluciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MicroservicioResolucionesApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(MicroservicioResolucionesApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioResolucionesApplication.class, args);
	}
}
