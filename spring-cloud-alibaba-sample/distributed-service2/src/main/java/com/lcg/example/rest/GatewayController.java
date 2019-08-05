package com.lcg.example.rest;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/api")
    public Object getApiDefinition(){
        return GatewayApiDefinitionManager.getApiDefinitions();
    }

    @GetMapping("/gateway")
    public Object getGatewayRules(){
        return GatewayRuleManager.getRules();
    }
    @GetMapping("/flow")
    public Object apiFlow() {
        return FlowRuleManager.getRules();
    }
}
