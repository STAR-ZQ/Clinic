package cn.service;

import cn.entity.Medicine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface MedicineMapper {

//    @Select({"<script>" +
//            "select * from medicine where createTime like #{createTime} " +
//            "<when test='toll_type != " + "全部" + "'>toll_type=#{toll_type} </when>" +
//            "<when test='status != -1'>status=#{status} </when>" +
//            "<when test='info != null'>med_name LIKE '%#{info}%' OR CODE LIKE '%#{info}%' OR factory LIKE '%#{info}%' </when>" +
//            "</script>"})
    List<Medicine> all(@Param("toll_type") String toll_type,
                       @Param("status") int status,
                       @Param("createTime") String createTime,
                       @Param("info") String info,
                       @Param("from")Integer pageNo,
                       @Param("pageSize")Integer pageSize);

    int totalCount(@Param("toll_type") String toll_type,
                   @Param("status") int status,
                   @Param("createTime") String createTime,
                   @Param("info") String info);
}
