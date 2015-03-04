package org.hackillinois.data.api;

import java.util.List;

import org.hackillinois.data.model.Event;
import org.hackillinois.data.model.Login;
import org.hackillinois.data.model.Map;
import org.hackillinois.data.model.News;
import org.hackillinois.data.model.Sponsor;
import org.hackillinois.data.model.User;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Stephen on 1/26/2015.
 */
public interface HackIllinoisApi {

    @POST("/login")
    public Observable<User> login(@Body Login login);

    @GET("/schedule")
    public Observable<List<Event>> schedule();

    @GET("/news")
    public Observable<List<News>> news();

    @GET("/getsponsors")
    public Observable<List<Sponsor>> sponsors();

    @GET("/getmaps")
    public Observable<List<Map>> maps();

}
