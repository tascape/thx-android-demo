/*
 * Copyright 2015 tascape.
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

import com.tascape.qa.th.android.driver.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class Clock extends App {
    private static final Logger LOG = LoggerFactory.getLogger(Clock.class);

    public static final String PACKAGE_NAME = "com.android.deskclock";

    public static final String NAME = "Clock";

    @Override
    public String getPackageName() {
        return PACKAGE_NAME;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void reset() throws Exception {
        LOG.debug("na");
    }

    @Override
    public int getLaunchDelayMillis() {
        return 2000;
    }
}
