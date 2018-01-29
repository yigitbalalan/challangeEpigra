package comq.example.yigit.challaneepigra.Network;

import java.util.List;

import comq.example.yigit.challaneepigra.data.model.Serializers;
import comq.example.yigit.challaneepigra.data.model.photosResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yigit on 23.01.2018.
 */

public interface ApiService {
    @GET("posts/")
    Call<List<Serializers>>getAnswers();

    @GET("photos")
    Call<List<photosResult>>getPhotos(@Query("albumId")int id );

    //@Query("albumId") String query)



}
