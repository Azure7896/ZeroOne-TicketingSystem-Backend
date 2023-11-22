package com.zeroone.repository;

import com.zeroone.model.FailureInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FailureInfoRepository extends JpaRepository<FailureInfo, Long> {
    List<FailureInfo> findAllByOrderByIdDesc();

    @Query("SELECT f FROM FailureInfo f WHERE f.isActive = true ORDER BY f.id DESC")
    List<FailureInfo> findAllActiveOrderByIdDesc();

    FailureInfo findFailureInfoById(Long id);

}
