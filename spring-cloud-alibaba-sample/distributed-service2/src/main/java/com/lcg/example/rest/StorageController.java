package com.lcg.example.rest;

import com.lcg.example.data.OperationResponse;
import com.lcg.example.data.ReduceStockRequestVO;
import com.lcg.example.data.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/reduceStock")
    public OperationResponse reduceStock(@RequestBody ReduceStockRequestVO reduceStockRequestVO) throws Exception {
        return storageService.reduceStock(reduceStockRequestVO);
    }
}
