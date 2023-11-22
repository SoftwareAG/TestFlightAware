package com.wm.adapter.WmFlightAwareAdapter.connection;

public class FlightObject {
	
    //define here all fields of interest from the received messages
    public String type;
    public String ident;
    public String dest;
    public String air_ground;
    public String alt;
    public String clock;
    public String id;
    public String gs;
    public String heading;
    public String lat;
    public String lon;
    public String reg;
    public String squawk;
    public String updateType;

    @Override
    public String toString() {
        String result;
        //if any field is missing in the received message,
        //for eg if "squawk" is missing then squawk value will be null!
        //format as a table left justified, 10 chars min width
        result = String.format("%-10s %-10s\n %-10s %-10s\n %-10s %-10s\n "
                + "%-10s %-10s\n %-10s %-10s\n %-10s %-10s\n "
                + "%-10s %-10s\n %-10s %-10s\n %-10s %-10s\n "
                + "%-10s %-10s\n %-10s %-10s\n %-10s %-10s\n "
                + "%-10s %-10s\n",
                "type", type,
                "ident", ident,
                "dest", dest,
                "airground", air_ground,
                "alt", alt,
                "clock", clock,
                "id", id,
                "gs", gs,
                "heading", heading,
                "lat", lat,
                "lon", lon,
                "reg", reg,
                "squawk", squawk,
                "updateType", updateType
        );
        return result;
    }
}
