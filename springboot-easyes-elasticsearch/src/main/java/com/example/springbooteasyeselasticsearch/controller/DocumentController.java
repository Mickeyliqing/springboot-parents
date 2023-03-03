package com.example.springbooteasyeselasticsearch.controller;

import cn.easyes.core.biz.EsPageInfo;
import cn.easyes.core.biz.SAPageInfo;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import cn.easyes.core.toolkit.EsWrappers;
import com.example.springbooteasyeselasticsearch.dao.DocumentMapper;
import com.example.springbooteasyeselasticsearch.model.Document;
import com.example.springbooteasyeselasticsearch.service.IDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/document")
public class DocumentController {

    /**
     * 封装 Service 层方法
     */
    @Resource
    private IDocumentService documentService;

    /**
     * 框架把基础的方法放在了 Mapper 层，从而取消了 Service 层
     * 如果调用基础的方法不需要对业务做复杂的处理，可以直接在 Controller 层直接使用 Mapper 层的方法
     * 如果需要对业务进行复杂的处理，可以在封装一层 Service 层
     */
    @Resource
    private DocumentMapper documentMapper;

    /**
     * 调用 Service 层方法
     * @param document
     * @return
     */
    @PostMapping("/insert")
    public Integer insert(@RequestBody Document document) {
        return documentService.insert(document);
    }

    /**
     * 直接调用 Mapper 层方法
     * @param document
     * @return
     */
    @PostMapping("/insertDocument")
    public Integer insertDocument(@RequestBody Document document) {
        return documentMapper.insert(document);
    }

    /**
     * 查询 ES 中所有的数据
     * @return
     */
    @PostMapping("/list")
    public List<Document> getListDocument() {
        LambdaEsQueryWrapper lambdaEsQueryWrapper = new LambdaEsQueryWrapper();
        lambdaEsQueryWrapper.matchAllQuery();
        return documentMapper.selectList(lambdaEsQueryWrapper);
    }

    /**
     * 浅分页，适合数据量在 1W 以内的数据分页
     * @return
     */
    @PostMapping("/page")
    public EsPageInfo<Document> getPageDocument() {
        LambdaEsQueryWrapper lambdaEsQueryWrapper = new LambdaEsQueryWrapper();
        lambdaEsQueryWrapper.matchAllQuery();
        return documentMapper.pageQuery(lambdaEsQueryWrapper, 1,10);
    }

    /**
     * 滚动查询
     * @return
     */
    @PostMapping("/sa_page")
    public SAPageInfo<Document> getSAPageDocument() {
        LambdaEsQueryWrapper<Document> lambdaEsQueryWrapper = EsWrappers.lambdaQuery(Document.class);
        lambdaEsQueryWrapper.size(10);
        lambdaEsQueryWrapper.orderByDesc(Document::getId);
        // 第一页的数据
        SAPageInfo<Document> saPageInfo = documentMapper.searchAfterPage(lambdaEsQueryWrapper, null, 5);
        System.out.println("saPageInfo = " + saPageInfo.getList() + "; saPageInfo.getSearchAfter() = " + saPageInfo.getSearchAfter() + "; saPageInfo.getNextSearchAfter() = " + saPageInfo.getNextSearchAfter());

        // 下一页的数据
        SAPageInfo<Document> searchAfterPage = documentMapper.searchAfterPage(lambdaEsQueryWrapper, saPageInfo.getNextSearchAfter(), 5);
        System.out.println("searchAfterPage = " + searchAfterPage.getList() + "; searchAfterPage.getSearchAfter() = " + searchAfterPage.getSearchAfter() + "; searchAfterPage.getNextSearchAfter() = " + searchAfterPage.getNextSearchAfter());
        return searchAfterPage;
    }
}
