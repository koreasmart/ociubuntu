package ksmart.oci.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.oci.dto.LoginInfo;
import ksmart.oci.dto.Member;
import ksmart.oci.dto.MemberLevel;


@Mapper
public interface MemberMapper {
	
	// 로그인 사용자 조회
	public LoginInfo getLoginInfo(String memberId);
	
	// (판매자 1) 등록한 상품에 대한 주문 이력 삭제
	public int removeOrderByGoodsCode(String memberId);
	// (판매자 2) 판매자가 등록한 상품삭제
	public int removeGoodsBySellerId(String memberId);
	// (구매자 1) 주문이력삭제
	public int removeOrderByOrderId(String memberId);
	// (공통 1) 로그인 이력삭제
	public int removeLoginHistory(String memberId);
	// (공통 2) 회원삭제
	public int removeMember(String memberId);
	
	// 판매자 조회
	public List<Member> getSellerList();
	
	// 회원 수정
	public int modifyMember(Member member);
	
	// 특정 회원 정보 조회
	public Member getMemberInfoById(String memberId);
	
	// 아이디 중복체크
	public boolean idCheck(String memberId);
	
	//회원 가입
	public int addMember(Member member);
	
	//회원 등급 조회
	public List<MemberLevel> getMemberLevelList();
	
	//회원 목록 조회
	public List<Member> getMemberList();

}
