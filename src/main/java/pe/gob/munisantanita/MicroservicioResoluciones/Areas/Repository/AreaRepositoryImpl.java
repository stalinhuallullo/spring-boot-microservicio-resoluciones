package pe.gob.munisantanita.MicroservicioResoluciones.Areas.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import pe.gob.munisantanita.MicroservicioResoluciones.Areas.Model.Area;


@Repository
public class AreaRepositoryImpl implements AreaRepository{
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Area> all() {
		 String sql = "select * from resoluciones.areas where estado > 0";
		 List<Area> areas = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Area>(Area.class));
		 return areas;
	}

}
