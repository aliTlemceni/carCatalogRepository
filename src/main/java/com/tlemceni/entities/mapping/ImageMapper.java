package com.tlemceni.entities.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.tlemceni.entities.Image;
import com.tlemceni.entities.dto.ImageDto;

@Mapper(componentModel = "spring", uses = { CarMapper.class })
public interface ImageMapper extends EntityMapper<ImageDto, Image> {
	@Mappings({ @Mapping(source = "car.id", target = "idCar") })
	ImageDto toDto(Image image);

	@Mappings({ @Mapping(source = "idCar", target = "car.id") })
	Image toEntity(ImageDto imageDto);

	default Image fromId(Long id) {
		if (id == null) {
			return null;
		}
		Image image = new Image();
		image.setId(id);
		return image;
	}
}
