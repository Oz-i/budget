package com.forbusypeople.budget.filters;

import com.forbusypeople.budget.repositories.AssetsRepository;
import com.forbusypeople.budget.repositories.entities.AssetEntity;
import com.forbusypeople.budget.repositories.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class AssetsFilterRange extends FilterRangeAbstract {

    private final AssetsRepository assetsRepository;

    public AssetsFilterRange(AssetsRepository assetsRepository) {
        this.assetsRepository = assetsRepository;
    }

    @Override
    protected List<AssetEntity> getAllEntityBetweenDate(UserEntity user,
                                                        Instant fromDate,
                                                        Instant toDate) {
        return assetsRepository.findAllByBetweenDate(user, fromDate, toDate);
    }

    @Override
    protected String getFilterName() {
        return "AssetsFilter";
    }
}