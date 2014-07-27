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

import org.springframework.cloud.service.BaseServiceInfo;
import org.springframework.cloud.service.ServiceInfo;

/**
 * {@link ServiceInfo} for Amazon S3 services.
 * 
 * @author David Ehringer
 */
public class S3ServiceInfo extends BaseServiceInfo {

    private final String username;
    private final String accessKeyId;
    private final String secretAccessKey;
    private final String bucket;

    public S3ServiceInfo(String id, String username, String accessKeyId, String secretAccessKey, String bucket) {
        super(id);
        this.username = username;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.bucket = bucket;
    }

    public String getUsername() {
        return username;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public String getBucket() {
        return bucket;
    }

}
