package com.github.muxfe.contrib.test.kdniao;

import com.github.muxfe.contrib.kdniao.KdniaoConfiguration;
import com.github.muxfe.contrib.kdniao.KdniaoService;
import com.github.muxfe.contrib.kdniao.KdniaoService.RecogniseResult;
import com.github.muxfe.contrib.kdniao.KdniaoService.RecogniseResult.Shipper;
import com.github.muxfe.contrib.kdniao.KdniaoServiceImpl;

import lombok.val;

public class KdniaoServiceTraceTest {

	public static void main(String[] args) {
		val service = new KdniaoServiceImpl(KdniaoConfiguration.builder().eBusinessID("1565068").appKey("c2599a03-9cb8-441b-a935-db7a66a548f3").build());
		val p = KdniaoService.RecogniseParameters.builder().logisticCode("118650888018").build();
		RecogniseResult result = service.recognise(p);
		for (Shipper shipper : result.getShippers()) {
			System.out.println(shipper.getShipperCode() + "," + shipper.getShipperName());
		}
	}
}
