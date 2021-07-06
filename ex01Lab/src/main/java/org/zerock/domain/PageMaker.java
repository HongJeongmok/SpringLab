package org.zerock.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount;  // 기본생성자가 없으므로 0 -> 192
	private int startPage;  // 0 -> 1
	private int endPage;  // 0 -> 10
	private boolean prev;  // false -> false
	private boolean next;  // false -> true

	private int displayPageNum = 10;

	private Criteria cri;  // null -> {page:1, perPageNum:10}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;  //192

		calcData();
	}

	private void calcData() {

		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		// (int) (Math.ceil(1/10.0) * 10) = 1*10 = 10
		startPage = (endPage - displayPageNum) + 1;
		// 10-10 + 1 = 1
		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		//(int) (Math.ceil(192 / 10.0)) = (int) 20.0 = 20
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true;  // false

		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;  // 10 * 10 >= 192는 거짓이므로 true

	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public String makeQuery(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum()).build();

		return uriComponents.toUriString();
	}

	public String makeSearch(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria) cri).getSearchType())
				.queryParam("keyword", ((SearchCriteria) cri).getKeyword()).build();

		return uriComponents.toUriString();
	}
}
