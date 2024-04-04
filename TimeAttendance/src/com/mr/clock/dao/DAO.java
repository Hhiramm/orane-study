package com.mr.clock.dao;

import com.mr.clock.pojo.Employee;
import com.mr.clock.pojo.User;
import com.mr.clock.pojo.WorkTime;

import java.util.Date;
import java.util.Set;

public interface DAO {
    /**
     * @create 2024/3/31 20:35
     * @Description 获取所有员工
     * @return 所有员工对象集合
     **/
    public Set<Employee> getALLEmp();
    /**
     * @create 2024/3/31 20:37
     * @Description 根据员工编号获取员工对象
     * @Param id 员工编号
     * @return 具体员工对象
     **/
    public Employee getEmp(int id);
    /**
     * @create 2024/4/1 17:36
     * @Description 根据特征码获取员工对象
     * @Param code 特征码
     * @return 具体员工对象
     **/
    public Employee getEmp(String code);
    /**
     * @create 2024/4/1 17:38
     * @Description 添加新员工
     * @Param e 新员工对象
     * @return void
     **/
    public void addEmp(Employee e);
    /**
     * @create 2024/4/1 17:38
     * @Description 删除指定员工
     * @Param id 员工编号
     * @return void
     **/
    public void deleteEmp(Integer id);
    /**
     * @create 2024/4/1 22:01
     * @Description 获取作息时间
     * @return 作息时间对象
     **/
    public WorkTime getWorkTime();
    /**
     * @create 2024/4/1 22:05
     * @Description 更新作息时间
     * @Param time
     * @return void
     **/
    public void updateWorkTime(WorkTime time);
    /**
     * @create 2024/4/1 22:27
     * @Description 指定员工添加打卡记录
     * @Param empID 员工编号
     * @Param now 打卡日期
     * @return void
     **/
    public void addCLockInRecord(int empID, Date now);
    /**
     * @create 2024/4/1 22:46
     * @Description 删除指定员工所有打卡记录
     * @Param empID 员工编号
     * @return void
     **/
    public void deleteClockInRecord(int empID);
    /**
     * @create 2024/4/1 22:48
     * @Description 获取所有员工的打卡记录
     * @return 左索引记录员工编号，右索引记录打卡日期
     **/
    public String[][] getAllClockInRecord();
    /**
     * @create 2024/4/1 23:04
     * @Description 验证管理员登陆
     * @Param user 管理员账号
     * @return boolean 如果账号密码正确，则返回true，否则返回false
     **/
    public boolean userLogin(User user);
}
