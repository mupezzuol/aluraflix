package com.aluraflix.aluraflix.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE category SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
@Table(name = "category", indexes = @Index(name = "idx_category_title", columnList = "title", unique = true))
@Entity(name = "CategoryEntity")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoryIdSeq")
    @SequenceGenerator(name = "categoryIdSeq", sequenceName = "category_id_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be empty")
    private String title;

    @NotNull(message = "colour cannot be null")
    @NotEmpty(message = "colour cannot be empty")
    private String colour;

    @OneToMany(mappedBy = "category", targetEntity = Video.class, cascade = CascadeType.MERGE)
    private List<Video> videos;

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
