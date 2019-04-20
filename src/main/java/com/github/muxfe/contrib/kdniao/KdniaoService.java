package com.github.muxfe.contrib.kdniao;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.List;

public interface KdniaoService {

  /**
   * <a href="https://www.kdniao.com/api-track">即时查询</a>
   */
  TrackResult track(TrackParameters p);

  @Data
  @Builder
  class TrackParameters {
    String orderCode;
    String shipperCode;
    String logisticCode;
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

    @UtilityClass
    public static class State {
      public final Integer NO_TRACE = 0;
      public final Integer COLLECTED = 1;
      public final Integer DELIVERING = 2;
      public final Integer SIGNED = 3;
      public final Integer PROBLEM = 4;
    }
  }

}
