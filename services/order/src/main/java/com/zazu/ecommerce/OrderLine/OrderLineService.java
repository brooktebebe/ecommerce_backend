package com.zazu.ecommerce.OrderLine;

import com.zazu.ecommerce.OrderLine.OrderLineRequest;
import com.zazu.ecommerce.OrderLine.OrderLineMapper;
import com.zazu.ecommerce.OrderLine.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderlineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream().map(orderLineMapper::toOrderLineResponse).collect(Collectors.toList());
    }
}
