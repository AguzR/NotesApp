package aguzri.io.github.notesapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import aguzri.io.github.notesapp.R;
import aguzri.io.github.notesapp.model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.RecyclerViewAdapter>{

    private Context context;
    private List<Note> notes;
    private ItemClickListener itemClickListener;

    public NoteAdapter(Context context, List<Note> notes, ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Note note = notes.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        holder.cardView.setCardBackgroundColor(note.getColor());

        // ambil data date created
        String DateTimC = note.getCreated();

        // split date and time
        String[] datetimc = DateTimC.split("\\s");

        // result split date and time
        String datec = datetimc[0];
        String timec = datetimc[1];

        // split date with year, month and day
        String[] DateC = datec.split("\\-");

        // result split date
        String yearC = DateC[0];
        String monthC = DateC[1];
        String dayC = DateC[2];

        // split time with hour, minute and second
        String TimeC[] = timec.split("\\:");

        // result split time
        String hourC = TimeC[0];
        String minuteC = TimeC[1];
        String secondC = TimeC[2];

        if (monthC.equals("01")) {
            monthC = "Jan";
        } else if (monthC.equals("02")) {
            monthC = "Feb";
        } else if (monthC.equals("03")) {
            monthC = "Mar";
        } else if (monthC.equals("04")) {
            monthC = "Apr";
        } else if (monthC.equals("05")) {
            monthC = "Mei";
        } else if (monthC.equals("06")) {
            monthC = "Jun";
        } else if (monthC.equals("07")) {
            monthC = "Jul";
        } else if (monthC.equals("08")) {
            monthC = "Agu";
        } else if (monthC.equals("09")) {
            monthC = "Sep";
        } else if (monthC.equals("10")) {
            monthC = "Okt";
        } else if (monthC.equals("11")) {
            monthC = "Nov";
        } else if (monthC.equals("12")) {
            monthC = "Des";
        }

        holder.tv_date.setText("Dibuat : " + dayC + " " + monthC + " " + yearC + " pukul " + hourC + ":" + minuteC);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_title, tv_note, tv_date;
        CardView cardView;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.title);
            tv_note = itemView.findViewById(R.id.note);
            tv_date = itemView.findViewById(R.id.date);
            cardView = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
