// IAidlTagInterface.aidl
package com.tencent.multiprocess.tag;
import com.tencent.multiprocess.tag.BookInfo;

// Declare any non-default types here with import statements

interface IAidlTagInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    BookInfo addBookIn(in BookInfo book);
    BookInfo addBookOut(out BookInfo book);
    BookInfo addBookInOut(inout BookInfo book);
    BookInfo getBookInfo();
}
