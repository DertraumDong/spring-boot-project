package com.dtr.service;

import com.dtr.bean.CatEmailLog;
import com.dtr.bean.dto.CatEmailLogDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created on 2021/4/11.
 *
 * @author LiuDong
 */
public interface CatEmailLogService {

    CatEmailLog add(CatEmailLogDto catEmailLogDto);

    Page<CatEmailLog> findPage(CatEmailLogDto catEmailLogDto);

    CatEmailLog update(CatEmailLogDto catEmailLogDto) throws Exception;

    Integer deleteById(Integer id) throws Exception;
}
