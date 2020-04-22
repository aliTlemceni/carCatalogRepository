package com.tlemceni.security.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "roles")
public class Role{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	private static final long serialVersionUID = 1L;

	
	private String roleName;
	
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonCreator
	public Role(@JsonProperty("id")Long id, @JsonProperty("roleName")String roleName, @JsonProperty("users")List<User> users) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.users = users;
	}
	
//    @LazyCollection(LazyCollectionOption.FALSE)
//	@ManyToMany(mappedBy = "roles")
//	private List<Permission> permissions;

    
}