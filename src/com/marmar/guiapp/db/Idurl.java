package com.marmar.guiapp.db;

public class Idurl {

	private int _id;
	private String url_name;
	private String descripcion_url;
	private String idioma;
	private String acceso;

	public Idurl() {
		super();
	}

	public Idurl(int _id, String url_name, String descripcion_url,
			String idioma, String acceso) {
		super();
		this._id = _id;
		this.url_name = url_name;
		this.descripcion_url = descripcion_url;
		this.idioma = idioma;
		this.acceso = acceso;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getUrl_name() {
		return url_name;
	}

	public void setUrl_name(String url_name) {
		this.url_name = url_name;
	}

	public String getDescripcion_url() {
		return descripcion_url;
	}

	public void setDescripcion_url(String descripcion_url) {
		this.descripcion_url = descripcion_url;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getAcceso() {
		return acceso;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	@Override
	public String toString() {
		return "Idurl [url_name=" + url_name + ", descripcion_url="
				+ descripcion_url + ", idioma=" + idioma + "]";
	}

}
