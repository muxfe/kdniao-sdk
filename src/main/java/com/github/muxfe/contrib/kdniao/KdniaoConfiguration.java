package com.github.muxfe.contrib.kdniao;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class KdniaoConfiguration {

  String eBusinessID;

  String appKey;

}
