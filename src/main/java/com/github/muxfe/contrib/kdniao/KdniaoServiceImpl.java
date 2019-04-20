package com.github.muxfe.contrib.kdniao;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@RequiredArgsConstructor
public class KdniaoServiceImpl implements KdniaoService {

  private final KdniaoConfiguration configuration;

  private final OkHttpClient client = new OkHttpClient();

  @Getter
  private final ObjectMapper mapper = new ObjectMapper().
    setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE).
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Override
  @SneakyThrows(IOException.class)
  public TrackResult track(TrackParameters p) {
    val data = mapper.writeValueAsString(p);
    val form = new FormBody.Builder().
      add("RequestData", data).
      add("EBusinessID", configuration.getEBusinessID()).
      add("RequestType", "1002").
      add("DataSign", sign(data)).
      add("DataType", "2").
      build();
    val request = new Request.Builder().
      url("https://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx").
      post(form).
      build();
    try (val response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new KdniaoException("Response code is " + response.code());
      if (Objects.isNull(response.body())) throw new KdniaoException("Response body is null");
      val node = mapper.readTree(response.body().charStream());
      if (!node.path("Success").asBoolean()) throw new KdniaoException(node.path("Reason").asText());
      return mapper.treeToValue(node, TrackResult.class);
    }
  }

  @SneakyThrows(NoSuchAlgorithmException.class)
  public String sign(String data) {
    val md5 = MessageDigest.getInstance("MD5");
    md5.update((data + configuration.getAppKey()).getBytes(StandardCharsets.UTF_8));
    val buf = new StringBuilder();
    for (val b : md5.digest()) {
      buf.append(String.format("%02x", b));
    }
    val wtf = buf.toString().getBytes(StandardCharsets.UTF_8);
    return Base64.getEncoder().encodeToString(wtf);
  }

}
