package ksmart.oci.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ksmart.oci.dto.Goods;
import ksmart.oci.dto.Member;

@Mapper
public interface GoodsMapper {
	// 판매자 목록 조회
	public List<Member> getSearchSellerList(Map<String, Object> searchMap);
	
	// 상품등록
	public int addGoods(Goods goods);

	// 상품목록조회
	public List<Goods> getGoodsList(Map<String, Object> paramMap);
	
	// 상품목록갯수
	public int getGoodsListCount();
}
