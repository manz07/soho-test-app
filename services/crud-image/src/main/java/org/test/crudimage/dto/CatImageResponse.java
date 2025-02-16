package org.test.crudimage.dto;

public record CatImageResponse(
        String id,
        String url,
        Integer width,
        Integer height
) {
}
