package org.test.crudimage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "cat_image")
public class CatImage {

    @Id
    private String id;
    private String url;
    private Integer width;
    private Integer height;

}
