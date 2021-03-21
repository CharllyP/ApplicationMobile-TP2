package fr.uavignon.ceri.tp2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.uavignon.ceri.tp2.data.Book;

public class RecyclerAdapter extends RecyclerView.Adapter<fr.uavignon.ceri.tp2.RecyclerAdapter.ViewHolder> {

    private static final String TAG = fr.uavignon.ceri.tp2.RecyclerAdapter.class.getSimpleName();
    private List<Book> bookList;

    public void setBookList(List<Book> books)
    {
        bookList = books;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(bookList.get(i).getTitle());
        viewHolder.itemDetail.setText(bookList.get(i).getAuthors());
    }

    @Override
    public int getItemCount() {
        if(bookList != null)
        {
            return bookList.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemDetail;

        ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v)
                {
                    long id = RecyclerAdapter.this.bookList.get((int)getAdapterPosition()).getId();
                    /* Snackbar.make(v, "Click detected on chapter " + (position+1),
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                     */
                    ListFragmentDirections.ActionFirstFragmentToSecondFragment action = ListFragmentDirections.actionFirstFragmentToSecondFragment();
                    action.setBookNum(id);
                    Navigation.findNavController(v).navigate(action);

                }
            });
        }
    }

}