package com.example.mytube_forlecture.video.service;

import com.example.mytube_forlecture.user.dao.UserRepository;
import com.example.mytube_forlecture.user.domain.User;
import com.example.mytube_forlecture.video.dao.VideoRepository;
import com.example.mytube_forlecture.video.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

            Map<String, String> map = new HashMap<>();
            map.put("originalFile", originalFilename);
            map.put("changeFile", changedFilename);
            fileList.add(map);

            Video video = Video.builder()
                    .user(user)
                    .videoUrl("http://localhost:8123/uploadFiles/" + changedFilename)
                    .title(title)
                    .filename(originalFilename)
                    .build();

            videoRepository.save(video);

        }

        try {
            for(int i = 0; i < files.size(); i++) {
                String filepath = root + "\\" + fileList.get(i).get("changeFile");
                File uploadFile = new File(filepath);
                files.get(i).transferTo(uploadFile);
                
            }
            System.out.println("파일 업로드 성공");
        }catch(IllegalStateException | IOException e) {
            System.out.println("파일 업로드 실패");
            for(int i = 0; i < files.size();i++) {
                new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
            }
            e.printStackTrace();
        }

    }
}
