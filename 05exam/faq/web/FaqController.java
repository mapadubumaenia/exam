package egovframework.example.faq.web;

import java.util.List;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import egovframework.example.common.Criteria;
import egovframework.example.faq.service.FaqService;

@Controller
public class FaqController {

	@Autowired
	private FaqService faqService;
	
	@GetMapping("/faq/faq.do")
	public String name( @ModelAttribute Criteria criteria,
	          Model model) {
		
		
		//1)등차자동계산 클래스:PaginationInfo
		// -필요정보: (1) 현재페이지번호(pageIndex), 2)보일개수  (pageUnit): 3 
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(criteria.getPageIndex());
		paginationInfo.setRecordCountPerPage(criteria.getPageUnit());
		//등차를 자동 계산 결과: paginationInfo.getFirstRecordIndex 여기에있음
		criteria.setFirstIndex(paginationInfo.getFirstRecordIndex());
		
		//전체조회 서비스 메소드 실행
		List<?> faqs= faqService.selectFaqList(criteria);
		
		model.addAttribute("faqs", faqs);
	
		return "faq/faq_all";
	}
}
