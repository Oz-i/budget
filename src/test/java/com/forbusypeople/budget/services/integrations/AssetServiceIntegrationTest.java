package com.forbusypeople.budget.services.integrations;

import com.forbusypeople.budget.builders.AssetDtoBuilder;
import com.forbusypeople.budget.builders.AssetEntityBuilder;
import com.forbusypeople.budget.enums.AssetCategory;
import com.forbusypeople.budget.repositories.AssetsRepository;
import com.forbusypeople.budget.repositories.entities.AssetEntity;
import com.forbusypeople.budget.services.AssetsService;
import com.forbusypeople.budget.services.dtos.AssetDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Transactional
@WithMockUser
public class AssetServiceIntegrationTest {

    @Autowired
    private AssetsRepository repository;
    @Autowired
    private AssetsService service;


    @Test
    void shouldReturnListWithThreeElements() {
        // given
        initDataBase();

        // when
        var allAssetsInDB = service.getAllAssets();

        // then
        assertThat(allAssetsInDB).hasSize(3);

    }

    @Test
    void shouldAddAssetToDB() {
        // given
        AssetDto dto = new AssetDtoBuilder()
                .withAmount(new BigDecimal(11))
                .withIncomeDate(Instant.now())
                .withCategory(AssetCategory.BONUS)
                .build();

        // when
        service.setAsset(dto);

        // then
        var allAssetInDB = repository.findAll();
        assertThat(allAssetInDB).hasSize(1);
        var entity = allAssetInDB.get(0);
        assertThat(entity.getCategory()).isEqualTo(dto.getCategory());
        assertThat(entity.getAmount()).isEqualTo(dto.getAmount());
        assertThat(entity.getIncomeDate()).isEqualTo(dto.getIncomeDate());

    }

    @Test
    void shouldReturnListOnlyWithOneCategory() {
        // given
        initDataBase();
        var category = AssetCategory.OTHER;

        // when
        var allAssetsWithOneCategory = service.getAssetsByCategory(category);

        // then
        assertThat(allAssetsWithOneCategory).hasSize(1);
        var entity = allAssetsWithOneCategory.get(0);
        assertThat(entity.getCategory()).isEqualTo(category);
    }

    private void initDataBase() {
        AssetEntity entity1 = new AssetEntityBuilder()
                .withAmount(new BigDecimal(1))
                .withIncomeDate(Instant.now())
                .withCategory(AssetCategory.OTHER)
                .build();
        AssetEntity entity2 = new AssetEntityBuilder()
                .withAmount(new BigDecimal(3))
                .withIncomeDate(Instant.now())
                .withCategory(AssetCategory.SALARY)
                .build();
        AssetEntity entity3 = new AssetEntityBuilder()
                .withAmount(new BigDecimal(5))
                .withIncomeDate(Instant.now())
                .withCategory(AssetCategory.RENT)
                .build();

        repository.saveAll(asList(entity1, entity2, entity3));
    }
}
