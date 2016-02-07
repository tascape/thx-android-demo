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

import com.tascape.qa.th.android.driver.UiAutomatorDevice;
import com.tascape.qa.th.data.TestDataProvider;
import com.tascape.qa.th.data.TestIterationData;
import org.junit.Test;
import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.test.AbstractTest;
import java.awt.Dimension;
import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class DeviceTests extends AbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceTests.class);

    public static final TestDriver MOBILE_DEVICE = new TestDriver(DeviceTests.class, UiAutomatorDevice.class);

    private final UiAutomatorDevice device;

    public DeviceTests() {
        this.globalTimeout = new Timeout(5, TimeUnit.MINUTES);
        this.device = super.getEntityDriver(MOBILE_DEVICE);
    }

    @Before
    public void setup() throws Exception {
        device.backToHome();
        device.takeDeviceScreenshot();
    }

    @After
    public void cleanup() throws Exception {
        device.takeDeviceScreenshot();
    }

    @Test
    @TestDataProvider(klass = TestIterationData.class, method = "useIterations", parameter = "2")
    public void testScreenRecording() throws Exception {
        int seconds = 10;
        String mp4 = device.recordScreen(seconds, 512000);

        LOG.info("randomly touch screen for {} seconds", seconds);
        Dimension rect = device.getScreenDimension();
        Random r = new Random();
        Thread t = new Thread(Thread.currentThread().getName() + "-random-click") {
            @Override
            public void run() {
                long end = System.currentTimeMillis() + seconds * 1000 + 2000;
                while (System.currentTimeMillis() < end) {
                    int x = r.nextInt(rect.width);
                    int y = r.nextInt(rect.height);
                    device.click(x, y);
                }
            }
        };
        t.start();
        t.join();
        LOG.info("done recording");

        File f = device.getScreenRecord(mp4);
        File file = this.createDataFile("screen-recording", "mp4");
        file.delete();
        f.renameTo(file);
        LOG.info("screen recording file {}", file.getAbsolutePath());
    }

    @Override
    public String getApplicationUnderTest() {
        return "Android";
    }
}
