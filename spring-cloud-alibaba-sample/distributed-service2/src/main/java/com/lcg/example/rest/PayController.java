package com.lcg.example.rest;

import com.lcg.example.data.OperationResponse;
import com.lcg.example.data.PayService;
import com.lcg.example.data.ReduceBalanceRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/reduceBalance")
    @ResponseBody
    public OperationResponse reduceBalance(@RequestBody ReduceBalanceRequestVO reduceBalanceRequestVO) throws Exception {
        return payService.reduceBalance(reduceBalanceRequestVO);
    }

}
