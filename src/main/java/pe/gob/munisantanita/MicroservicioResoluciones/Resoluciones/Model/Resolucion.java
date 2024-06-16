package pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model;

public class Resolucion {

	private Long id;	
	private int tipo_id;
	private String tipo_nombre;
	private int area_id;
	private Long nro_resolucion;	
	private String detalle;
	private String sumilla;
	private String archivo;
	private String archivo_token;
	private int anio;
	private int electronico;
	private String fecha_emision;	
	private String fecha_registro;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTipo_id() {
		return tipo_id;
	}
	public void setTipo_id(int tipo_id) {
		this.tipo_id = tipo_id;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public Long getNro_resolucion() {
		return nro_resolucion;
	}
	public void setNro_resolucion(Long nro_resolucion) {
		this.nro_resolucion = nro_resolucion;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getSumilla() {
		return sumilla;
	}
	public void setSumilla(String sumilla) {
		this.sumilla = sumilla;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
    public String getArchivo_token() {
		return archivo_token;
	}
	public void setArchivo_token(String archivo_token) {
		this.archivo_token = archivo_token;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getElectronico() {
		return electronico;
	}
	public void setElectronico(int electronico) {
		this.electronico = electronico;
	}
	public String getFecha_emision() {
		return fecha_emision;
	}
	public void setFecha_emision(String fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getTipo_nombre() {
		return tipo_nombre;
	}
	public void setTipo_nombre(String tipo_nombre) {
		this.tipo_nombre = tipo_nombre;
	}

	
	
	
}
