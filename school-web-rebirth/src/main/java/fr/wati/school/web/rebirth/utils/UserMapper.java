package fr.wati.school.web.rebirth.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import fr.wati.school.entities.bean.Users;
import fr.wati.school.web.rebirth.domain.UserDto;

public class UserMapper {

	public static UserDto map(Users user) {
			UserDto dto = new UserDto();
			dto.setId(Integer.valueOf(user.getId().toString()));
			dto.setUsername(user.getUsername());
			return dto;
	}
	
	public static List<UserDto> map(Page<Users> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (Users user: users) {
			dtos.add(map(user));
		}
		return dtos;
	}
}
