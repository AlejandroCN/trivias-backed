package com.trivias.backend.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "records")
public class Record implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long tiempo;
	
	@Column(name = "total_aciertos")
	private int totalAciertos;
	
	private Date fecha;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="records_preguntas",
    joinColumns=
        @JoinColumn(name="record_id", referencedColumnName="id"),
    inverseJoinColumns=
        @JoinColumn(name="pregunta_id", referencedColumnName="id")
    )
	private List<Pregunta> preguntas;
	
	@PrePersist
	public void prePresist() {
		this.fecha = new Date();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.fecha = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTiempo() {
		return tiempo;
	}
	
	public void setTiempo(Long tiempo) {
		this.tiempo = tiempo;
	}
	
	public int getTotalAciertos() {
		return totalAciertos;
	}
	
	public void setTotalAciertos(int totalAciertos) {
		this.totalAciertos = totalAciertos;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

}
