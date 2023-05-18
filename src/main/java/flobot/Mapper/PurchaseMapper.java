package flobot.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import flobot.domain.AuthInfoVO;
import flobot.domain.CartVO;
import flobot.domain.DeliveryVO;
import flobot.domain.OrderListVO;
import flobot.domain.PaymentVO;
import flobot.domain.PurchaseVO;
@Mapper
public interface PurchaseMapper {

	public List<CartVO> goodsList(CartVO vo);

	public String selectNum();

	public int goodsOrder(PurchaseVO vo);

	public PurchaseVO purchaseSelect(String purchaseNum);

	public int paymentInsert(PaymentVO vo);

	public void purchaseStatusUpdate(String purchaseNum);

	public AuthInfoVO selectMember(String string);

	public int purchaseListInsert(PurchaseVO vo);

	public void cartItemDelete(CartVO cartVO);

	public List<OrderListVO> purchaseList(String userNum);

	public List<OrderListVO> marketPurchaseList(String userNum);

	public void deliveryUpdate(DeliveryVO vo);

	public void deliveryInsert(DeliveryVO vo);

	public void temp(String purchaseNum);

	public void purchaseOk(String purchaseNum);

	public int addPrp(PurchaseVO vo);
	
	

}
