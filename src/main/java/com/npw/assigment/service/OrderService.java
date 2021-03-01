package com.npw.assigment.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.npw.assigment.dto.Item;
import com.npw.assigment.dto.OrderDetailResponse;
import com.npw.assigment.dto.OrderSummaryResponse;
import com.npw.assigment.dto.SKU;
import com.npw.assigment.mock.MockUtil;

@Service
public class OrderService {

	@Value("${order.service.url}")
	private String url;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CatalogService catalogService;

	@Autowired
	MockUtil mockUtil;

	public List<OrderSummaryResponse> getOrderSummary(String customerId) {

		List<OrderSummaryResponse> response = new ArrayList<>();

		List<Long> orders = mockUtil.getCustIdToOrderId().get(customerId);

		if (!CollectionUtils.isEmpty(orders)) {

			for (Long id : orders) {
				response.add(getOrderSummary(id));
			}
		}

		return response;

	}

	public OrderDetailResponse getOrderDetails(Long orderId) {

		String orderUrl = url + orderId;
		OrderDetailResponse orderDetail = restTemplate.getForObject(orderUrl, OrderDetailResponse.class);

		for (Item item : orderDetail.getItems()) {

			SKU sku = catalogService.getSKU(item.getSkuId());
			orderDetail.getSkus().add(sku);
		}

		return orderDetail;

	}

	private OrderSummaryResponse getOrderSummary(Long id) {

		String orderUrl = url + id;
		OrderSummaryResponse orderSummary = restTemplate.getForObject(orderUrl, OrderSummaryResponse.class);
		return orderSummary;
	}

}
