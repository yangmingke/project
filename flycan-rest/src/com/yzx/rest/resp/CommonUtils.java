package com.yzx.rest.resp;

public class CommonUtils {
	public static PageResp setPageInfoFromPage(Page page) {
		int currentPage = 0;
		int onePageSize = 0;
		long totalResults = 0;
		int firstResult = 0;
		int nextPage = 0;
		int previousPage = 0;
		long totalPage = 0;
		if (page != null){
			currentPage = page.getPageNo();
			onePageSize = page.getPageSize();
			totalResults = page.getTotalCount();
			firstResult = page.getFirst();
			nextPage = page.getNextPage();
			previousPage = page.getPrePage();
			totalPage = page.getTotalPages();
			PageResp obj = new PageResp();
			obj.setCurrentPage(currentPage);
			obj.setOnePageSize(onePageSize);
			obj.setTotalResults(totalResults);
			obj.setFirstResult(firstResult);
			obj.setNextPage(nextPage);
			obj.setPreviousPage(previousPage);
			obj.setTotalPage(totalPage);
			return obj;
		} else {
			return null;
		}
	}
}
