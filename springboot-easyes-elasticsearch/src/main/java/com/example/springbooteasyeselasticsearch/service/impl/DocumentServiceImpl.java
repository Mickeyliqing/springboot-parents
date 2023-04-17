package com.example.springbooteasyeselasticsearch.service.impl;
import com.example.springbooteasyeselasticsearch.dao.DocumentMapper;
import com.example.springbooteasyeselasticsearch.model.Document;
import com.example.springbooteasyeselasticsearch.service.IDocumentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 框架把基础的方法放在了 Mapper 层，从而取消了 Service 层
 * 如果调用基础的方法不需要对业务做复杂的处理，可以直接在 Controller 层直接使用 Mapper 层的方法
 * 如果需要对业务进行复杂的处理，可以在封装一层 Service 层
 */
@Service
public class DocumentServiceImpl implements IDocumentService {

    @Resource
    private DocumentMapper documentMapper;

    // 新增
    @Override
    public Integer insert(Document document) {
        return documentMapper.insert(document);
    }

}
