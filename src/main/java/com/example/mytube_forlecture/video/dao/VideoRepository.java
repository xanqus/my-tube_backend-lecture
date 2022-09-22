package com.example.mytube_forlecture.video.dao;

import com.example.mytube_forlecture.user.domain.User;
import com.example.mytube_forlecture.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByUser(User user);
}
