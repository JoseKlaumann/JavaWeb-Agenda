package model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_agenda")
public class JavaBeans {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idCon;
	private String nome;
	private String fone;
	private String email;
	
	public JavaBeans() {
		super();
	}
	
	public JavaBeans(String idCon, String nome, String fone, String email) {
		super();
		this.idCon = idCon;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}
	
	public String getIdCon() {
		return idCon;
	}
	public void setIdCon(String idCon) {
		this.idCon = idCon;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JavaBeans other = (JavaBeans) obj;
		return Objects.equals(idCon, other.idCon);
	}

	@Override
	public String toString() {
		return "JavaBeans [idCon=" + idCon + ", nome=" + nome + ", fone=" + fone + ", email=" + email + "]";
	}	
}
