package com.lab.mapper;

import com.lab.entity.Emp;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface EmpMapper extends BaseMapper<Emp> {

    List<Emp> selectAll();
    Emp selectById(Integer empId);
    int insert(Emp emp);
    int update(Emp emp);
    int deleteById(Integer empId);List<Emp> selectByCondition(Emp emp);
    int updateDynamic(Emp emp);
    int deleteByIds(List<Integer> idList);
    int insertBatch(List<Emp> empList);
    List<Emp> selectByChoose(String dept);


}
