// IBookManager.aidl
package com.yonyou.aidl;
import com.yonyou.aidl.Book;
interface IBookManager {
    List<Book>getBookList();
    void addBook(in Book book);
}