package com.example.mytube_forlecture.video.dao;

import com.example.mytube_forlecture.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
