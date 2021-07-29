package com.forbusypeople.budget.controllers;

import com.forbusypeople.budget.enums.AssetCategory;
import com.forbusypeople.budget.services.AssetsService;
import com.forbusypeople.budget.services.dtos.AssetDto;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assets")
public class AssetsController {

    private final AssetsService assetsService;

    public AssetsController(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @GetMapping
    public List<AssetDto> getAssets() {
        return assetsService.getAllAssets();
    }

    @PostMapping
    public void setAsset(@RequestBody List<AssetDto> dtos) {
        assetsService.setAsset(dtos);
    }

    @DeleteMapping
    public void deleteAsset(@RequestBody AssetDto dto) {
        assetsService.deleteAsset(dto);
    }

    @PutMapping
    public void updateAsset(@RequestBody AssetDto dto) {
        assetsService.updateAsset(dto);
    }

    @GetMapping("/find")
    public List<AssetDto> getAllAssetsByCategory(@PathParam("category") String category) {
        return assetsService.getAssetsByCategory(AssetCategory.valueOf(category.toUpperCase()));
    }

    @GetMapping("/filter")
    public List<AssetDto> getFilteredAssets(@RequestParam Map<String, String> filter) {
        return assetsService.getAssetsByFilter(filter);
    }

}
