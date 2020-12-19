package com.cloud.web.web;

import com.cloud.commons.constants.Constants;
import com.cloud.commons.dto.GoodsInfo;
import com.cloud.web.elasticsearchrepository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author: LiuHeYong
 * @create: 2019-06-20
 * @description:
 **/
@RestController("goodsController")
//@Order(3)
public class GoodsController extends DefaultController {

    @Autowired
    GoodsRepository goodsRepository;

    public GoodsController() {
        System.out.println("GoodsController");
    }

    @GetMapping(Constants.CLOUD + "save")
    public String save() {
        GoodsInfo goodsInfo = new GoodsInfo(System.currentTimeMillis(),
                "商品" + System.currentTimeMillis(), "这是一个测试商品");
        goodsRepository.save(goodsInfo);
        return "success";
    }

    @GetMapping(Constants.CLOUD + "delete")
    public String delete(long id) {
        GoodsInfo goodsInfo = new GoodsInfo(id);
        goodsRepository.delete(goodsInfo);
        return "success";
    }

    @GetMapping(Constants.CLOUD + "update")
    public String update(long id, String name, String description) {
        GoodsInfo goodsInfo = new GoodsInfo(id, name, description);
        goodsRepository.save(goodsInfo);
        return "success";
    }

    @GetMapping(Constants.CLOUD + "getOne")
    public GoodsInfo getOne(long id) {
        Optional<GoodsInfo> goodsInfo = goodsRepository.findById(id);
        return goodsInfo.get();
    }

    /*//每页数量
    private Integer PAGESIZE = 10;

    @GetMapping(Constants.CLOUD + "getGoodsList")
    public List<GoodsInfo> getList(Integer pageNumber, String query) {
        if (pageNumber == null) {
            pageNumber = 0;
        }
        //es搜索默认第一页页码是0
        SearchQuery searchQuery = getEntitySearchQuery(pageNumber, PAGESIZE, query);
        Page<GoodsInfo> goodsPage = goodsRepository.search(searchQuery);
        return goodsPage.getContent();
    }

    private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("name", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(100))
                .add(QueryBuilders.matchPhraseQuery("description", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(100))
                //设置权重分 求和模式
                .scoreMode("sum")
                //设置权重分最低分
                .setMinScore(10);

        // 设置分页
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }*/

}
