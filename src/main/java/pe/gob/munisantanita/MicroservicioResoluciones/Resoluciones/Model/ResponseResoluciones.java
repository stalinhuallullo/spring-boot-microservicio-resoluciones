package pe.gob.munisantanita.MicroservicioResoluciones.Resoluciones.Model;

import java.util.List;

public class ResponseResoluciones {
	
	private int draw;
	private int iTotalDisplayRecords;
	private int iTotalRecords;
	
	private List<Resolucion> aaData;
	
	
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Resolucion> getAaData() {
		return aaData;
	}
	public void setAaData(List<Resolucion> aaData) {
		this.aaData = aaData;
	}	
		
}
