package cn.ff.auth.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole implements Serializable {
    private String id;
    private String code;
    private String name;
    private String desc;
}
