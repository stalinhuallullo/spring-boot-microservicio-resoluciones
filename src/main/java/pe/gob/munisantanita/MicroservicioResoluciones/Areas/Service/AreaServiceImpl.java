package pe.gob.munisantanita.MicroservicioResoluciones.Areas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.munisantanita.MicroservicioResoluciones.Areas.Model.Area;
import pe.gob.munisantanita.MicroservicioResoluciones.Areas.Repository.AreaRepository;

@Service
public class AreaServiceImpl implements AreaService{
	
	
	
	@Autowired
	AreaRepository areaRepository;
	
	
	@Override
	public List<Area> all() {
		return areaRepository.all();
	}

}
