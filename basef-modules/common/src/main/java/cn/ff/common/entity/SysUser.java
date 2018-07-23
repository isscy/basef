package cn.ff.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class SysUser implements Serializable {
    private String id;
    private String username;
    private String password;
    private String status;
    private String tel;
    private List<SysRole> roles;
    private String delFlag;
}
