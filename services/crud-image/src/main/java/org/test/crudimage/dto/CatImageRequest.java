package org.test.crudimage.dto;

import jakarta.validation.constraints.NotNull;

public record CatImageRequest(
        @NotNull(message = "ID tidak boleh null")
        String id,
        String url,
        Integer width,
        Integer height
) {
}
