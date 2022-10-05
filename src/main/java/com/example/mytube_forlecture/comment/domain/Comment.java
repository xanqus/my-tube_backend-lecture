package com.example.mytube_forlecture.comment.domain;

import com.example.mytube_forlecture.video.domain.Video;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private Long likes;

    @ManyToOne
    private Video video;

}
