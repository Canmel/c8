package com.camel.c8.model;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

import java.util.Date;

@SmartTable(name = "系统菜单")
public class SysMenu {
    /**
     * 菜单ID
     */
    @SmartColumn(id=0, name = "ID", autoMerge = true)
    private Integer menuId;
    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentId;
    /**
     * 菜单名称
     */
    @SmartColumn(id = 1, name = "菜单名称")
    private String name;
    /**
     * 菜单url
     */
    @SmartColumn(id = 2, name = "地址")
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序号
     */
    private Integer orderNum;
    /**
     * 创建时间
     */
    private String gmtCreate;
    /**
     * 更新时间
     */
    @SmartColumn(id = 3, name = "最后更新时间")
    private Date gmtModifiled;
    /**
     * 状态(0：已删除，1：正常)
     */
    private String status;

    public SysMenu(Integer menuId, String name, String url, Date gmtModifiled) {
        this.menuId = menuId;
        this.name = name;
        this.url = url;
        this.gmtModifiled = gmtModifiled;
    }
}
