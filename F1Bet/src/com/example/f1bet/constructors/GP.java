//Constructor de una carrera (GP).
package com.example.f1bet.constructors;

import java.util.Date;

public class GP {

	private String nom, ciutat, pais;
	private Date data;
	private int voltes;
	private float longitud, distancia;
	private int imatge;
	private String linkimatge;

	public GP(String nom, String ciutat, String pais, Date data, int voltes,
			float longitud, float distancia, int imatge, String linkimatge) {
		super();
		this.nom = nom;
		this.ciutat = ciutat;
		this.pais = pais;
		this.data = data;
		this.voltes = voltes;
		this.longitud = longitud;
		this.distancia = distancia;
		this.imatge = imatge;
		this.linkimatge = linkimatge;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCiutat() {
		return ciutat;
	}

	public void setCiutat(String ciutat) {
		this.ciutat = ciutat;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getVoltes() {
		return voltes;
	}

	public void setVoltes(int voltes) {
		this.voltes = voltes;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public int getImatge() {
		return imatge;
	}

	public void setImatge(int imatge) {
		this.imatge = imatge;
	}

	public String getLinkimatge() {
		return linkimatge;
	}

	public void setLinkimatge(String linkimatge) {
		this.linkimatge = linkimatge;
	}

}
