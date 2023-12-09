package com.zeroone.service;


import com.zeroone.datatransferobjects.GET.FailureInfoDto;
import com.zeroone.exceptions.FailureInfoException;
import com.zeroone.exceptions.FailureInfoNotSavedException;
import com.zeroone.model.FailureInfo;
import com.zeroone.repository.FailureInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FailureInfoService {

    private final FailureInfoRepository failureInfoRepository;

    public FailureInfo saveFailureInfo(FailureInfoDto failureInfoDto) throws FailureInfoNotSavedException {
        FailureInfo failureInfo = FailureInfo.builder()
                .failureTitle(failureInfoDto.getFailureTitle())
                .additionalInfo(failureInfoDto.getAdditionalInfo())
                .failureStart(failureInfoDto.getFailureStart())
                .failureEnd(failureInfoDto.getFailureEnd())
                .isActive(false)
                .build();
        try {
            return failureInfoRepository.save(failureInfo);
        } catch (Exception e) {
            throw new FailureInfoNotSavedException("Error when ticket saving: " + e.getMessage());
        }
    }

    public List<FailureInfo> getAllFailures() {

        try {
            return failureInfoRepository.findAllByOrderByIdDesc();
        } catch (Exception e) {
            throw new FailureInfoException("Failed to retrieve failure information", e);
        }
    }

    public List<FailureInfo> getAllActiveFailures() {

        try {
            return failureInfoRepository.findAllActiveOrderByIdDesc();
        } catch (Exception e) {
            throw new FailureInfoException("Failed to retrieve failure information", e);
        }
    }

    public void setFailureStatus(Long failureId) {
        FailureInfo failureInfo = failureInfoRepository.findFailureInfoById(failureId);

        failureInfo.setActive(!failureInfo.isActive());

        failureInfoRepository.save(failureInfo);
    }
}
