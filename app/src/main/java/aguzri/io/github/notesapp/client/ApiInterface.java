package aguzri.io.github.notesapp.client;

import java.util.List;

import aguzri.io.github.notesapp.model.Note;
import aguzri.io.github.notesapp.model.Value;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/create.php")
    Call<Note> createNote(
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @GET("api/read.php")
    Call<List<Note>> getNotes();

    @FormUrlEncoded
    @POST("api/update.php")
    Call<Note> updateNote(
            @Field("id") int id,
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @FormUrlEncoded
    @POST("api/delete.php")
    Call<Note> deleteNote(
            @Field("id") int id
    );
}
