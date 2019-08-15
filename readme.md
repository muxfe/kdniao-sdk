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
<repository>
	<id>nutz</id>
	<url>http://jfrog.nutz.cn/artifactory/libs-release</url>
</repository>
<repository>
	<id>nutz-snapshots</id>
	<url>http://jfrog.nutz.cn/artifactory/snapshots</url>
	<snapshots>
		<enabled>true</enabled>
		<updatePolicy>always</updatePolicy>
	</snapshots>
	<releases>
	<enabled>false</enabled>
	</releases>
</repository>
```

添加 Kdniao SDK 到 `pom.xml`:

```xml
  <dependency>
    <groupId>com.github.muxfe.contrib</groupId>
  	<artifactId>kdniao-sdk</artifactId>
  	<version>1.0.1</version>
  </dependency>
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


RecogniseParameters parameters =
  RecogniseParameters.builder().
      logisticCode("118650888018").
      build();

RecogniseResult result = kdniaoService.recognise(parameters);

FollowParameters parameters =
  FollowParameters.builder().
      shipperCode("SF").
      logisticCode("118650888018").
      build();

FollowResult result = kdniaoService.follow(parameters);
```

## License

[MIT](license)
