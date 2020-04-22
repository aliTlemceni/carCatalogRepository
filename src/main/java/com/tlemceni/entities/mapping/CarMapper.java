package com.tlemceni.entities.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.tlemceni.entities.Car;
import com.tlemceni.entities.dto.CarDto;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface CarMapper extends EntityMapper<CarDto, Car> {
	
	@Mappings({ @Mapping(source = "user.id", target = "idUser"),
		        @Mapping(source = "comments", target = "commentDtos")})
	CarDto toDto(Car car);

	@Mappings({ @Mapping(source = "idUser", target = "user.id"),
                @Mapping(source = "commentDtos", target = "comments") })
	Car toEntity(CarDto carDto);

	default Car fromId(Long id) {
		if (id == null) {
			return null;
		}
		Car car = new Car();
		car.setId(id);
		return car;
	}

}
