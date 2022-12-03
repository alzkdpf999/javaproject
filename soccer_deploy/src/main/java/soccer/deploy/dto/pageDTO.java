package soccer.deploy.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.Data;

@Data
public class pageDTO{
	
	private Params params;            /** 여러개의 전달인자 포장 */
	private int rowCount;             /** 테이블로부터 검색된 행의 수 */
	
	private int pageCount;            /** 페이지 수 */
	private int listNo;               /** 목록별 식별번호 */
	private int startPage;            /** 현재 페이지 목록의 시작 번호 */
	private int endPage;              /** 현재 페이지 목록의 마지막 번호 */
	private int previousStartPage;    /** 이전 페이지 목록의 시작 번호 */
	private int nextStartPage;        /** 다음 페이지 목록의 마지막 번호 */
	
	public pageDTO() {}
	
	/**
	 * @param params           요청 파라메터
	 * @param totalRowCount    검색타입별 검색된 행의 수
	 */
	public pageDTO(Params params, int rowCount ) {
		this.params = params;
		this.rowCount = rowCount;
	}
	
	/** setter/getter method */
	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getListNo() {
		return listNo;
	}

	public void setListNo(int listNo) {
		this.listNo = listNo;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPreviousStartPage() {
		return previousStartPage;
	}

	public void setPreviousStartPage(int previousStartPage) {
		this.previousStartPage = previousStartPage;
	}

	public int getNextStartPage() {
		return nextStartPage;
	}

	public void setNextStartPage(int nextStartPage) {
		this.nextStartPage = nextStartPage;
	}
	
	@Override
	public String toString() {
		return "PageBuilder [params=" + params + ", rowCount=" + rowCount + ", pageCount=" + pageCount + ", listNo="
				+ listNo + ", startPage=" + startPage + ", endPage=" + endPage + ", previousStartPage="
				+ previousStartPage + ", nextStartPage=" + nextStartPage + "]";
	}

	/** 페이징 계산 */
	public void build(){
		// DB로부터 검색된 행의 수에 따른 전체페이지수 계산
		pageCount = (int)Math.ceil((double)rowCount / params.getPageSize());
		
		// 목록별 번호
		listNo = (params.getRequestPage() - 1) / params.getPageSize();
		//(1~5): 0, (6~10): 1, (11~15): 2, .....
		
		// 현재 목록의 시작페이지번호와 마지막페이지번호 계산
		startPage = (listNo * params.getPageCount()) + 1;
		endPage = (listNo * params.getPageCount()) + params.getPageCount();
		
		if (endPage > pageCount){
			endPage = pageCount;
		}
		
		// 이전 목록의 시작페이지 번호 계산
		previousStartPage = startPage - params.getPageSize();
		// 첫번째 목록인 경우 1페이지로 설정
		if (previousStartPage < 0)  previousStartPage = 1;
		
		// 다음 목록의 시작페이지 번호 계산
		nextStartPage = startPage + params.getPageSize();
	}
	
	/** 현재 목록에서 [처음으로] 출력 여부 반환 */
	public boolean isShowFirst() {
		return listNo > 0;
	}
	
	/** 현재 목록에서 [끝으로] 출력 여부 반환 */
	public boolean isShowLast() {
		return endPage < pageCount;
	}
	
	/** 현재 목록에서 [이전목록] 출력 여부 반환 */
	public boolean isShowPrevious() {
		return listNo > 0;
	}
	
	/** 현재 목록에서 [다음목록] 출력 여부 반환 */
	public boolean isShowNext() {
		return endPage < pageCount;
	}
	
	/** 동적 쿼리스트링 반환 */
	public String getQueryString(int page) {
		String queryString = "?page=" + page;
		String searchValue = null;
		try {
			searchValue = URLEncoder.encode(params.getSearchValue(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 조건검색이 있는 경우
		queryString += !params.getSearchType().equals("") ? "&type=" + params.getSearchType() + "&value=" + searchValue  :  "";
		return queryString;
	}
	
	
	/** 테스트을 위한 main */
	public static void main(String[] args) {
		Params params = new Params("", "", 5, 5, 6);
		int selectCount = 156;
		pageDTO pageBuilder = new pageDTO(params, selectCount);
		pageBuilder.build();
		
		System.out.println("검색된 행수: " + pageBuilder.getRowCount());
		System.out.println("요청페이지: " + pageBuilder.getParams().getRequestPage());
		
		System.out.println("전체페이지수: " + pageBuilder.getPageCount());
		
		System.out.println("현재목록의 시작페이지: " + pageBuilder.getStartPage());
		System.out.println("현재목록의 끝페이지: " + pageBuilder.getEndPage());
		
		System.out.println("처음으로 보여주기 여부: " + pageBuilder.isShowFirst());
		System.out.println("이전목록 보여주기 여부: " + pageBuilder.isShowPrevious());
		
		System.out.println("다음목록 보여주기 여부: " + pageBuilder.isShowNext());
		System.out.println("끝으로 보여주기 여부: " + pageBuilder.isShowLast());
		
		// JSP에서 페이지 번호 직접 출력 시
		if(pageBuilder.isShowFirst()) {
			System.out.print("처음으로 ");
		}
		
		if(pageBuilder.isShowPrevious()) {
			System.out.print("이전목록 ");
		}
		
		for(int i=pageBuilder.getStartPage(); i<=pageBuilder.getEndPage(); i++){
			System.out.print(i + " | ");
		}
		
		if(pageBuilder.isShowNext()) {
			System.out.print("다음목록 ");
		}
		
		if(pageBuilder.isShowLast()) {
			System.out.print("끝으로 ");
		}
		
		System.out.println();
		
		
		System.out.println("-----------------------------------------------");
		
		// 이름으로 검색 시
		Params searchParams = new Params("", "", 5, 5, 1 );
		int searchCount = 11;
		pageDTO pageBuilder2 = new pageDTO(searchParams, searchCount);
		pageBuilder2.build();
		System.out.println("검색된 행수: " + pageBuilder2.getRowCount());
		System.out.println("요청페이지: " + pageBuilder2.getParams().getRequestPage());
		
		System.out.println("전체페이지수: " + pageBuilder2.getPageCount());
		
	}
}
