package pe.gob.munisantanita.MicroservicioResoluciones.Areas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.munisantanita.MicroservicioResoluciones.Areas.Model.Area;
import pe.gob.munisantanita.MicroservicioResoluciones.Areas.Service.AreaService;


@RestController
@RequestMapping("/area")
public class AreasController {
	
	@Autowired
	AreaService areaService;

	@GetMapping
	@ResponseBody
	public List<Area> all(){		
		return areaService.all();
	}
	
}
