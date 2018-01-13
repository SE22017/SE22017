package com.cn.swm.model.activiti;
import java.util.List;


public class PageInfo<T> {
	public Integer pageSize = 5;
	private Long count;
	//private List<T> pageList;
	private Integer pageIndex;
	private Long total;
	private List<T> rows;
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getCount() {
		return count;
	}


	public void setCount(long deployCount) {
		this.count = deployCount;
	}

	/*public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}*/

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	/*public long getTotalPages() {
		this.total = this.count / this.pageSize;
		if (this.count % this.pageSize != 0)
			this.total++;
		return this.total;
	}*/

}
