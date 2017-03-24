package com.marmar.guiapp.db;

public class Docente {

	private int _id;
	private String cve_acceso;
	private String nombre_docente;
	private String programa;

	public Docente() {
		super();
	}

	public Docente(int _id, String cve_acceso, String nombre_docente,
			String programa) {
		super();
		this._id = _id;
		this.cve_acceso = cve_acceso;
		this.nombre_docente = nombre_docente;
		this.programa = programa;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getCve_acceso() {
		return cve_acceso;
	}

	public void setCve_acceso(String cve_acceso) {
		this.cve_acceso = cve_acceso;
	}

	public String getNombre_docente() {
		return nombre_docente;
	}

	public void setNombre_docente(String nombre_docente) {
		this.nombre_docente = nombre_docente;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	@Override
	public String toString() {
		return "Docente [_id=" + _id + ", cve_acceso=" + cve_acceso
				+ ", nombre_docente=" + nombre_docente + ", programa="
				+ programa + "]";
	}

}
