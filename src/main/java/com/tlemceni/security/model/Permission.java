package com.tlemceni.security.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Role> roles;
	
//	@ManyToMany(mappedBy = "permissions")
//    @JsonIgnore
//    private List<Role> roles = new ArrayList<Role>();

}
