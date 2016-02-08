#!/bin/bash

echo 'run "mvn clean package" if needed'

java -cp target/*:target/th/*:target/dependency/* \
  -Dqa.th.comm.ADB_EXECUTABLE=/usr/local/bin/adb
com.tascape.qa.android.demo.suite.SmokeSuite
