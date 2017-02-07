// IMyAidlInterface.aidl
package com.tencent.multiprocess.aidlexample;
import com.tencent.multiprocess.aidlexample.Person;
import com.tencent.multiprocess.aidlexample.IMyAidlCallback;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void plus(int a, int b);
    void printPerson(in Person person);
    void setCallback(in IMyAidlCallback callback);
}
