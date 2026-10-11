package com.lab.test;

import com.lab.entity.Emp;
import com.lab.mapper.EmpMapper;
import com.lab.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;





public class DynamicSqlTest {
    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisUtil.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
/**
        //构造查询条件：模糊查询姓名含有"工"，部门研发部
        Emp emp = new Emp();
        emp.setEmpName("工");
        emp.setDept("研发部");

        List<Emp> list = mapper.selectByCondition(emp);
        for(Emp e : list){
            System.out.println(e);
        }
 */

/*
        // --------测试3.2动态更新--------
        Emp emp = new Emp();
        emp.setEmpId(1);
        emp.setSalary(new BigDecimal("13000"));
        int rows = mapper.updateDynamic(emp);
        System.out.println("更新行数：" + rows);

        sqlSession.commit();//❗增删改必须提交事务！不写commit数据库不会变
*/

 /*

        //========3.3 批量插入测试========
        List<Emp> empList = new ArrayList<>();

        Emp e1=new Emp();
        e1.setEmpName("张三");
        e1.setGender("男");
        e1.setDept("技术部");
        e1.setPost("开发");
        e1.setSalary(new BigDecimal("8000"));
        e1.setStatus(1);

        Emp e2=new Emp();
        e2.setEmpName("李四");
        e2.setGender("女");
        e2.setDept("财务部");
        e2.setPost("会计");
        e2.setSalary(new BigDecimal("7200"));
        e2.setStatus(1);

        empList.add(e1);
        empList.add(e2);

        int count = mapper.insertBatch(empList);
        System.out.println("批量插入行数："+count);
        sqlSession.commit();
*/
 /*
        //========3.3 批量删除测试========
        List<Integer> idList=new ArrayList<>();
        idList.add(7);
        idList.add(8);
        int delCount=mapper.deleteByIds(idList);
        System.out.println("批量删除行数："+delCount);
        sqlSession.commit();
*/
        //========3.4 choose‑when‑otherwise测试========
        //测试①：传入部门参数，查询技术部员工
        List<Emp> list1 = mapper.selectByChoose("技术部");
        System.out.println("====传入技术部的查询结果====");
        for(Emp e : list1){
            System.out.println(e.getEmpName()+" "+e.getDept());
        }

        //测试②：传入null（部门为空，触发otherwise，查询在职员工）
        List<Emp> list2 = mapper.selectByChoose(null);
        System.out.println("====部门为空，查询全部在职员工====");
        for(Emp e : list2){
            System.out.println(e.getEmpName()+" "+e.getStatus());
        }
        //不需要修改数据库，不用commit！只是查询

        sqlSession.close();
    }
}


