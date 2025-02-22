package com.mire.cinema.service;

import java.util.List;
import java.util.Map;

import com.mire.cinema.domain.notice.Notice;
import com.mire.cinema.domain.notice.NoticeDTO;

public interface NoticeService {
	// 공지사항 등록
	public void saveNotice(Notice notice);

	// 공지사항 리스트 출력
	public List<Notice> seeNotice();

	// 공지사항 내용 출력
	public Notice findNotice(long boardNo);

	// 공지사항 검색기능 or 페이징
	public Notice findSearchNotice(String boardTitle);
	public Map<String, Object> getNoticeMap(Integer pageNum, String boardTitle);
	public int getTotalNotice();
	public int getTotalNotice(String boardTitle);

	// 공지사항 내용 수정
	public void modifyNotice(NoticeDTO.NoticeUpdate notice);

	// 공지사항 삭제
	public void removeNotice(Long boardNo);

}
