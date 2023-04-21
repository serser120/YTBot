package com.ytbot.website.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO extends GenericDTO {
    private String title;
    private String description;
}
