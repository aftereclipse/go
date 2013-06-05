package cn.go.util;

/**
 * <b>分页功能包装类</b><br />
 * 使用场景：分页展示数据时[分页工具栏]构建时使用
 * 
 * @author qingtian
 * 
 *         2011-2-4 下午09:23:19
 */
public final class Pager {
	/** 当前页码 */
	private int currentPage;
	/** 总页数 */
	private int totalPage;
	/** 每页记录数 */
	private int countPerPage;
	/** 总记录数 */
	private int totalCount;
	/** 可否前翻 */
	private int hasPrevent;
	/** 可否后翻 */
	private int hasNext;
	/** 是否为首页 */
	private int isFirst;
	/** 是否为尾页 */
	private int isLast;

	/**
	 * <b>唯一构造器</b><br />
	 * 通过传入的当前页码、每页记录数、总记录数参数值计算得到总页数
	 * 
	 * @param currentPage
	 *            当前页码，默认为1
	 * @param countPerPage
	 *            每页记录数，默认为10
	 * @param totalCount
	 *            总记录数，默认为0
	 */
	public Pager(int currentPage, int countPerPage, int totalCount) {
		// 当前页码
		if (currentPage < 0)
			this.currentPage = 1;
		else
			this.currentPage = currentPage;
		// 每页大小
		if (countPerPage < 0)
			this.countPerPage = 10;
		else
			this.countPerPage = countPerPage;
		// 总记录数
		if (totalCount < 0)
			this.totalCount = 0;
		else
			this.totalCount = totalCount;
		// 总页数
		this.totalPage = totalCount % countPerPage == 0 ? totalCount
				/ countPerPage : totalCount / countPerPage + 1;
		// 非法值纠正
		if (this.currentPage > this.totalPage)
			this.currentPage = this.totalPage;
		// 针对无记录
		if (this.currentPage == 0 && this.totalPage == 0) {
			this.currentPage = 1;
			this.totalPage = 1;
		}

		this.hasPrevent = (this.currentPage > 1 && this.currentPage <= this.totalPage) ? 1
				: 0;
		this.hasNext = (this.currentPage >= 1 && this.currentPage < this.totalPage) ? 1
				: 0;
		isFirst = this.currentPage == 1 ? 1 : 0;
		isLast = this.currentPage == this.totalPage ? 1 : 0;

	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
		// 重新计算总页数
		this.totalPage = totalCount % countPerPage == 0 ? totalCount
				/ countPerPage : totalCount / countPerPage + 1;
		this.hasPrevent = (this.currentPage > 1 && this.currentPage <= this.totalPage) ? 1
				: 0;
		this.hasNext = (this.currentPage >= 1 && this.currentPage < this.totalPage) ? 1
				: 0;
		isFirst = this.currentPage == 1 ? 1 : 0;
		isLast = this.currentPage == this.totalPage ? 1 : 0;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getHasPrevent() {
		return hasPrevent;
	}

	public void setHasPrevent(int hasPrevent) {
		this.hasPrevent = hasPrevent;
	}

	public int getHasNext() {
		return hasNext;
	}

	public void setHasNext(int hasNext) {
		this.hasNext = hasNext;
	}

	public int getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(int isFirst) {
		this.isFirst = isFirst;
	}

	public int getIsLast() {
		return isLast;
	}

	public void setIsLast(int isLast) {
		this.isLast = isLast;
	}

	@Override
	public String toString() {
		return "Pager [currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", countPerPage=" + countPerPage + ", totalCount="
				+ totalCount + ", hasPrevent=" + hasPrevent + ", hasNext="
				+ hasNext + ", isFirst=" + isFirst + ", isLast=" + isLast + "]";
	}
}
