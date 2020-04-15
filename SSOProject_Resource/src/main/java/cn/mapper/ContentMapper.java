package cn.mapper;

import cn.pojo.TbContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContentMapper {
    @Select("select * from tb_user")
    public List<TbContent> queryAllContent(Long categoryId);

}
