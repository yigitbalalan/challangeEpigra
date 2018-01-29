package comq.example.yigit.challaneepigra.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by yigit on 23.01.2018.
 */

public class Serializers implements Parcelable {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    protected Serializers(Parcel in) {
        userId = in.readByte() == 0x00 ? null : in.readInt();
        id = in.readByte() == 0x00 ? null : in.readInt();
        title = in.readString();
        body = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (userId == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(userId);
        }
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(title);
        dest.writeString(body);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Serializers> CREATOR = new Parcelable.Creator<Serializers>() {
        @Override
        public Serializers createFromParcel(Parcel in) {
            return new Serializers(in);
        }

        @Override
        public Serializers[] newArray(int size) {
            return new Serializers[size];
        }
    };
}