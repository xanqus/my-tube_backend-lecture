package com.example.mytube_forlecture.video.domain;

import com.example.mytube_forlecture.comment.domain.Comment;
import com.example.mytube_forlecture.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
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

    @Column(columnDefinition = "tinyint(1) default(0)")
    private Boolean isPublic;

    @Column(columnDefinition = "integer default 0")
    private Integer views;

    @Column(columnDefinition = "integer default 0")
    private Integer likeCount;

    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private User user;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regDate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "video", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

}
