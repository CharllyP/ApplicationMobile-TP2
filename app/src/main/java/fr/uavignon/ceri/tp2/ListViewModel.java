package fr.uavignon.ceri.tp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.uavignon.ceri.tp2.data.Book;

public class ListViewModel extends AndroidViewModel {

    private BookRepository repo;
    private LiveData<List<Book>> books;

    public ListViewModel(@NonNull Application app)
    {
        super(app);
        repo = new BookRepository(app);
        books = repo.getAllBooks();
    }

    LiveData<List<Book>> getBooks()
    {
        return books;
    }
}

