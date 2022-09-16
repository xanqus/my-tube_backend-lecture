package com.example.mytube_forlecture.video.domain;

import com.example.mytube_forlecture.user.domain.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicInsert
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String videoUrl;

    @Column(length=255)
    private String thumbnailUrl;

    @Column(length=50)
    private String title;

    @Column(length = 255)
    private String filename;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "tinyint(1) default(1)")
    private Boolean isTemp;

    @Column(columnDefinition = "tinyint(1) default(1)")
    private Boolean isPublic;

    @Column(columnDefinition = "integer default 0")
    private Integer views;

    @Column(columnDefinition = "integer default 0")
    private Integer likeCount;

    @ManyToOne
    private User user;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regDate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

}
