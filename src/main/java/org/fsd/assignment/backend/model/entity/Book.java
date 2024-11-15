package org.fsd.assignment.backend.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private Long id;
    @Column("title")
    private String title;
    @Column("author")
    private String author;
    @Column("genre")
    private String genre;
    @Column("condition")
    private String condition;
    @Column("available")
    private boolean available;
    @Column("owner")
    private User owner;

}
