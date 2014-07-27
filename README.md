# Spring Cloud Service Connector for Amazon S3 Services

This project provides a [Spring Cloud](https://github.com/spring-projects/spring-cloud) service connector for Amazon S3 services brokered by the Cloud Foundry [s3-cf-service-broker](https://github.com/davidehringer/s3-cf-service-broker).

## Example Usage

Add the library to your project:

```
<dependency>
	<groupId>org.cloudfoundry.community</groupId>
	<artifactId>spring-cloud-s3-service-connector</artifactId>
	<version>1.0.0</version>
</dependency>
```

Bind an S3 service to your Cloud Foundry application.

Use Spring Cloud to get an `S3ServiceInfo` instance.

```
CloudFactory cloudFactory = new CloudFactory();
Cloud cloud = cloudFactory.getCloud();

S3ServiceInfo serviceInfo = (S3ServiceInfo) cloud.getServiceInfo("my-s3-service");
...  serviceInfo.getAccessKeyId();
...  serviceInfo.getSecretAccessKey();
...  serviceInfo.getBucket();
```
