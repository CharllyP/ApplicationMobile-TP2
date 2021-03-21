package fr.uavignon.ceri.tp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import fr.uavignon.ceri.tp2.data.Book;

public class DetailFragment extends Fragment {

    private EditText textTitle, textAuthors, textYear, textGenres, textPublisher;
    public DetailViewModel viewModel;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        // Get selected book
        DetailFragmentArgs args = DetailFragmentArgs.fromBundle(getArguments());
        viewModel.setSelectedBook((int)args.getBookNum());


        textTitle = (EditText) view.findViewById(R.id.nameBook);
        textAuthors = (EditText) view.findViewById(R.id.editAuthors);
        textYear = (EditText) view.findViewById(R.id.editYear);
        textGenres = (EditText) view.findViewById(R.id.editGenres);
        textPublisher = (EditText) view.findViewById(R.id.editPublisher);

        viewModel.getBook().observe(getViewLifecycleOwner(),
            new Observer<Book>()
            {
                @Override
                public void onChanged(Book book)
                {
                    textTitle.setText(book.getTitle());
                    textAuthors.setText(book.getAuthors());
                    textYear.setText(book.getYear());
                    textGenres.setText(book.getGenres());
                    textPublisher.setText(book.getPublisher());
                }
            }
        );


        view.findViewById(R.id.buttonBack).setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    NavHostFragment.findNavController(fr.uavignon.ceri.tp2.DetailFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }
            }
        );

        view.findViewById(R.id.buttonUpdate).setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    String title = textTitle.getText().toString();
                    String authors = textAuthors.getText().toString();
                    String year = textYear.getText().toString();
                    String genres = textGenres.getText().toString();
                    String publisher = textPublisher.getText().toString();

                    DetailFragmentArgs args = DetailFragmentArgs.fromBundle(getArguments());
                    int id = (int)args.getBookNum();
                    Book updatedBook = new Book(title, authors, year, genres, publisher);
                    updatedBook.setId(id);
                    viewModel.updateBook(updatedBook);
                }
            }
        );
    }
}
