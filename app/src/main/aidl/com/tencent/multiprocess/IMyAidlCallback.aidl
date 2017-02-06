// IMyAidlCallback.aidl
package com.tencent.multiprocess;
import com.tencent.multiprocess.Person;

// Declare any non-default types here with import statements

interface IMyAidlCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void getPerson(in Person p);
}
