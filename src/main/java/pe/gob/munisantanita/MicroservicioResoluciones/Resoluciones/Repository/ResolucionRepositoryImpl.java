package pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Mensaje;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Resolucion;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.ResponseResoluciones;
import pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model.Resultados;

@Repository
public class ResolucionRepositoryImpl implements ResolucionRepository{
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	 NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Resultados getResoluciones(Integer tipo, Integer area, Integer numero, Integer anio, String palabra, Integer electronico, Integer mostrar, Integer pag, String orderby, String orden) {

		
    	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("resoluciones").withProcedureName("SP_RESOLUCIONES_FILTRAR");
    	
		Map<String, Object> inParamMap = new HashMap<String, Object>();

		pag = (pag != null && pag > 0) ? pag : 1;
		
		inParamMap.put("tipo_id", tipo != null ? tipo : 0);
		inParamMap.put("area_id", area != null ? area : 0);
		inParamMap.put("numero", numero != null ? numero : 0);
		inParamMap.put("anio", anio != null ? anio : 0);
		inParamMap.put("palabra", palabra != null ? palabra : "");
		inParamMap.put("electronico", electronico != null ? electronico : 0);
		inParamMap.put("mostrar", mostrar != null ? mostrar : 10);
		inParamMap.put("pag", pag != null ? pag : 1);
		inParamMap.put("orderby", orderby );
		inParamMap.put("orden", orden);

		
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
        ArrayList<Object> arrayTotal = (ArrayList) simpleJdbcCallResult.get("#result-set-1");
        Map<String, Object> total = (Map) arrayTotal.get(0);
        
		ArrayList<Object> arrayList = (ArrayList) simpleJdbcCallResult.get("#result-set-2");

        int total_resultados = Integer.parseInt(total.get("total_registros").toString());
        int total_paginas = Integer.parseInt(total.get("total_paginas").toString());

        
        
        System.out.println("arrayList"+arrayList.size());
        
		Resultados resultados = new Resultados();
		
		List<Resolucion> datos = new ArrayList<>();
		for(int i = 0; i < arrayList.size(); i++) {
        	Map<String, Object> rs = (Map<String, Object>) arrayList.get(i);
    		Resolucion r = new Resolucion();

    		r.setId(rs.get("id") != null ? Long.parseLong(rs.get("id").toString()) : 0);
    		r.setTipo_id(rs.get("tipo_id") != null ? Integer.parseInt(rs.get("tipo_id").toString()) : 0);
    		r.setTipo_nombre(rs.get("tipo_nombre") != null ? rs.get("tipo_nombre").toString() : "");
    		
    		r.setArea_id(rs.get("area_id") != null ? Integer.parseInt(rs.get("area_id").toString()) : 0);
    		r.setNro_resolucion(rs.get("nro_resolucion") != null ? Long.parseLong(rs.get("nro_resolucion").toString()) : 0);
    		r.setDetalle(rs.get("detalle") != null ? rs.get("detalle").toString() : "");
    		r.setSumilla(rs.get("sumilla") != null ? rs.get("sumilla").toString() : ""); //rs.get("sumilla").toString()
    		r.setArchivo(rs.get("archivo") != null ? rs.get("archivo").toString() : "");
    		r.setAnio(rs.get("anio") != null ? Integer.parseInt(rs.get("anio").toString()) : 0);
    		r.setElectronico(rs.get("electronico") != null ? Integer.parseInt(rs.get("electronico").toString()) : 0);
    		r.setFecha_emision(rs.get("fecha_emision") != null ? rs.get("fecha_emision").toString() : "");
    		r.setFecha_registro(rs.get("fecha_registro") != null ? rs.get("fecha_registro").toString() : "");



    		datos.add(r);
        }
        
        resultados.setDatos(datos);

        resultados.setTotal_resultados(total_resultados);
        resultados.setTotal_paginas(total_paginas);
        resultados.setPag_actual(pag);

        return resultados;

		
	
	}
	

	public ResponseResoluciones all(HttpServletRequest request, Model model, @RequestBody String json) {
	
	    try {
	        JSONObject obj = new JSONObject(json);
	        
	        int draw = obj.getInt("draw");
	        int row =  obj.getInt("start");
	        int rowperpage = obj.getInt("length"); // Números de filas por pag.
	        
	        JSONArray arrOrden = obj.getJSONArray("order");
	        JSONObject arrOrden_ = arrOrden.getJSONObject(0);
	        int columnIndex = arrOrden_.getInt("column"); // Columna index
	
	        JSONArray arrColumnas = obj.getJSONArray("columns");
	        JSONObject arrColumnas_ = arrColumnas.getJSONObject(columnIndex);
	        String columnName = arrColumnas_.getString("data"); // Columna nombre
	        
	        JSONArray arrColumnSortOrder = obj.getJSONArray("order");
	        JSONObject arrColumnSortOrder_ = arrColumnSortOrder.getJSONObject(0);
	        String columnSortOrder = arrColumnSortOrder_.getString("dir"); // Columna nombre
	        
	        JSONObject searchObj = obj.getJSONObject("search");
	        String searchValue = searchObj.getString("value"); // Search value
	        
	        
	        
		    SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("resoluciones").withProcedureName("SP_RESOLUCIONES_LISTAR");
		
		    Map<String, Object> inParamMap = new HashMap<String, Object>();
		    
		    inParamMap.put("desde", row);
		    inParamMap.put("hasta", row + rowperpage);
		    inParamMap.put("orderby", columnName +" "+columnSortOrder);
		    inParamMap.put("buscar", searchValue);
		    
		    inParamMap.put("mostrar", rowperpage > 0  ? rowperpage : 10);
			inParamMap.put("pag", row > 0 ? row : 1);
		    
		    SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		    
		    Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		    
		    
		    ArrayList<Object> arrayList = (ArrayList) simpleJdbcCallResult.get("#result-set-1");
		    ArrayList<Object> arrayTotal = (ArrayList) simpleJdbcCallResult.get("#result-set-2");
		    
		    Map<String, Object> total = (Map) arrayTotal.get(0);
		    
		    int totalRegistro = Integer.parseInt(total.get("total").toString());
		    
		    ResponseResoluciones r = new ResponseResoluciones();
		    r.setDraw(draw);
		    r.setiTotalDisplayRecords(totalRegistro);
		    r.setiTotalRecords(totalRegistro);
		    
		    
		    List<Resolucion> datos = new ArrayList<>();
		    
		    for(int i = 0; i < arrayList.size(); i++) {
		        Map<String, Object> rs = (Map<String, Object>) arrayList.get(i);
		        Resolucion re = new Resolucion();
		
		        re.setId(rs.get("id") != null ? Long.parseLong(rs.get("id").toString()) : 0);
		        re.setTipo_id(rs.get("tipo_id") != null ? Integer.parseInt(rs.get("tipo_id").toString()) : 0);
		        re.setTipo_nombre(rs.get("tipo_nombre") != null ? rs.get("tipo_nombre").toString() : "");
		        
		        re.setArea_id(rs.get("area_id") != null ? Integer.parseInt(rs.get("area_id").toString()) : 0);
		        re.setNro_resolucion(rs.get("nro_resolucion") != null ? Long.parseLong(rs.get("nro_resolucion").toString()) : 0);
		        re.setDetalle(rs.get("detalle") != null ? rs.get("detalle").toString() : "");
		        re.setSumilla(rs.get("sumilla") != null ? rs.get("sumilla").toString() : ""); //rs.get("sumilla").toString()
		        re.setArchivo(rs.get("archivo") != null ? rs.get("archivo").toString() : "");
		        re.setAnio(rs.get("anio") != null ? Integer.parseInt(rs.get("anio").toString()) : 0);
		        re.setElectronico(rs.get("electronico") != null ? Integer.parseInt(rs.get("electronico").toString()) : 0);
		        re.setFecha_emision(rs.get("fecha_emision") != null ? rs.get("fecha_emision").toString() : "");
		        re.setFecha_registro(rs.get("fecha_registro") != null ? rs.get("fecha_registro").toString() : "");
		
		        datos.add(re);
		    }
		    
		    r.setAaData(datos);
		
		
		    return r;
		
		} catch (JSONException err) {
			System.out.println("Error"+ err.toString());
		}
		return null;
	}
	
	
	public Mensaje crearResolucion(Resolucion resolucion) {

		Mensaje m = new Mensaje();
		
		if(resolucion != null) {
	    	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("resoluciones").withProcedureName("SP_RESOLUCIONES_CREAR");
	    	
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			/*
		    StringBuilder XML = new StringBuilder();
		    if(noticia.getImagenes() != null)
		    noticia.getImagenes().stream().forEach((i)-> {
					XML.append("<fila><archivo_url>"+i.getArchivo_url()+"</archivo_url><token>"+i.getToken()+"</token></fila>");
			});
		    */
			
			inParamMap.put("tipo_id", resolucion.getTipo_id());
			inParamMap.put("area_id", resolucion.getArea_id());
			inParamMap.put("nro_resolucion", resolucion.getNro_resolucion());
			inParamMap.put("detalle", resolucion.getDetalle());
			inParamMap.put("sumilla", resolucion.getSumilla());
			inParamMap.put("archivo", resolucion.getArchivo());
			inParamMap.put("anio", resolucion.getAnio());
			inParamMap.put("electronico", resolucion.getElectronico());
			inParamMap.put("fecha_emision", resolucion.getFecha_emision());
			inParamMap.put("fecha_registro", resolucion.getFecha_registro());
			inParamMap.put("archivo_token", resolucion.getArchivo_token());
		
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			
		
			if(simpleJdbcCallResult.get("sp_error") == null) {
				m.setMensaje( simpleJdbcCallResult.get("sp_aviso").toString() );  // 
				m.setId( Long.parseLong(simpleJdbcCallResult.get("sp_id").toString()) ); // 
				m.setEstado("OK");
				
			} else {
				m.setMensaje(simpleJdbcCallResult.get("sp_error").toString());
				m.setEstado("ERR");
			}
			
		}

		return m;
		
	}

	public Mensaje editarResolucion(Resolucion resolucion) {
		
    	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("resoluciones").withProcedureName("SP_RESOLUCIONES_EDITAR");
    	
		Map<String, Object> inParamMap = new HashMap<String, Object>();

		
	    /*StringBuilder XML = new StringBuilder();
	    noticia.getImagenes().stream().forEach((i)-> {
				XML.append("<fila><archivo_url>"+i.getArchivo_url()+"</archivo_url><token>"+i.getToken()+"</token></fila>");
		});*/
		
		inParamMap.put("id", resolucion.getId());
		inParamMap.put("tipo_id", resolucion.getTipo_id());
		inParamMap.put("area_id", resolucion.getArea_id());
		inParamMap.put("nro_resolucion", resolucion.getNro_resolucion());
		inParamMap.put("detalle", resolucion.getDetalle());
		inParamMap.put("sumilla", resolucion.getSumilla());
		inParamMap.put("archivo", resolucion.getArchivo());
		inParamMap.put("anio", resolucion.getAnio());
		inParamMap.put("electronico", resolucion.getElectronico());
		inParamMap.put("fecha_emision", resolucion.getFecha_emision());
		inParamMap.put("fecha_registro", resolucion.getFecha_registro());
		inParamMap.put("archivo_token", resolucion.getArchivo_token());
		//inParamMap.put("XML", XML);
		
		
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		Mensaje m = new Mensaje();
		
		if(simpleJdbcCallResult.get("sp_error") == null) {
			m.setMensaje(simpleJdbcCallResult.get("sp_aviso").toString());
			m.setId(resolucion.getId());
			m.setEstado("OK");
			
		} else {
			m.setMensaje(simpleJdbcCallResult.get("sp_error").toString());
			m.setEstado("ERR");
		}
		
		return m;

		
	}
	
	public Mensaje elimiarArchivoResolucion(long id) {
		
    	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("resoluciones").withProcedureName("SP_RESOLUCIONES_ELIMINAR_ARCHIVO");
    	
		Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("id", id);
		
		
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		Mensaje m = new Mensaje();
		
		if(simpleJdbcCallResult.get("sp_error") == null) {
			m.setMensaje(simpleJdbcCallResult.get("sp_aviso").toString());
			m.setEstado("OK");
			
		} else {
			m.setMensaje(simpleJdbcCallResult.get("sp_error").toString());
			m.setEstado("ERR");
		}
		
		return m;

		
	}
	
	
	@Override
	public Mensaje eliminarResolucion(long id) {
		Mensaje m = new Mensaje();
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("resoluciones").withProcedureName("SP_RESOLUCIONES_ELIMINAR");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("id", id);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		if(simpleJdbcCallResult.get("sp_error") == null) {
			m.setMensaje(simpleJdbcCallResult.get("sp_aviso").toString());
			m.setId(id);
			m.setEstado("OK");
			
		} else {
			m.setMensaje(simpleJdbcCallResult.get("sp_error").toString());
			m.setEstado("ERR");
		}
		  
		return m;
	}
	
	
	
	@Override
	public Resolucion findId(int id) {
	    SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("resoluciones").withProcedureName("SP_RESOLUCIONES_BUSCAR_POR_ID");

	    Map<String, Object> inParamMap = new HashMap<String, Object>();
	    inParamMap.put("id", id);
	    System.out.println("id ==> " + id);

	    SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	    Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);

	    System.out.println("size " + simpleJdbcCallResult);
	    
	    Object objResolucion = simpleJdbcCallResult.get("#result-set-1");
		ArrayList<?> arrayListResolucion = (ArrayList<?>) objResolucion;
	    
		Resolucion resolucion = null;
		
	    if(arrayListResolucion.size() > 0) {
	    	
	    	Map<ArrayList<?>, Object> arrayResolucion = (Map<ArrayList<?>, Object>) arrayListResolucion.get(0);
	    	System.out.println("arrayResolucion " + arrayResolucion);
	    	resolucion = new Resolucion();
	    	resolucion.setId( 				Long.parseLong(arrayResolucion.get("id").toString()) );
	    	resolucion.setTipo_id( 			Integer.parseInt(arrayResolucion.get("tipo_id").toString()) );
	    	resolucion.setArea_id( 			Integer.parseInt(arrayResolucion.get("area_id").toString()) );
	    	resolucion.setNro_resolucion( 	Long.parseLong(arrayResolucion.get("nro_resolucion").toString()) );
	    	resolucion.setDetalle( 			arrayResolucion.get("detalle").toString() );
	    	resolucion.setSumilla( 			arrayResolucion.get("sumilla").toString() );
	    	resolucion.setArchivo( 			(arrayResolucion.get("archivo") != null) ? arrayResolucion.get("archivo").toString() : "" );
            resolucion.setArchivo_token(	(arrayResolucion.get("archivo_token") != null) ? arrayResolucion.get("archivo_token").toString() : "" );
	    	resolucion.setAnio( 			Integer.parseInt(arrayResolucion.get("anio").toString()) );
	    	resolucion.setElectronico( 		Integer.parseInt(arrayResolucion.get("electronico").toString()) );
	    	resolucion.setFecha_emision( 	arrayResolucion.get("fecha_emision").toString() );
	    	resolucion.setFecha_registro( 	arrayResolucion.get("fecha_registro").toString() );
	    	
	    }
	    

	    return resolucion;
	}

}
