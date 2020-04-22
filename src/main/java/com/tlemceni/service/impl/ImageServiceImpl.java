package com.tlemceni.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.tlemceni.entities.Image;
import com.tlemceni.entities.dto.ImageDto;
import com.tlemceni.entities.mapping.ImageMapper;
import com.tlemceni.repository.ImageRepository;
import com.tlemceni.service.interf.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	private final ImageRepository imageRepository;

	private final ImageMapper imageMapper;

	public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper) {
		super();
		this.imageRepository = imageRepository;
		this.imageMapper = imageMapper;
	}

	@Override
	public List<ImageDto> saveAllImage(List<ImageDto> imageDtos) {
		List<ImageDto> res = this.imageMapper
				.toDtos((List<Image>) this.imageRepository.saveAll(this.imageMapper.toEntities(imageDtos)));

		return res;
	}

	@Override
	public List<ImageDto> getAllByCarId(Long cadreId) {
		List<ImageDto> res = this.imageMapper.toDtos(this.imageRepository.getAllByCarId(cadreId));
		return res;
	}

	@Override
	public List<ImageDto> getAllImages() {
		List<ImageDto> res = this.imageMapper.toDtos((List<Image>) this.imageRepository.findAll());
		return res;
	}

	@Override
	public List<ImageDto> find25RandomImages() {
		List<ImageDto> randomImageDtos = new ArrayList<ImageDto>();
		List<ImageDto> imageDtos = this.imageMapper.toDtos(this.imageRepository.find25RandomImages());
		Set<Long> listOfIds = new HashSet<Long>();
		for(ImageDto imageDto: imageDtos) {
			listOfIds.add(imageDto.getIdCar());
		}
		
		for(Long id: listOfIds) {
			System.out.println(id);
			randomImageDtos.add(this.imageMapper.toDto(this.imageRepository.findRandomImage(id)));
		}
		return randomImageDtos;
	}

	@Override
	public ImageDto findRandomImageById(Long idCar) {
		return this.imageMapper.toDto(this.imageRepository.findRandomImage(idCar));
	}

	@Override
	public List<ImageDto> findAllByCarId(Long idCar) {
		return this.imageMapper.toDtos(this.imageRepository.findAllByCarId(idCar));
	}
	
	

}
