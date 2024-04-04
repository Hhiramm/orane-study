package com.mr.clock.pojo;

import java.util.Objects;

/**
 * @author orane
 * @create 2024/3/31 20:25
 * @Description 员工类，与MySQL中的t_emp表相对应
 **/

public class Employee {
    private Integer id;             //员工编号
    private String name;            //员工名称
    private String code;            //员工特征码

    public Employee() {
    }

    public Employee(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
