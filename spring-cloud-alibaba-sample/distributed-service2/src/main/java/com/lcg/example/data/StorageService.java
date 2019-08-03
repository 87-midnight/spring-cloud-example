package com.lcg.example.data;

public interface StorageService {

    /**
     * 扣减库存
     *
     * @param reduceStockRequestVO 请求 VO
     * @return 操作结果
     * @throws Exception 扣减失败时抛出异常
     */
    OperationResponse reduceStock(ReduceStockRequestVO reduceStockRequestVO) throws Exception;
}
