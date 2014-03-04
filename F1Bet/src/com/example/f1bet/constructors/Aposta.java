//Constructor d'una aposta.
package com.example.f1bet.constructors;

public class Aposta {

	private String id, posicio1, posicio2, posicio3, posicio4, posicio5,
			voltarapida;

	public Aposta(String id, String posicio1, String posicio2, String posicio3,
			String posicio4, String posicio5, String voltarapida) {
		this.id = id;
		this.posicio1 = posicio1;
		this.posicio2 = posicio2;
		this.posicio3 = posicio3;
		this.posicio4 = posicio4;
		this.posicio5 = posicio5;
		this.voltarapida = voltarapida;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosicio1() {
		return posicio1;
	}

	public void setPosicio1(String posicio1) {
		this.posicio1 = posicio1;
	}

	public String getPosicio2() {
		return posicio2;
	}

	public void setPosicio2(String posicio2) {
		this.posicio2 = posicio2;
	}

	public String getPosicio3() {
		return posicio3;
	}

	public void setPosicio3(String posicio3) {
		this.posicio3 = posicio3;
	}

	public String getPosicio4() {
		return posicio4;
	}

	public void setPosicio4(String posicio4) {
		this.posicio4 = posicio4;
	}

	public String getPosicio5() {
		return posicio5;
	}

	public void setPosicio5(String posicio5) {
		this.posicio5 = posicio5;
	}

	public String getVoltarapida() {
		return voltarapida;
	}

	public void setVoltarapida(String voltarapida) {
		this.voltarapida = voltarapida;
	}

	@Override
	public String toString() {
		return "Aposta [id=" + id + ", posicio1=" + posicio1 + ", posicio2="
				+ posicio2 + ", posicio3=" + posicio3 + ", posicio4="
				+ posicio4 + ", posicio5=" + posicio5 + ", voltarapida="
				+ voltarapida + "]";
	}
}
