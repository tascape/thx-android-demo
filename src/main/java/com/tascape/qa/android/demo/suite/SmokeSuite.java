/*
 * Copyright 2015 - 2016 Nebula Bay.
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
package com.tascape.qa.android.demo.suite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tascape.qa.android.demo.driver.FDroid;
import com.tascape.qa.android.demo.test.FDroidSettingsTests;
import com.tascape.qa.android.demo.test.DeviceTests;
import com.tascape.qa.th.android.driver.App;
import com.tascape.qa.th.android.driver.UiAutomatorDevice;
import com.tascape.qa.th.android.suite.UiAutomatorTestSuite;
import com.tascape.qa.th.suite.AbstractSuite;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author linsong wang
 */
public class SmokeSuite extends AbstractSuite implements UiAutomatorTestSuite {
    private static final Logger LOG = LoggerFactory.getLogger(SmokeSuite.class);

    private final FDroid droid = new FDroid();

    private UiAutomatorDevice device;

    @Override
    public void setUpTestClasses() {
        this.addTestClass(FDroidSettingsTests.class);
        this.addTestClass(DeviceTests.class);
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        device = this.getAvailableDevice();
        String apk = SYSCONFIG.getProperty(App.SYSPROP_APK_PATH);
        if (StringUtils.isNotEmpty(apk)) {
            device.uninstall(droid.getPackageName());
            device.install(apk);
        }
        device.install(droid);
        device.takeDeviceScreenshot();

        this.putTestDirver(DeviceTests.MOBILE_DEVICE, device);

        this.putTestDirver(FDroidSettingsTests.MOBILE_DEVICE, device);
        this.putTestDirver(FDroidSettingsTests.MOBILE_APP, droid);
    }

    @Override
    protected void tearDownEnvironment() {
        if (device != null) {
            device.stop();
        }
    }

    @Override
    public String getProductUnderTest() {
        return droid.getName();
    }

    @Override
    public String getProjectName() {
        return "thx-android-demo";
    }

    @Override
    public int getNumberOfEnvs() {
        return this.getNumberOfDevices();
    }
}
