package ksmart.oci.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;
import ksmart.oci.dto.Goods;
import ksmart.oci.dto.Member;
import ksmart.oci.service.GoodsService;


@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	
	private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

	
	private final GoodsService goodsService;
	
	public GoodsController(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	@PostConstruct
	public void goodsControllerInit() {
		log.info("goodsController bean 생성");
	}
	
	
	@GetMapping("/modifyGoods")
	public String modifyGoods() {
		
		return "goods/modifyGoods";
	}
	
	@PostMapping("/sellerList")
	public String getSearchSellerList(@RequestParam(name="searchKey", defaultValue = "memberId") String sk
									 ,@RequestParam(name="searchValue", required = false, defaultValue = "") String sv
									 ,@RequestParam Map<String, Object> paramMap1
									 ,Model model) {
		log.info("paramMap1 ::: {}", paramMap1);
		// 1. searchKey -> memberId, memberName..... -> db에 있는 컬럼 명 매칭 처리
		if("memberId".equals(sk)) {
			sk = "m_id";
		}else if("memberName".equals(sk)) {
			sk = "m_name";
		}else if("memberEmail".equals(sk)) {
			sk = "m_email";
		}else {
			sk = "g_name";
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sk", sk);
		paramMap.put("sv", sv);
		
		// 2. ${}  vs  #{}   => ${searchKey} LIKE #{searchValue} : ex) memberId LIKE 'id001'
		List<Member> sellerList = goodsService.getSearchSellerList(paramMap);
		
		// 3. model 검색된 리스트를 출력하면 됩니다.
		model.addAttribute("title", "판매자목록조회");
		model.addAttribute("sellerList", sellerList);
		
		return "goods/sellerList";
	}
	
	@GetMapping("/sellerList")
	public String getSellerList(Model model) {
		List<Member> sellerList = goodsService.getSearchSellerList(null);
		log.info("판매자 목록 조회  :::: {}", sellerList);
		model.addAttribute("title", "판매자 목록조회");
		model.addAttribute("sellerList", sellerList);
		
		return "goods/sellerList";
	
	}
	
	@PostMapping("/addGoods")
	public String addGoods(Goods goods) {
		
		goodsService.addGoods(goods);
		
		return "redirect:/goods/goodsList";
	}
	
	@GetMapping("/addGoods")
	public String addGoods(Model model) {
		
		// 판매자 조회
		List<Member> sellerList = goodsService.getSellerList();
		
		model.addAttribute("title", "상품등록");
		model.addAttribute("sellerList", sellerList);
		
		return "goods/addGoods";
	}
	
	@GetMapping("/goodsList")
	public String getGoodsList(Model model, @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage) {
		
		Map<String, Object> resultMap = goodsService.getGoodsList(currentPage);
		
		//log.info("상품목록  ::: {}", goodsList);
		
		model.addAttribute("title", "상품목록");
		model.addAttribute("goodsList", resultMap.get("goodsList"));
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		
		return "goods/goodsList";
	}
}
