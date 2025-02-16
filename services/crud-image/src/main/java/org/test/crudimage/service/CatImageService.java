package org.test.crudimage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.crudimage.dto.CatImageRequest;
import org.test.crudimage.dto.CatImageResponse;
import org.test.crudimage.entity.CatImage;
import org.test.crudimage.exception.CatImageNotFoundException;
import org.test.crudimage.repository.CatImageRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatImageService {

    private final CatImageRepository catImageRepository;
    private final CatImageMapper catImageMapper;

    public String createCatImage(CatImageRequest catImageRequest) {
        var catImage = catImageMapper.toCatImage(catImageRequest);
        return catImageRepository.save(catImage).getId();
    }

    public List<CatImageResponse> findAll() {
        return catImageRepository.findAll()
                .stream()
                .map(catImageMapper::fromCatImage)
                .collect(Collectors.toList());
    }

    public CatImageResponse findById(String catImageId) {
        return catImageRepository.findById(catImageId)
                .map(catImageMapper::fromCatImage)
                .orElseThrow(() -> new CatImageNotFoundException(format("Tidak ditemukan data kucing dengan ID: " + catImageId)));
    }
    
    public void updateCatImage(String id, CatImageRequest catImageRequest) {
        if (catImageRequest.id() == null || catImageRequest.id().isBlank()) {
            throw new IllegalArgumentException("ID gambar kucing tidak boleh kosong!");
        }
        var catImage = catImageRepository.findById(catImageRequest.id())
                .orElseThrow(() -> new CatImageNotFoundException(
                format("Tidak dapat ubah data::Data gambar kucing dengan ID: %s tidak ditemukan", id)));
        mergeCatImage(catImage, catImageRequest);
        catImageRepository.save(catImage);
    }

    private void mergeCatImage(CatImage catImage, CatImageRequest catImageRequest) {
        if (StringUtils.isNotBlank(catImageRequest.id())) {
            catImage.setId(catImageRequest.id());
        }
        if (StringUtils.isNotBlank(catImageRequest.url())) {
            catImage.setUrl(catImageRequest.url());
        }
        if (StringUtils.isNotBlank(String.valueOf(catImageRequest.width()))) {
            catImage.setWidth(catImageRequest.width());
        }
        if (StringUtils.isNotBlank(String.valueOf(catImageRequest.height()))) {
            catImage.setHeight(catImageRequest.height());
        }
    }

    public List<CatImage> saveAllByPas(List<CatImage> kucingList) {
        return catImageRepository.saveAll(kucingList);
    }

    @Transactional
    public List<CatImage> saveAll(List<CatImage> kucingList) {
        if (kucingList == null || kucingList.isEmpty()) {
            log.warn("Gagal menyimpan: daftar kucing kosong atau null.");
            throw new IllegalArgumentException("Daftar kucing tidak boleh kosong.");
        }

        try {
            List<CatImage> savedCats = catImageRepository.saveAll(kucingList);
            log.info("Berhasil menyimpan {} gambar kucing.", savedCats.size());
            return savedCats;
        } catch (Exception e) {
            log.error("Gagal menyimpan gambar kucing: {}", e.getMessage(), e);
            throw new RuntimeException("Terjadi kesalahan saat menyimpan gambar kucing.", e);
        }
    }

    public void deleteCatImage(String catImageId) {
        catImageRepository.deleteById(catImageId);
    }
}
