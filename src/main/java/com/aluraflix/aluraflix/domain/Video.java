package com.aluraflix.aluraflix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE video SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
@Table(name = "video", indexes = @Index(name = "idx_video_title", columnList = "title", unique = true))
@Entity(name = "VideoEntity")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videoIdSeq")
    @SequenceGenerator(name = "videoIdSeq", sequenceName = "video_id_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be empty")
    private String title;

    @NotNull(message = "description cannot be null")
    @NotEmpty(message = "description cannot be empty")
    private String description;

    @NotNull(message = "url cannot be null")
    @NotEmpty(message = "url cannot be empty")
    private String url;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PreRemove
    protected void onDelete() {
        this.deletedAt = LocalDateTime.now();
    }
}
