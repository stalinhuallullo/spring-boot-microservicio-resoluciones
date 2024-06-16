package pe.gob.munisantanita.MicroservicioResoluciones.Tipos.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import pe.gob.munisantanita.MicroservicioResoluciones.Tipos.Model.Tipo;

@Repository
public class TipoRepositoryImpl implements TipoRepository{
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Override
	public List<Tipo> all() {
		 String sql = "select * from resoluciones.tipos where estado > 0";
		 List<Tipo> tipos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Tipo>(Tipo.class));
		 return tipos;
	}

}
