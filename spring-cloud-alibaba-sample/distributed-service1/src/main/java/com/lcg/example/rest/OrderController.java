package com.lcg.example.rest;

import com.lcg.example.data.OperationResponse;
import com.lcg.example.data.OrderService;
import com.lcg.example.data.PlaceOrderRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    @ResponseBody
    public OperationResponse placeOrder(@RequestBody PlaceOrderRequestVO placeOrderRequestVO) {
        log.info("收到下单请求,用户:{}, 商品:{}", placeOrderRequestVO.getUserId(), placeOrderRequestVO.getProductId());
        return orderService.placeOrder(placeOrderRequestVO);
    }
}