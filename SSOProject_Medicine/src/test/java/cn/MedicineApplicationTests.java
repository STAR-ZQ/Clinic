package cn;

import cn.entity.Medicine;
import cn.service.MedicineServiceImpl;
import cn.utils.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
//@Api
class MedicineApplicationTests {
    @Autowired
    private MedicineServiceImpl medicineServiceImpl;
//    @ApiOperation(value = "查询药品", notes = "根据条件查询药品信息")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(value = "处方分类", name = "tollType"),
//                    @ApiImplicitParam(value = "药品状态", name = "status"),
    //                    @ApiImplicitParam(value = "创建时间", name = "createTime"),
//                    @ApiImplicitParam(value = "药品名称/编码/生产厂家", name = "info")
//            }
//    )
    @Test
    void findByMedicine() {
        System.out.println("sd");
//        Page<Medicine> page = new Page<>();
//        page.setPageNo(1);
//        System.out.println(medicineServiceImpl.all("全部", -1,
//                "2019-02-09", "10", page));
    }

}
