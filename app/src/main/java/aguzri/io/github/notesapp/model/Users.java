package aguzri.io.github.notesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    @Expose
    @SerializedName("id") private String id;
    @Expose
    @SerializedName("email") private String email;
    @Expose
    @SerializedName("password") private String password;
    @Expose
    @SerializedName("name") private String name;
    @Expose
    @SerializedName("created") private String created;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
