package com.ytbot.website.controller.mvc;

import com.ytbot.website.dto.ServerDTO;
import com.ytbot.website.dto.VideoDTO;
import com.ytbot.website.dto.VideoSearchDTO;
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


import java.util.List;

@Controller
@RequestMapping("/videos")
@Hidden
@Slf4j
public class MVCVideoController {

    private final VideoService service;

    public MVCVideoController(VideoService service) {
        this.service = service;
    }

    @GetMapping("")
    public String getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int pageSize,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "numberOfPlays"));
        Page<VideoDTO> videoDTOList = service.getAllVideos(pageRequest);
        model.addAttribute("videos", videoDTOList);
        return "videos/viewAllVideos";
    }
    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        model.addAttribute("video", service.getVideo(id));
        return "videos/viewVideo";
    }

    @PostMapping("/search")
    public String searchVideos(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "5") int pageSize,
                               @ModelAttribute("videoSearchForm") VideoSearchDTO videoSearchDTO,
                               Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "number_of_plays"));
        Page<VideoDTO> videoDTOPage = service.findVideos(videoSearchDTO, pageRequest);
        model.addAttribute("videos", videoDTOPage);
        return "videos/viewAllVideos";
    }



}
