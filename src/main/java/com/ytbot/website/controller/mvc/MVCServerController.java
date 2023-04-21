package com.ytbot.website.controller.mvc;

import com.ytbot.website.dto.*;
import com.ytbot.website.model.ServerVideoHistory;
import com.ytbot.website.service.ServerService;
import com.ytbot.website.service.ServerVideoHistoryService;
import com.ytbot.website.service.VideoService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/servers")
@Hidden
@Slf4j
public class MVCServerController {

    private final ServerService serverService;
    private final VideoService videoService;
    private final ServerVideoHistoryService  serverVideoHistoryService;

    public MVCServerController(ServerService serverService, VideoService videoService, ServerVideoHistoryService  serverVideoHistoryService) {
        this.serverService = serverService;
        this.videoService = videoService;
        this.serverVideoHistoryService = serverVideoHistoryService;
    }

    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "serverName"));
        Page<ServerDTO> serverDTOS = serverService.listAll(pageRequest);
        model.addAttribute("servers", serverDTOS);
        return "servers/viewAllServers";
    }

    @GetMapping("/{id}")
    public String getVideos(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            @PathVariable Long id, Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "playbackDate"));
        model.addAttribute("server", serverService.getServer(id));
        Page<ServerVideoHistoryDTO> historyPage = serverService.listAllHistory(pageRequest, id);
        model.addAttribute("histories", historyPage);
        return "servers/viewServer";
    }


//    @GetMapping("/add")
//    public String create() {
//        return "servers/addServer";
//    }
//
//    @PostMapping("/add")
//    public String create(@ModelAttribute("serverForm") ServerDTO serverDTO,
//                         @RequestParam MultipartFile file) {
//        serverService.create(serverDTO);
//        return "redirect:/servers";
//    }
//
//    @GetMapping("/update/{id}")
//    public String update(@PathVariable Long id,
//                         Model model) {
//        model.addAttribute("server", serverService.getOne(id));
//        return "servers/updateServer";
//    }
//
//    @PostMapping("/update")
//    public String update(@ModelAttribute("serverForm") ServerDTO serverDTO,
//                         @RequestParam MultipartFile file) {
//        serverService.update(serverDTO);
//        return "redirect:/servers";
//    }

    @PostMapping("/search")
    public String searchServers(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "size", defaultValue = "5") int pageSize,
                                @ModelAttribute("serverSearchForm") ServerSearchDTO serverSearchDTO,
                                Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("servers", serverService.findServers(serverSearchDTO, pageRequest));
        return "servers/viewAllServers";
    }



}
