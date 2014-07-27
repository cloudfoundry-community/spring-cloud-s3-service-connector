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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.cloudfoundry.TestCloudFoundryConnector;
import org.springframework.cloud.util.EnvironmentAccessor;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

/**
 * @author David Ehringer
 */
public class S3ServiceInfoCreatorTest {

    private TestCloudFoundryConnector cloudConnector = new TestCloudFoundryConnector();

    private CloudFactory cloudFactory;
    @Mock
    private EnvironmentAccessor environment;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
        cloudConnector.setCloudEnvironment(environment);

        cloudFactory = new CloudFactory();
        cloudFactory.registerCloudConnector(cloudConnector);

        when(environment.getEnvValue("VCAP_APPLICATION")).thenReturn(fileAsString("vcap_application.json"));
    }

    @Test
    public void s3ServicesAreAvailableThroughS3ServiceInfo() throws IOException {
        when(environment.getEnvValue("VCAP_SERVICES")).thenReturn(fileAsString("s3-service-info.json"));

        Cloud cloud = cloudFactory.getCloud();

        assertThat(cloud.getServiceInfos().size(), is(1));
        S3ServiceInfo serviceInfo = (S3ServiceInfo) cloud.getServiceInfo("my-s3-service");
        assertThat(serviceInfo.getId(), is("my-s3-service"));
        assertThat(serviceInfo.getAccessKeyId(), is("my-access-key-id"));
        assertThat(serviceInfo.getSecretAccessKey(), is("my-secret-key"));
        assertThat(serviceInfo.getUsername(), is("cloud-foundry-s3-c5271ba4-6d2f-4163-843c-6a5fdceb7a1a"));
        assertThat(serviceInfo.getBucket(), is("cloud-foundry-2eac2d52-bfc9-4d0f-af28-c02187689d72"));
    }

    private String fileAsString(String fileName) throws IOException {
        URL url = Resources.getResource(fileName);
        return Resources.toString(url, Charsets.UTF_8);
    }

}
