package com.lab.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lab.entity.Emp;
import com.lab.mapper.EmpMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

public class MyBatisPlusTest {
    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisUtil.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

        String name = "张";
        String gender = null;
        java.math.BigDecimal minSalary = new java.math.BigDecimal("5000");

        //①普通QueryWrapper条件构造器
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "emp_name", name)
                .eq(StringUtils.isNotBlank(gender), "gender", gender)
                .eq("status", 1)
                .ge(minSalary != null, "salary", minSalary)
                .orderByDesc("salary");
        List<Emp> list = empMapper.selectList(wrapper);
        System.out.println("====QueryWrapper查询结果====");
        for(Emp e : list){
            System.out.println(e);
        }

        //②LambdaQueryWrapper（防止硬编码写错数据库字段名，推荐写法）
        System.out.println("\n====LambdaQueryWrapper查询结果====");
        LambdaQueryWrapper<Emp> lw = new LambdaQueryWrapper<>();
        lw.like(Emp::getEmpName, name)
                .eq(Emp::getStatus,1)
                .orderByDesc(Emp::getSalary);
        List<Emp> lambdaList = empMapper.selectList(lw);
        lambdaList.forEach(System.out::println);

        //③MP分页查询：第1页，每页3条
        System.out.println("\n====MyBatis‑Plus分页查询====");
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Emp> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(1,3);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Emp> resultPage = empMapper.selectPage(page, lw);
        System.out.println("总记录数 = " + resultPage.getTotal());
        System.out.println("总页数 = " + resultPage.getPages());
        resultPage.getRecords().forEach(System.out::println);


        sqlSession.close();
    }
}
