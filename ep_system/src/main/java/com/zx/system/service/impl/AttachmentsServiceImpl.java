package com.zx.system.service.impl;

import com.zx.system.dao.SysAttachmentDao;
import com.zx.system.dao.SysDepartmentDao;
import com.zx.system.model.SysAttachment;
import com.zx.system.model.SysDepartment;
import com.zx.system.service.AttachmentsService;
import com.zx.system.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("attachmentsService")
@Transactional
public class AttachmentsServiceImpl implements AttachmentsService {
    @Resource
    private SysAttachmentDao sysAttachmentDao;///附件表
    @Override
    public SysAttachment selectById(int id) {
      return   sysAttachmentDao.selectById(id);
    }
}
