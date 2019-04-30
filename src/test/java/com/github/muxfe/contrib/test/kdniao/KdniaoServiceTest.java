package com.github.muxfe.contrib.test.kdniao;

import com.github.muxfe.contrib.kdniao.KdniaoConfiguration;
import com.github.muxfe.contrib.kdniao.KdniaoService;
import com.github.muxfe.contrib.kdniao.KdniaoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.MissingResourceException;

@Slf4j
public class KdniaoServiceTest {

  private KdniaoService service;

  @Before
  public void setup() {
    try {
      service = new KdniaoServiceImpl(
        KdniaoConfiguration.builder().
          eBusinessID(getenv("E_BUSINESS_ID")).
          appKey(getenv("APP_KEY")).
          build()
      );
    } catch (MissingResourceException e) {
      log.error("{} is not set", e.getKey());
    }
  }

  private String getenv(String name) {
    val value = System.getenv(name);
    if (value == null) throw new MissingResourceException("", "", name);
    return value;
  }

  @Test
  public void track() {
    if (service == null) return;
    val p = KdniaoService.TrackParameters.builder().
      shipperCode("SF").
      logisticCode("118650888018").
      build();
    log.debug("track parameters = {}", p);
    val r = service.track(p);
    log.debug("track result = {}", r);
    Assert.assertNotNull(KdniaoService.TrackResult.State.NO_TRACE);
    Assert.assertNotNull(r.getState());
    Assert.assertNotNull(r.getTraces());
    for (val t : r.getTraces()) {
      Assert.assertNotNull(t.getAcceptTime());
      Assert.assertNotNull(t.getAcceptStation());
    }
  }

  @Test
  public void sign() throws Exception {
    val service = new KdniaoServiceImpl(
      KdniaoConfiguration.builder().
        eBusinessID("").
        appKey("00000000-0000-0000-0000-000000000000").
        build()
    );
    val p = KdniaoService.TrackParameters.builder().
      shipperCode("SF").
      logisticCode("118650888018").
      build();
    val data = service.getMapper().writeValueAsString(p);
    val dataSign = service.sign(data);
    Assert.assertEquals("YjQ4MmJlMjgzYjA0ZGY4ODUxYjgwMzc4MTY0YzY0MzU=", dataSign);
  }

}
