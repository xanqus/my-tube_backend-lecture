package com.example.mytube_forlecture.video.service;

import com.example.mytube_forlecture.user.dao.UserRepository;
import com.example.mytube_forlecture.user.domain.User;
import com.example.mytube_forlecture.video.dao.VideoRepository;
import com.example.mytube_forlecture.video.domain.Video;
import com.example.mytube_forlecture.video.dto.VideoDto;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    public void uploadFiles(List<MultipartFile> files, String root, Integer userId) throws IOException{
        User user = userRepository.findById(Long.valueOf(userId)).get();

        List<Map<String, String>> fileList = new ArrayList<>();

        for(int i = 0; i < files.size(); i++) {
            String originalFilename = files.get(i).getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            String changedFilename = UUID.randomUUID().toString() + ext;
            String thumbnailFileName = changedFilename.substring(0, changedFilename.lastIndexOf(".")) + ".png";

            Map<String, String> map = new HashMap<>();
            map.put("originalFile", originalFilename);
            map.put("changeFile", changedFilename);
            map.put("thumbnail", thumbnailFileName);
            fileList.add(map);

            Video video = Video.builder()
                    .user(user)
                    .videoUrl("http://localhost:8123/uploadFiles/" + changedFilename)
                    .title(title)
                    .filename(originalFilename)
                    .thumbnailUrl("http://localhost:8123/uploadFiles/" + thumbnailFileName)
                    .build();

            videoRepository.save(video);

        }

        try {
            for(int i = 0; i < files.size(); i++) {
                String filepath = root + "\\" + fileList.get(i).get("changeFile");
                String imageFilepath = root + "\\" + fileList.get(i).get("thumbnail");

                File uploadFile = new File(filepath);
                files.get(i).transferTo(uploadFile);
                Picture picture = FrameGrab.getFrameFromFile(uploadFile, 0);

                BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
                ImageIO.write(bufferedImage, "png", new File(imageFilepath));

                
            }
            System.out.println("파일 업로드 성공");
        }catch(IllegalStateException | IOException e) {
            System.out.println("파일 업로드 실패");
            for(int i = 0; i < files.size();i++) {
                new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
            }
            e.printStackTrace();
        } catch (JCodecException e) {
            throw new RuntimeException(e);
        }

    }

    public List<VideoDto> getVideos(Integer userId) {
        User user = userRepository.findById(Long.valueOf(userId)).get();
        List<Video> videos =  videoRepository.findByUser(user);

        List<VideoDto> collect = videos.stream()
                .map(video -> {
                    VideoDto item = new VideoDto(video);
                    return item;
                })
                .collect(Collectors.toList());
        return collect;
    }
}
