package com.example.ankit.myproject;

/**
 * Created by Ankit on 15-05-2015.
 */
public class Message {
    private String id,type,data,timestamp;

    public void setId(String id)
    {
        this.id = id;
    }
    public String getId()
    {
        return this.id;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getType()
    {
        return this.type;
    }
    public void setData(String data)
    {
        this.data = data;
    }
    public String getData()
    {
        return this.data;
    }
    public void setTimestamp(String ts)
    {
        this.timestamp  = ts;
    }
    public String getTimestamp()
    {
        return this.timestamp;
    }

}