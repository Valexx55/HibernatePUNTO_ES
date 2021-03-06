package edu.val.dto.oracle;
// Generated 24-jun-2017 12:37:16 by Hibernate Tools 4.3.1.Final

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Registro generated by hbm2java
 */
@Entity
@Table(name = "REGISTRO", schema = "HR")
public class Registro implements java.io.Serializable {

	private UUID id;

	public Registro() {
	}

	public Registro(UUID id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false, length = 20)
	public UUID getId() {
		return this.id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}
