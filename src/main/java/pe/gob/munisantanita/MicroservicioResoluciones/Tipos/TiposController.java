package pe.gob.munisantanita.MicroservicioResoluciones.Tipos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.munisantanita.MicroservicioResoluciones.Tipos.Model.Tipo;
import pe.gob.munisantanita.MicroservicioResoluciones.Tipos.Service.TipoService;

@RestController
@RequestMapping("/tipo")
public class TiposController {
	
	@Autowired
	TipoService tipoService;

	@GetMapping
	@ResponseBody
	public List<Tipo> all(){
		return tipoService.all();
	}

}
