package cn.ff.common.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysUserRole implements Serializable {
    private String id;
    private String userId;
    private String roleId;
}
