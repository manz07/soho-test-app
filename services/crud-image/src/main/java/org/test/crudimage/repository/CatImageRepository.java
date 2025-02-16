package org.test.crudimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.crudimage.entity.CatImage;

public interface CatImageRepository extends JpaRepository<CatImage, String> {
}
