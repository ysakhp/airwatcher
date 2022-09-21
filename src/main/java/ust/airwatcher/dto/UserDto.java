package ust.airwatcher.dto;

import lombok.Builder;
import lombok.Data;
import ust.airwatcher.entity.User;

@Data
@Builder
public class UserDto {
    private String username;
    private String password;
    private String name;


    public User getUserFromDto() {

        return User.builder().userName(username).password(password).name(name).build();

    }
}
