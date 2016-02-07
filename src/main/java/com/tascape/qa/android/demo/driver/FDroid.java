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
package com.tascape.qa.android.demo.driver;

import com.android.uiautomator.stub.UiSelector;
import com.tascape.qa.th.android.driver.App;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class FDroid extends App {
    private static final Logger LOG = LoggerFactory.getLogger(FDroid.class);

    public static final String PACKAGE_NAME = "org.fdroid.fdroid";

    public static final String NAME = "F-Droid";

    @Override
    public String getPackageName() {
        return PACKAGE_NAME;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Settings openSettings() throws IOException {
        this.uiObject.useUiObjectSelector(new UiSelector().resourceId("org.fdroid.fdroid:id/action_bar"));
        this.uiObject.selectChild(new UiSelector().index(1));
        this.uiObject.selectChild(new UiSelector().index(2));
        this.uiObject.click();
        this.uiaDevice.takeDeviceScreenshot();

        this.uiCollection.useUiCollectionSelector(new UiSelector().className("android.widget.ListView"));
        this.uiCollection.selectChild(new UiSelector().index(3));
        this.uiCollection.click();
        this.uiaDevice.takeDeviceScreenshot();
        return new Settings(this);
    }

    @Override
    public void reset() throws Exception {
        LOG.debug("na");
    }

    @Override
    public int getLaunchDelayMillis() {
        return 5000;
    }
}
