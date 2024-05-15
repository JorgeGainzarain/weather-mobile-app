package es.usj.individualassessment.Classes;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Day {
    private String sunrise;
    private String sunset;
    private Date date;
    private List<Hour> hours;

    public Day(JSONObject jsonObject) throws JSONException, ParseException {

        hours = new ArrayList<>();

        this.sunrise = jsonObject.getString("sunrise");
        this.sunset = jsonObject.getString("sunset");

        JSONArray jsonHours = jsonObject.getJSONArray("hours");
        for (int i = 0; i < jsonHours.length() -1; i++) {
            hours.add(new Hour(jsonHours.getJSONObject(i)));
        }

        String dateString = jsonObject.getString("datetime");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);

        assert date != null;
        this.date = date;

    }

    public JSONObject toJSON() throws JSONException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        JSONObject jsonObject = new JSONObject()
                .put("sunrise", sunrise)
                .put("sunset", sunset)
                .put("datetime", dateString);

        JSONArray jsonHours = new JSONArray();
        for (Hour hour : hours) {
            jsonHours.put(hour.toJSON());
        }

        jsonObject.put("hours", jsonHours);

        return jsonObject;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
