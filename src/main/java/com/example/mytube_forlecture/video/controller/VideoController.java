package com.example.mytube_forlecture.video.controller;

import com.example.mytube_forlecture.fileSystem.service.FileSystemService;
import com.example.mytube_forlecture.video.domain.Video;
import com.example.mytube_forlecture.video.dto.VideoDto;
import com.example.mytube_forlecture.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/video")
@RequiredArgsConstructor
public class VideoController {
    private final FileSystemService fileSystemService;
    private final VideoService videoService;


    @GetMapping("/{videoId}")
    public VideoDto getVideo(@PathVariable("videoId") Integer videoId) {
        System.out.println("videoId: " + videoId);

        return videoService.getVideo(videoId);
    }

    @GetMapping("")
    public List<VideoDto> getVideos(@RequestParam("userId") Integer userId) {

        return videoService.getVideos(userId);
    }

    @PostMapping("")
    public List<VideoDto> uploadsVideos(@RequestParam("files")List<MultipartFile> files, @RequestParam("userId") Integer userId) throws IOException {

        System.out.println("파일 입력 들어옴");
        System.out.println("files: " + files);
        System.out.println("userId: " + userId);

        String root = "C:\\uploadFiles";

        fileSystemService.createFolder(root);

        videoService.uploadFiles(files, root, userId);
        return videoService.getVideos(userId);
    }

    @PatchMapping("/{videoId}")
    public void updateVideo(@PathVariable("videoId") int videoId, @RequestBody Video video) {

        this.videoService.updateVideo(videoId, video);
    }

}
