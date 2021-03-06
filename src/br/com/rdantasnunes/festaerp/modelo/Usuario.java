package br.com.rdantasnunes.festaerp.modelo;

import java.io.Serializable;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

/**
 * 
 * Created on 14/05/2014
 * 
 * @author Rodrigo Dantas Nunes - http://www.linkedin.com/in/rdantasnunes - rdantasnunes(at)gmail(dot)com
 * 
 * Entidade Usuario usada para representar os dados do usu�rio do sistema.
 * 
 */
@Entity
public class Usuario extends SuperEntity<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String nome;
	
	@Index
	private String login;
	
	private String senha;
	
	private Boolean admin;
	
	private Key<Caixa> caixa_id;
	@Ignore private Caixa caixa;

	public Usuario() {
		super();
	}

	public Usuario(String nome, String login, String senha, Boolean admin, Caixa caixa) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.admin = admin;
		setCaixa(caixa);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Caixa getCaixa() {
		if(this.caixa == null && caixa_id != null){
			this.caixa = (Caixa)get(Caixa.class,caixa_id.getId());
		}
		return this.caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
		if(caixa != null && caixa.getId() != null){
			this.caixa_id = Key.create(Caixa.class,caixa.getId());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + "]";
	}
	
}
