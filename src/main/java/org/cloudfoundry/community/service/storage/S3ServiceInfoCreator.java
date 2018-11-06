/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cloudfoundry.community.service.storage;

import java.util.Map;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

/**
 * An internal service provider for creating S3ServiceInfo instances.
 *
 * @author David Ehringer
 */
public class S3ServiceInfoCreator extends CloudFoundryServiceInfoCreator<S3ServiceInfo> {

   public S3ServiceInfoCreator() {
      super(new Tags("s3"));
   }

   @Override
   public S3ServiceInfo createServiceInfo(final Map<String, Object> serviceData) {
      final String id = (String) serviceData.get("name");

      @SuppressWarnings("unchecked")
      final Map<String, Object> credentials = (Map<String, Object>) serviceData.get("credentials");

      final String username = (String) credentials.get("username");
      final String accessKeyId = (String) credentials.get("access_key");
      final String secretAccessKey = (String) credentials.get("secret_access_key");
      final String bucket = (String) credentials.get("bucket");
      final String region = (String) credentials.get("region");

      return new S3ServiceInfo(id, username, accessKeyId, secretAccessKey, bucket, region);
   }

}
