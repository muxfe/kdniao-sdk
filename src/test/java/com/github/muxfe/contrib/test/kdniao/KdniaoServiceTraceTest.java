package com.github.muxfe.contrib.test.kdniao;

import com.github.muxfe.contrib.kdniao.KdniaoConfiguration;
import com.github.muxfe.contrib.kdniao.KdniaoService;
import com.github.muxfe.contrib.kdniao.KdniaoService.RecogniseResult;
import com.github.muxfe.contrib.kdniao.KdniaoService.RecogniseResult.Shipper;
import com.github.muxfe.contrib.kdniao.KdniaoServiceImpl;

import lombok.val;

public class KdniaoServiceTraceTest {

	public static void main(String[] args) {
		val service = new KdniaoServiceImpl(KdniaoConfiguration.builder().eBusinessID("xxxxxx").appKey("0000-9cb8-00000-0000-0000").build());
		val p = KdniaoService.RecogniseParameters.builder().logisticCode("118650888018").build();
		RecogniseResult result = service.recognise(p);
		for (Shipper shipper : result.getShippers()) {
			System.out.println(shipper.getShipperCode() + "," + shipper.getShipperName());
		}
	}
}
