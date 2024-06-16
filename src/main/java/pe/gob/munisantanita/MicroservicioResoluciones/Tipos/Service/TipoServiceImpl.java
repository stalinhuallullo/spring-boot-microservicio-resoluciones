package pe.gob.munisantanita.MicroservicioResoluciones.Tipos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.munisantanita.MicroservicioResoluciones.Tipos.Model.Tipo;
import pe.gob.munisantanita.MicroservicioResoluciones.Tipos.Repository.TipoRepository;

@Service
public class TipoServiceImpl implements TipoService{
	
	@Autowired
	TipoRepository tipoRepository;
	
	
	@Override
	public List<Tipo> all() {
		return tipoRepository.all();
	}

}
