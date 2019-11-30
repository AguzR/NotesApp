package aguzri.io.github.notesapp.client;

import java.util.List;

import aguzri.io.github.notesapp.model.Note;
import aguzri.io.github.notesapp.model.Users;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("notes/create.php")
    Call<Note> createNote(
            @Field("idUsers") String user_id,
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @GET("notes/read.php")
    Call<List<Note>> getNotes(
            @Query("idUsers") String idUsers
    );

    @FormUrlEncoded
    @POST("notes/update.php")
    Call<Note> updateNote(
            @Field("id") int id,
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @FormUrlEncoded
    @POST("notes/delete.php")
    Call<Note> deleteNote(
            @Field("id") int id
    );

    // For Login
    @FormUrlEncoded
    @POST("users/login.php")
    Call<Users> Login(
            @Field("email") String email,
            @Field("password") String password
    );

    // For Regist
    @FormUrlEncoded
    @POST("users/register.php")
    Call<Users> Regist(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );
}
