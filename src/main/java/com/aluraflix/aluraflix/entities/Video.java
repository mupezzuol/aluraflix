package com.aluraflix.aluraflix.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "video")
@Entity(name = "VideoEntity")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videoIdSeq")
    @SequenceGenerator(name = "videoIdSeq", sequenceName = "video_seq", allocationSize = 1)
    @EqualsAndHashCode.Include()
    private Long id;

    private String title;
    private String description;
    private String url;

}
