package ksmart.oci.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import ksmart.oci.dto.Goods;
import ksmart.oci.dto.Member;
import ksmart.oci.mapper.GoodsMapper;
import ksmart.oci.mapper.MemberMapper;


@Service
@Transactional
public class GoodsService {
	
	private static final Logger log = LoggerFactory.getLogger(GoodsService.class);
	
	//DI 의존성 주입
	private final GoodsMapper goodsMapper;
	private final MemberMapper memberMapper;
	
	public GoodsService(GoodsMapper goodsMapper, MemberMapper memberMapper) {
		this.goodsMapper = goodsMapper;
		this.memberMapper = memberMapper;
	}
	
	@PostConstruct
	public void goodsServicInit() {
		log.info("goodsService bean 생성");
	}
	//판매자 목록조회
	public List<Member> getSearchSellerList(Map<String,Object> paramMap){
		List<Member> sellerList = goodsMapper.getSearchSellerList(paramMap);
		return sellerList;
	}
	
	//상품등록
	public void addGoods(Goods goods) {
		log.info("상품등록 전 goods ::: {}", goods);
		goodsMapper.addGoods(goods);
		log.info("상품등록 후 goods ::: {}", goods);
	}
	
	//판매자 아이디 조회
	public List<Member> getSellerList() {
		
		List<Member> sellerList = memberMapper.getSellerList();
		
		return sellerList;
	}
	
	//상품목록조회
	public Map<String, Object> getGoodsList(int currentPage){
		
		int rowPerPage = 10;
		int rowFirstNum = (currentPage - 1) * rowPerPage;
		double rowCnt = goodsMapper.getGoodsListCount();
		
		int lastPage = (int) Math.ceil(rowCnt/rowPerPage);
		

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowFistNum", rowFirstNum);
		paramMap.put("rowPerPage", rowPerPage);
		
		List<Goods> goodsList = goodsMapper.getGoodsList(paramMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lastPage", lastPage);
		resultMap.put("goodsList", goodsList);
		
		return resultMap;
	}


}
