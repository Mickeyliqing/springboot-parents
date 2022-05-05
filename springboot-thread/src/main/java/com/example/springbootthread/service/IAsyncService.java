package com.example.springbootthread.service;
import com.example.springbootthread.model.RegionData2021;

import java.util.List;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
public interface IAsyncService {

    void saveRegionData(List<RegionData2021> list) throws Exception;
}
