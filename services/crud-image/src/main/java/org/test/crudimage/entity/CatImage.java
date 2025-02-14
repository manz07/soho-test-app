package org.test.crudimage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class CatImage {

    @Id
    private Integer id;
    private String url;
    private Float width;
    private Float height;

}
