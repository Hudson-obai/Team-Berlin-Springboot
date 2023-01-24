package com.spaceyatech.berlin.response;

import com.spaceyatech.berlin.models.Role;
import lombok.*;
import java.util.List;
import java.util.UUID;


@Data
//@Builder
@NoArgsConstructor
public class AllUsersResponse {

    private UUID id;
    private String username;
    private String email;
    private String phone_number;
    private String date_created;
    private List<Role> role;


}
