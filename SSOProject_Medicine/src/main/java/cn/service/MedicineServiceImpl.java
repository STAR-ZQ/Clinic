package cn.service;

import cn.controller.UserController;
import cn.entity.Medicine;
import cn.utils.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MedicineServiceImpl {
    @Autowired
    private MedicineMapper medicineMapper;

    public Page<Medicine> all(String toll_type, int status, String createTime, String info, Page<Medicine> page) {
        int totalCount = medicineMapper.totalCount(toll_type, status, createTime, info);
        page.setTotalCount(totalCount);

        if (totalCount > 0) {
            if (page.getPageNo() < 1) {
                page.setPageNo(1);
            }
            if (page.getPageNo() > page.getTotalPageCount()) {
                page.setPageNo(page.getTotalPageCount());
            }
        }

        page.setList(medicineMapper.all(toll_type, status, createTime, info, page.getPageNo(), page.getPageSize()));

        return page;
    }
}
