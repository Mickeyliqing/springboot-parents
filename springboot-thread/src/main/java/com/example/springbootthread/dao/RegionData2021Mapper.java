package com.example.springbootthread.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootthread.model.RegionData2021;

import java.util.List;

/**
 * <p>
 * 全国区域编码数据表 Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2022-04-11
 */
public interface RegionData2021Mapper extends BaseMapper<RegionData2021> {

    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertBatch(List<RegionData2021> list);

}
