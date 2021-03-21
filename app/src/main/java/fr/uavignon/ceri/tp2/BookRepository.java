package fr.uavignon.ceri.tp2;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import fr.uavignon.ceri.tp2.data.Book;

public class BookRepository {

    final MutableLiveData<Book> selectedBook = new MutableLiveData<>();
    private LiveData<List<Book>> allBook;
    private BookDao bookDao;

    public BookRepository(Application application) {
        BookRoomDatabase db = BookRoomDatabase.getDatabase(application);
        bookDao = db.bookDao();
        allBook = bookDao.getAllBooks();
    }

    public MutableLiveData<Book> getSearchResults()
    {
        return selectedBook;
    }

    public void updateBook(Book book){
        BookRoomDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.updateBook(book);
        });
    }

    public void insertBook(Book book){
        BookRoomDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.insertBook(book);
        });
    }

    public void getBook(long id){
        BookRoomDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.getBook(id);
        });
    }

    public void deleteBook(long id) {
        BookRoomDatabase.databaseWriteExecutor.execute(() -> {
            bookDao.deleteBook(id);
        });
    }

    public LiveData <List<Book>> getAllBooks() {
        return allBook;
    }
}