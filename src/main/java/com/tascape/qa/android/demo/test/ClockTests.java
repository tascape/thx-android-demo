/*
 * Copyright 2016 tascape.
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
package com.tascape.qa.android.demo.test;

import com.tascape.qa.android.demo.driver.Clock;
import com.tascape.qa.th.android.driver.UiAutomatorDevice;
import com.tascape.qa.th.data.TestDataProvider;
import com.tascape.qa.th.data.TestIterationData;
import org.junit.Test;
import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.test.AbstractTest;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class ClockTests extends AbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(ClockTests.class);

    public static final TestDriver MOBILE_DEVICE = new TestDriver(ClockTests.class, UiAutomatorDevice.class);

    public static final TestDriver MOVIES_APP = new TestDriver(ClockTests.class, Clock.class);

    private final UiAutomatorDevice device;

    private final Clock app;

    public ClockTests() {
        this.globalTimeout = new Timeout(5, TimeUnit.MINUTES);
        this.device = super.getEntityDriver(MOBILE_DEVICE);
        this.app = super.getEntityDriver(MOVIES_APP);
    }

    @Before
    public void setup() throws Exception {
        device.backToHome();
        app.launch();
        device.takeDeviceScreenshot();
    }

    @After
    public void cleanup() throws Exception {
        device.takeDeviceScreenshot();
    }

    @Test
    @TestDataProvider(klass = TestIterationData.class, method = "useIterations", parameter = "9")
    public void testRandomAlarm() throws Exception {
        Assert.fail();
    }

    @Override
    public String getApplicationUnderTest() {
        return app.getName();
    }
}
