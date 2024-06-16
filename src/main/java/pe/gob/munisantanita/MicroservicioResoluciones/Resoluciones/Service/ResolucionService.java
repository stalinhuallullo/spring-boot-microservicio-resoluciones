package pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Mensaje;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Resolucion;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.ResponseResoluciones;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Resultados;

public interface ResolucionService {

	public Resultados getResoluciones(Integer tipo, Integer area, Integer numero, Integer anio, String palabra, Integer electronico, Integer mostrar, Integer pag, String orderby, String orden);
	public ResponseResoluciones all(HttpServletRequest request, Model model, @RequestBody String json);
	public Resolucion findId(int id);
	public Mensaje crearResolucion(Resolucion resolucion);
	public Mensaje editarResolucion(Resolucion resolucion);
	public Mensaje elimiarArchivoResolucion(long id);
	public Mensaje eliminarResolucion(long id);
}
