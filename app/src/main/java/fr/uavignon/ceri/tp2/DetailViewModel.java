package fr.uavignon.ceri.tp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import fr.uavignon.ceri.tp2.data.Book;

public class DetailViewModel extends AndroidViewModel {

    private BookRepository repo;
    MutableLiveData<Book> book;

    public void setSelectedBook(long id) {
        repo.getBook(id);
        book = repo.getSearchResults();
    }

    public DetailViewModel(@NonNull Application application)
    {
        super(application);
        repo = new BookRepository(application);
        book = repo.getSearchResults();

    }

    MutableLiveData<Book> getBook()
    {
        return book;
    }

    public void insertOrUpdateBook(Book book)
    {
        if((book != null))
        {
            if((book.getId() != -1))
            {
                repo.updateBook(book);
            }
            else
            {
                book.setId(repo.countAllBooks() + 1);
                repo.insertBook(book);
            }
        }
    }
}

