package com.cn.swm.model.sys;

import java.util.Date;

public class Tcategory {
    private String id;

    private String name;

    private Date caretetime;

    private Date updatetime;

    private String pid;

    private String isSelect;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCaretetime() {
        return caretetime;
    }

    public void setCaretetime(Date caretetime) {
        this.caretetime = caretetime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
    
}