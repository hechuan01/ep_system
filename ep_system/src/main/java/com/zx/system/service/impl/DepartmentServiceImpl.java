package com.zx.system.service.impl;

import com.zx.common.utils.UUIDUtil;
import com.zx.system.dao.SysDepartmentDao;
import com.zx.system.model.SysDepartment;
import com.zx.system.service.DepartmentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private SysDepartmentDao sysDepartmentDao;

    @Override
    public List<SysDepartment> getSubsetsBranchByCode(String code) {
        return sysDepartmentDao.getSubsetsBranchByCode(code == null ? "" : code);
    }

    @Override
    public List<SysDepartment> getSubDepartments(List<SysDepartment> all, String deptcode) {
        List<SysDepartment> returnList = new ArrayList<>();
        return all.stream().filter(__ -> __.getDeptcode().startsWith(deptcode)
                && __.getDeptcode().length() == deptcode.length() + 3)
                .collect(Collectors.toList());
    }

    @Override
    public List<SysDepartment> getSubDepartments(String code) {
        return sysDepartmentDao.getSubDepartments(code);
    }

    @Override
    public int update(SysDepartment sysDepartment) {
        if (sysDepartment.getId() != null && !"".equals(sysDepartment.getId())) {
            return sysDepartmentDao.update(sysDepartment);
        } else {
        	sysDepartment.setId(UUIDUtil.getUUID());
            return sysDepartmentDao.insert(sysDepartment);
        }
    }

    @Override
    public SysDepartment selectById(String id) {
        return sysDepartmentDao.selectById(id);
    }

    @Override
    public SysDepartment selectByCode(String code) {
        return sysDepartmentDao.selectByCode(code);
    }

}
