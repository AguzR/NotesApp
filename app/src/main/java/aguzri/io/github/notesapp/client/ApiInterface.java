package aguzri.io.github.notesapp.client;

import aguzri.io.github.notesapp.model.Note;
import aguzri.io.github.notesapp.model.Value;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/create.php")
    Call<Note> createNote(
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );
}
