package com.npw.assigment.api;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.npw.assigment.dto.OrderDetailResponse;
import com.npw.assigment.dto.OrderSummaryResponse;
import com.npw.assigment.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("customer/{custId}/order/summary")
	public List<OrderSummaryResponse> getCustomerOrderSummary(@PathVariable(value = "custId") String custId)
			throws ClientProtocolException, IOException {

		return orderService.getOrderSummary(custId);

	}

	@GetMapping("/order/{orderId}/detail")
	public OrderDetailResponse getOrderDetail(@PathVariable(value = "orderId") Long orderId)
			throws ClientProtocolException, IOException {

		return orderService.getOrderDetails(orderId);

	}

}
