package cn.controller;

import cn.entity.Medicine;
import cn.service.MedicineMapper;
import cn.service.MedicineServiceImpl;
import cn.utils.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "药品数据接口")
public class MedicineController {
    @Autowired
    private MedicineServiceImpl medicineServiceImpl;

    @ApiOperation(value = "查询药品", notes = "根据条件查询药品信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(value = "处方分类", name = "tollType"),
                    @ApiImplicitParam(value = "药品状态", name = "status"),
                    @ApiImplicitParam(value = "创建时间", name = "createTime"),
                    @ApiImplicitParam(value = "药品名称/编码/生产厂家", name = "info")
            }
    )
    @GetMapping("/findByMedicine")
    public Page<Medicine> findMedicineInfo(/*String tollType, int status, String createTime, String info, Integer pageNo*/) throws ParseException {
//        List<Medicine> list = medicineMapper.all("全部", -1,
//                "2019-02-09", "兰");
        Page<Medicine> page = new Page<>();
        page.setPageNo(1);
        return medicineServiceImpl.all("全部", -1,
                "2019-02-09", "10", page);
    }
}
