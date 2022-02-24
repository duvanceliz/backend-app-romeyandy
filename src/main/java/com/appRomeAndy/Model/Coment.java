package com.appRomeAndy.Model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Coment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String nombre;
	
	@Column
	private String comentario;
	
	@Column
	private String fecha;
	
	@Column
	private int meGustas;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<Reply> reply;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

	public int getMeGustas() {
		return meGustas;
	}

	public void setMeGustas(int meGustas) {
		this.meGustas = meGustas;
	}
	

	public Set<Reply> getReply() {
		return reply;
	}

	public void setReply(Set<Reply> reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "Coment [id=" + id + ", nombre=" + nombre + ", comentario=" + comentario + ", fecha=" + fecha
				+ ", meGustas=" + meGustas + ", reply=" + reply + "]";
	}


}
