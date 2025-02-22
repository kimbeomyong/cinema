package com.mire.cinema.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mire.cinema.domain.notice.Notice;
import com.mire.cinema.domain.notice.NoticeDTO;
import com.mire.cinema.domain.notice.NoticeDTO.Search;
import com.mire.cinema.domain.page.PageCreate;
import com.mire.cinema.exception.ErrorMsg;
import com.mire.cinema.repository.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {
	private final NoticeMapper noticeMapper;

	// 공지사항 등록
	@Override
	public void saveNotice(Notice notice) {
		noticeMapper.insertNotice(notice);
	}

	// 공지사항 출력하기
	@Override
	public List<Notice> seeNotice() {
		return noticeMapper.selectNotice();
	}

	// 공지사항 내용 출력
	@Override
	public Notice findNotice(long boardNo) {
		noticeMapper.updateNoticeViews(boardNo);
		return noticeMapper.getNotice(boardNo);
	}

	// 공지사항 페이징처리 및 검색기능
	@Override
	public Map<String, Object> getNoticeMap(Integer pageNum, String boardTitle) {
		Map<String, Object> map = new HashMap<>();

		PageCreate pc = new PageCreate();

		if (boardTitle == null || "".equals(boardTitle.trim())) {
			pc = pc.getPage(pageNum, getTotalNotice());
			map.put("list", noticeMapper.getPartList(pc.getPaging().getStartNum(), pc.getPaging().getEndNum()));
		} else {
			pc = pc.getPage(pageNum, getTotalNotice(boardTitle));
			NoticeDTO.Search noticeDTO = new Search();
			noticeDTO.setBoardTitle(boardTitle);
			noticeDTO.setStartNum(pc.getPaging().getStartNum());
			noticeDTO.setEndNum(pc.getPaging().getEndNum());

			map.put("Keyword", boardTitle);
			map.put("searchList", noticeMapper.searchNoticeList(noticeDTO));
		}

		map.put("page", pc);

		return map;
	}

	@Override
	public int getTotalNotice() {
		int num = noticeMapper.countNotice();
		if (num == 0) {// 0이면
			throw new NullPointerException(ErrorMsg.DataNOTFOUND);
		}
		return num;
	}

	@Override
	public int getTotalNotice(String boardTitle) {
		if (boardTitle == null) {
			return noticeMapper.countNotice();
		} else {
			return noticeMapper.countNoticeTitle(boardTitle);
		}
	}

	@Override
	public Notice findSearchNotice(String boardTitle) {
		return noticeMapper.getSearchNotice(boardTitle);
	}

	// 공지사항 내용 업데이트
	@Override
	public void modifyNotice(NoticeDTO.NoticeUpdate notice) {
		noticeMapper.updateNotice(notice);
	}

	// 공지사항 내용 삭제
	@Override
	public void removeNotice(Long boardNO) {
		noticeMapper.deleteNotice(boardNO);
	}

}
