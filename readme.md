# kdniao-sdk

快递鸟 (kdniao.com) 非官方 Java SDK

## 目前支持的 API

- [x] 即时查询 [track](https://www.kdniao.com/api-track)
- [x] 物流跟踪 [follow](https://www.kdniao.com/api-follow)
- [ ] 在途监控 [monitor](https://www.kdniao.com/api-monitor)
- [x] 单号识别 [recognise](https://www.kdniao.com/api-recognise)

## Maven 安装

添加 JitPack Repository 到 `pom.xml`:

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

添加 Kdniao SDK 到 `pom.xml`:

```xml
<dependencies>
  <dependency>
    <groupId>com.github.muxfe</groupId>
    <artifactId>kdniao-sdk</artifactId>
    <version>11c9a7ad8b</version>
  </dependency>
</dependencies>
```

## 使用

```java
KdniaoConfiguration kdniaoConfiguration =
  KdniaoConfiguration.builder().
    eBusinessID("YOUR E_BUSINESS_ID").
    appKey("YOUR APP_KEY").
    build();

KdniaoService kdniaoService =
  new KdniaoServiceImpl(kdniaoConfiguration);

TrackParameters parameters =
  TrackParameters.builder().
      shipperCode("SF").
      customerName("收件人/发件人 手机后4位").
      logisticCode("118650888018").
      build();

TrackResult result = kdniaoService.track(parameters);
```

## License

[MIT](license)
