package com.zx.system.service;

import com.zx.system.model.SysDepartment;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */
public interface DepartmentService {
    List<SysDepartment> getSubsetsBranchByCode(String code);

    List<SysDepartment> getSubDepartments(List<SysDepartment> all, String deptcode);
    List<SysDepartment> getSubDepartments(String deptcode);

    int update(SysDepartment sysDepartment);

    SysDepartment selectById(Integer id);

    SysDepartment selectByCode(String code);
}
