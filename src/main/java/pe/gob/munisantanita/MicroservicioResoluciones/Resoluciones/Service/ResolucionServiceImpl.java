package pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Mensaje;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Resolucion;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.ResponseResoluciones;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Resultados;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Repository.ResolucionRepository;

@Service
public class ResolucionServiceImpl implements ResolucionService{

	@Autowired
	ResolucionRepository resolucionRepository;
	
	@Override
	public Resultados getResoluciones(Integer tipo, Integer area, Integer numero, Integer anio, String palabra,
			Integer electronico, Integer mostrar, Integer pag, String orderby, String orden) {
		return resolucionRepository.getResoluciones(tipo, area, numero, anio, palabra, electronico, mostrar, pag,orderby, orden);

	}
	
	@Override
	public ResponseResoluciones all(HttpServletRequest request, Model model, @RequestBody String json) {
		return resolucionRepository.all(request, model, json);
	}
	
	@Override
	public Mensaje crearResolucion(Resolucion resolucion) {
		return resolucionRepository.crearResolucion(resolucion);
	}
	
	@Override
	public Mensaje elimiarArchivoResolucion(long id) {
		return resolucionRepository.elimiarArchivoResolucion(id);
	}
	
	@Override
	public Mensaje editarResolucion(Resolucion resolucion) {
		return resolucionRepository.editarResolucion(resolucion);
	}
	
	public Mensaje eliminarResolucion(long id) {
		return resolucionRepository.eliminarResolucion(id);
	}
	
	@Override
	public Resolucion findId(int id) {
		return resolucionRepository.findId(id);
	}
	
	

}
