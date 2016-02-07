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

import com.tascape.qa.android.demo.driver.FDroid;
import com.tascape.qa.android.demo.driver.Settings;
import com.tascape.qa.th.android.driver.UiAutomatorDevice;
import org.junit.Test;
import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.test.AbstractTest;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.tascape.qa.android.demo.driver.Settings.UPDATE_INTERVALS;

/**
 *
 * @author linsong wang
 */
public class FDroidSettingsTests extends AbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(FDroidSettingsTests.class);

    public static final TestDriver MOBILE_DEVICE = new TestDriver(FDroidSettingsTests.class, UiAutomatorDevice.class);

    public static final TestDriver MOBILE_APP = new TestDriver(FDroidSettingsTests.class, FDroid.class);

    private final UiAutomatorDevice device;

    private final FDroid app;

    private Settings settings;

    public FDroidSettingsTests() {
        this.globalTimeout = new Timeout(5, TimeUnit.MINUTES);
        this.device = super.getEntityDriver(MOBILE_DEVICE);
        this.app = super.getEntityDriver(MOBILE_APP);
    }

    @Before
    public void setup() throws Exception {
        device.backToHome();
        app.launch();
        device.takeDeviceScreenshot();
        this.settings = app.openSettings();
    }

    @After
    public void cleanup() throws Exception {
        device.takeDeviceScreenshot();
    }

    @Test
    public void testAutoUpdateInterval() throws Exception {
        for (Map.Entry<String, String> intervalEntry : UPDATE_INTERVALS.entrySet()) {
            String interval = intervalEntry.getKey();
            LOG.info("set auto update interval to '{}'", interval);
            settings.setAutoUpdateInterval(interval);
            device.takeDeviceScreenshot();
            Assert.assertEquals("Update interval is not updated,", intervalEntry.getValue(),
                settings.getAUtoUpdateInterval());
        }
    }

    @Override
    public String getApplicationUnderTest() {
        return app.getName();
    }
}
