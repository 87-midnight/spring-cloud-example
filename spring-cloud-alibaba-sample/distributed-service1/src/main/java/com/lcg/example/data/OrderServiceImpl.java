package com.lcg.example.data;

import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderMapper orderDao;

    private final String STORAGE_SERVICE_HOST = "http://storage-service/storage";
    private final String PAY_SERVICE_HOST = "http://pay-service/pay";

    @GlobalTransactional
    @Override
    public OperationResponse placeOrder(PlaceOrderRequestVO placeOrderRequestVO) {
        Integer amount = 1;
        Integer price = placeOrderRequestVO.getPrice();

        Order order = Order.builder()
                .userId(placeOrderRequestVO.getUserId())
                .productId(placeOrderRequestVO.getProductId())
                .status(OrderStatus.INIT)
                .payAmount(price)
                .build();

        Integer saveOrderRecord = orderDao.saveOrder(order);
        log.info("保存订单{}", saveOrderRecord > 0 ? "成功" : "失败");
        log.info("当前 XID: {}", RootContext.getXID());
        // 扣减库存
        log.info("开始扣减库存");
        ReduceStockRequestVO reduceStockRequestVO = ReduceStockRequestVO.builder()
                .productId(placeOrderRequestVO.getProductId())
                .amount(amount)
                .build();
        String storageReduceUrl = String.format("%s/reduceStock", STORAGE_SERVICE_HOST);
        OperationResponse storageOperationResponse = restTemplate.postForObject(storageReduceUrl, reduceStockRequestVO, OperationResponse.class);
        log.info("扣减库存结果:{}", storageOperationResponse);

        // 扣减余额
        log.info("开始扣减余额");
        ReduceBalanceRequestVO reduceBalanceRequestVO = ReduceBalanceRequestVO.builder()
                .userId(placeOrderRequestVO.getUserId())
                .price(price)
                .build();

        String reduceBalanceUrl = String.format("%s/reduceBalance", PAY_SERVICE_HOST);
        OperationResponse balanceOperationResponse = restTemplate.postForObject(reduceBalanceUrl, reduceBalanceRequestVO, OperationResponse.class);
        log.info("扣减余额结果:{}", balanceOperationResponse);

        Integer updateOrderRecord = orderDao.updateOrder(order.getId(), OrderStatus.SUCCESS);
        log.info("更新订单:{} {}", order.getId(), updateOrderRecord > 0 ? "成功" : "失败");

        return OperationResponse.builder()
                .success(balanceOperationResponse.isSuccess() && storageOperationResponse.isSuccess())
                .build();
    }

}