package com.github.muxfe.contrib.kdniao;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class KdniaoConfiguration {

  @NonNull String eBusinessID;

  @NonNull String appKey;

}
