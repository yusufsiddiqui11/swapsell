package com.stackroute.userservice.configuration;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
