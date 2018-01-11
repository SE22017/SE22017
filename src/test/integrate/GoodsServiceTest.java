package test.integrate;

import com.cn.swm.model.goods.Goods;
import com.cn.swm.service.goods.GoodServiceI;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Assert;
import org.junit.Test;
import test.BaseTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


public class GoodsServiceTest extends BaseTest {
    @Resource
    GoodServiceI goodServiceI;

    @Test
    public void testUpdate() {

        Assert.assertEquals(1, goodServiceI.update(new Goods()));
    }

    @Test
    public void testGetGoodList(){
        List<Goods> goodsList = null;
        Goods goods1 = new Goods();
        Goods goods2 = new Goods();
        goods1.setSpname("g1");
        goods2.setSpname("g2");
        goodsList.add(goods1);
        goodsList.add(goods2);
        Assert.assertEquals(goodsList,goodServiceI.getGoodList(goods1));
    }

    @Test
    public void testAddMenu(){
        Assert.assertEquals(123, goodServiceI.update(new Goods()));
    }

    @Test
    public void testDel(){
        Assert.assertTrue(goodServiceI.del(new Goods().getSpname()));
    }

    @Test
    public void testGoodList(){
        List<Goods> goodsList = null;
        Goods goods1 = new Goods();
        Goods goods2 = new Goods();
        goods1.setSpname("g1");
        goods2.setSpname("g2");
        goodsList.add(goods1);
        goodsList.add(goods2);
        Map<String,Object> map = null;
        Assert.assertEquals(goodsList,goodServiceI.goodList(map));
    }

    @Test
    public void testGetGood(){
        Goods goods = new Goods();
        Assert.assertEquals(goods, goodServiceI.getGood(new Goods()));
    }
}
