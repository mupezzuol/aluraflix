package com.aluraflix.aluraflix.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "video", indexes = @Index( name = "idx_video_title", columnList = "title", unique = true ))
@Entity(name = "VideoEntity")
public class Video {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "videoIdSeq")
    @SequenceGenerator( name = "videoIdSeq", sequenceName = "video_id_seq", allocationSize = 1)
    private Long id;
    private String title; // TODO: use javax.validation to put NotEmpty etc...
    private String description;
    private String url;
}
