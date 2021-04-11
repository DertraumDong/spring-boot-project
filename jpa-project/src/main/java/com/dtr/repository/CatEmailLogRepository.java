package com.dtr.repository;

import com.dtr.bean.CatEmailLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/4/11.
 *
 * @author LiuDong
 */
@Component
public interface CatEmailLogRepository extends JpaRepository<CatEmailLog, Integer> {
    CatEmailLog getById(Integer id);
}
