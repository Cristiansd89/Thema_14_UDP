package Practica_02_Objetos_01;

import java.io.Serializable;

public class Contacto implements Serializable, Comparable {

	private String telefono;
	private String nombre;

	public Contacto(String nombre, String telefono) {

		this.telefono = telefono;
		this.nombre = nombre;

	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() { // este m�todo dobrecargado muestra los atributos de la clase
		return this.nombre + " (" + this.telefono + ")";
	}

	public boolean equals(Contacto otro) {

		return (nombre.equals(otro.nombre) && otro.telefono.equals(telefono));

	}

///////////////////////////////////
	public boolean equals(Object otro) {
		Contacto contacto = (Contacto) otro;
		return (nombre.equals(contacto.nombre) && telefono.equals(contacto.telefono));

	}

////////////////////////////////////
	public int compareTo(Contacto other) {

		return this.nombre.compareTo(other.getNombre());

	}

	/////////////////////////////////////////
	public boolean equalsVictor(Contacto otro) {

		return (nombre.equals(otro.nombre) && otro.telefono.equals(telefono));

	}

	@Override
	public int compareTo(Object arg0) {
		Contacto contacto = (Contacto) arg0;
		return (nombre.compareTo(contacto.nombre));
	}
}
