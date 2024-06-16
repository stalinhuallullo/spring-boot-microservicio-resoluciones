package pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Mensaje;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Resolucion;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.ResponseResoluciones;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Resultados;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Service.ResolucionService;


@RestController
@RequestMapping("/resolucion")
public class ResolucionesController {
	
	@Autowired
	ResolucionService resolucionService;

	
	@GetMapping
	@ResponseBody
	public Resultados getResoluciones(
			@RequestParam(required = false) Integer tipo,
			@RequestParam(required = false) Integer area,
			@RequestParam(required = false) Integer numero,
			@RequestParam(required = false) Integer anio,
			@RequestParam(required = false) String palabra,
			@RequestParam(required = false) Integer electronico,
			@RequestParam(required = false) Integer mostrar,
			@RequestParam(required = false) Integer pag,
			@RequestParam(required = false) String orderby,
			@RequestParam(required = false) String orden
			){			

		return resolucionService.getResoluciones(tipo,area,numero,anio,palabra,electronico,mostrar,pag,orderby,orden);
	}
	
	@PostMapping
	@ResponseBody
	public Mensaje crearResolucion(@RequestBody Resolucion resolucion){	
		
		return resolucionService.crearResolucion(resolucion);
	}	

	
	@PutMapping
	@ResponseBody
	public Mensaje editarResolucion(@RequestBody Resolucion resolucion){		
		return resolucionService.editarResolucion(resolucion);
	}
	
	@PostMapping(value="/elimiar-archivo/{id}")
	@ResponseBody
	public Mensaje elimiarArchivoResolucion(@PathVariable(value="id", required = true) long id){
		
		return resolucionService.elimiarArchivoResolucion(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public Mensaje eliminarResolucion(@PathVariable(value="id", required = true) long id){		
		
	
		return resolucionService.eliminarResolucion(id);
	}
	
	
	@PostMapping(value="/all")
	@ResponseBody
	public ResponseResoluciones all(HttpServletRequest request, Model model, @RequestBody String json) {

		return resolucionService.all(request, model, json);
	} 
	
	@GetMapping(value="/{id}")
	@ResponseBody
	public Resolucion findId(@PathVariable(name = "id") int id) {
		
		Resolucion resolucion = new Resolucion();
		resolucion = resolucionService.findId(id);
		System.out.println("asdad " + resolucion);
		return resolucion;
	}	
	
	@GetMapping(value="/mey")
	public String mey() {
		
		return "mey";//resolucionService.all(request, model, json);
	}
	
	
	
}
