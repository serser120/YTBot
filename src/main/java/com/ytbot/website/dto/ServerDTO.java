package com.ytbot.website.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServerDTO extends GenericDTO{
    private String serverName;
    private String serverDiscordID;
    private Set<Long> serverVideoHistories;
    private Set<ServerVideoHistoryDTO> serverVideoHistoryDTOS;
}
