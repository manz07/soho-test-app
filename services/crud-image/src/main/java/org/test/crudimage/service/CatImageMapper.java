package org.test.crudimage.service;

import org.springframework.stereotype.Service;
import org.test.crudimage.dto.CatImageRequest;
import org.test.crudimage.dto.CatImageResponse;
import org.test.crudimage.entity.CatImage;

@Service
public class CatImageMapper {


    public CatImage toCatImage(CatImageRequest catImageRequest) {
        if (catImageRequest == null) {
            return null;
        }
        return CatImage.builder()
                .id(catImageRequest.id())
                .url(catImageRequest.url())
                .width(catImageRequest.width())
                .height(catImageRequest.height())
                .build();
    }

    public CatImageResponse fromCatImage(CatImage catImage) {
        return new CatImageResponse(
                catImage.getId(),
                catImage.getUrl(),
                catImage.getWidth(),
                catImage.getHeight()
        );
    }
}
