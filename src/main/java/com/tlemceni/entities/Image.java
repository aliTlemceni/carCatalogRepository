package com.tlemceni.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Data
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imageName;

    private String imageType;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;


}
