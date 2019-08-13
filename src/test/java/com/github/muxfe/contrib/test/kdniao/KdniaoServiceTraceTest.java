package com.github.muxfe.contrib.test.kdniao;

import com.github.muxfe.contrib.kdniao.KdniaoConfiguration;
import com.github.muxfe.contrib.kdniao.KdniaoService;
import com.github.muxfe.contrib.kdniao.KdniaoService.TrackResult;
import com.github.muxfe.contrib.kdniao.KdniaoService.TrackResult.Trace;
import com.github.muxfe.contrib.kdniao.KdniaoServiceImpl;

import lombok.val;

public class KdniaoServiceTraceTest {

	public static void main(String[] args) {
		val service = new KdniaoServiceImpl(KdniaoConfiguration.builder().eBusinessID("平台ID").appKey("00000000-0000-0000-0000-000000000000").build());
		val p = KdniaoService.TrackParameters.builder().shipperCode("SF").customerName("发件人/收件人手机后4位").logisticCode("118650888018").build();
		TrackResult result = service.track(p);
		System.out.println(result.getState());
		for (Trace traces : result.getTraces()) {
			System.out.println(traces.getAcceptTime() + "  " + traces.getAcceptStation());
		}
	}
}
