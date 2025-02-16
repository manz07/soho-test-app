package org.test.crudimage.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.crudimage.apiresponse.CustomApiResponse;
import org.test.crudimage.dto.CatImageRequest;
import org.test.crudimage.dto.CatImageResponse;
import org.test.crudimage.entity.CatImage;
import org.test.crudimage.service.CatAPIService;
import org.test.crudimage.service.CatImageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cat")
public class CatImageController {

    private final CatImageService catImageService;
    private final CatAPIService catAPIService;

    @PostMapping("/import-and-save")
    public List<CatImage> importKucingFromAPIAndSave() {
        List<CatImage> catList = catAPIService.fetchKucingFromAPI();
        return catImageService.saveAllByPas(catList);
    }

    @GetMapping("/import")
    public List<CatImage> importKucingFromAPI() {
        return catAPIService.fetchKucingFromAPI();
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse> createCatImage(
            @RequestBody @Valid CatImageRequest catImageRequest
    ) {
        String catImageId = catImageService.createCatImage(catImageRequest);
        CustomApiResponse customApiResponse = new CustomApiResponse("success", "Data gambar kucing berhasil disimpan dengan ID: " + catImageId);
        return new ResponseEntity<>(customApiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/save")
    public ResponseEntity<CustomApiResponse> saveSelectedCats(@RequestBody @Valid List<CatImageRequest> selectedCatRequests) {
        List<CatImage> selectedCats = selectedCatRequests.stream().map(request ->
                new CatImage(request.id(), request.url(), request.width(), request.height())
        ).toList();

        List<CatImage> savedCats = catImageService.saveAll(selectedCats);
        String message = "Data gambar kucing berhasil disimpan. Total: " + savedCats.size() + " gambar.";

        CustomApiResponse customApiResponse = new CustomApiResponse("success", message);
        return new ResponseEntity<>(customApiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CatImageResponse>> findAll() {
        return ResponseEntity.ok(catImageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatImageResponse> findById(
            @PathVariable("id") String catImageId
    ) {
        return ResponseEntity.ok(catImageService.findById(catImageId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse> updateCatImage(
            @PathVariable String id,
            @RequestBody @Valid CatImageRequest catImageRequest
    ) {
        System.out.println("=== REQUEST MASUK DARI FRONTEND ===");
        System.out.println("ID: " + id);
        System.out.println("Payload: " + catImageRequest);

        catImageService.updateCatImage(id, catImageRequest);
        CustomApiResponse customApiResponse = new CustomApiResponse("success", "Data gambar kucing berhasil diubah");
        return new ResponseEntity<>(customApiResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse> deleteCatImage(
            @PathVariable("id") String catImageId
    ) {
        catImageService.deleteCatImage(catImageId);
        CustomApiResponse customApiResponse = new CustomApiResponse("success", "Data gambar kucing berhasil dihapus");
        return new ResponseEntity<>(customApiResponse, HttpStatus.OK);
    }
}
