package com.msut.mappers;

import com.msut.domain.User;
import com.msut.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by mariusz on 10.02.17.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "authorities", target = "roles")
    UserDto userToUserDto(User user);

    default String grantedAuthorityToString(GrantedAuthority grantedAuthority) {
        return grantedAuthority.getAuthority();
    }

}
