package com.stackroute.authenticationservice.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
