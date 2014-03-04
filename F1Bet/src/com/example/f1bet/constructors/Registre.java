//Constructor d'un registre.
package com.example.f1bet.constructors;

public class Registre {

	private String nom, correu, contrasenya;

	public Registre() {
	}

	public Registre(String nom, String correu, String contrasenya) {
		super();
		this.nom = nom;
		this.correu = correu;
		this.contrasenya = contrasenya;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCorreu() {
		return correu;
	}

	public void setCorreu(String correu) {
		this.correu = correu;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	@Override
	public String toString() {
		return "Registre [nom=" + nom + ", correu=" + correu + ", contrasenya="
				+ contrasenya + "]";
	}
}
