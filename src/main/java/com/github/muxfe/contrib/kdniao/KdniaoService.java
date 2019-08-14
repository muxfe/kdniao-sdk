package com.github.muxfe.contrib.kdniao;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

public interface KdniaoService {

	/**
	 * <a href="https://www.kdniao.com/api-track">即时查询</a>
	 */
	TrackResult track(TrackParameters p);

	/**
	 * <a href="https://www.kdniao.com/api-follow">物流跟踪</a>
	 */
	FollowResult follow(FollowParameters p);

	/**
	 * <a href="https://www.kdniao.com/api-recognise">单号识别</a>
	 */
	
	RecogniseResult recognise(RecogniseParameters p);

	@Data
	@Builder
	class TrackParameters {
		String orderCode;
		String shipperCode;
		String logisticCode;
		String customerName;
	}

	@Data
	@Builder
	class RecogniseParameters {
		String logisticCode;
	}

	@Data
	@Builder
	class FollowParameters {
		String shipperCode;
		String logisticCode;
	}

	@Data
	class FollowResult {
		Date updateTime;
		String eBusinessID;
		boolean success;
	}

	@Data
	class TrackResult {
		Integer state;
		List<Trace> traces;

		@Data
		public static class Trace {
			String acceptTime;
			String acceptStation;
			String remark;
		}

		public static class State {
			public static final Integer NO_TRACE = 0;
			public static final Integer COLLECTED = 1;
			public static final Integer DELIVERING = 2;
			public static final Integer SIGNED = 3;
			public static final Integer PROBLEM = 4;
		}
	}

	@Data
	class RecogniseResult {
		String code;
		boolean success;
		String eBusinessID;
		String logisticCode;
		List<Shipper> shippers;

		@Data
		public static class Shipper {
			String shipperName;
			String shipperCode;
		}
	}
}