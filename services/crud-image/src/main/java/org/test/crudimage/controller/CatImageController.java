package org.test.crudimage.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.crudimage.apiresponse.CustomApiResponse;
import org.test.crudimage.dto.CatImageRequest;
import org.test.crudimage.service.CatImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cat")
public class CatImageController {

    private final CatImageService catImageService;

    @PostMapping
    public ResponseEntity<CustomApiResponse> createCatImage(
            @RequestBody @Valid CatImageRequest catImageRequest
    ) {
        Integer catImageId = catImageService.createCatImage(catImageRequest);
        CustomApiResponse customApiResponse = new CustomApiResponse("success", "Gambar kucing berhasil disimpan dengan ID: " + catImageId);
        return new ResponseEntity<>(customApiResponse, HttpStatus.CREATED);
    }
}
