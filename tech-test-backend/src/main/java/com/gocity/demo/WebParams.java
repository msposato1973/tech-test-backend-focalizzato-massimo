package com.gocity.demo;

public class WebParams {

	public static final String ALL_API = "/";
    public static final String API = "/" + "api";
    
    public static final String ATTRACTION_API = API + ALL_API + "attractions";
    public static final String ATTRACTION_PAGINATION = ALL_API + "attractions/";
    public static final String DESTINATION_API = API + ALL_API + "destinations";
    public static final String ID_API = "/" +"{id}";
    
    public static final String ATTRACTION_ID_API  = ATTRACTION_API + ID_API; 
    public static final String DESTINATION_ID_API = DESTINATION_API + ID_API;
    
    public static final String ATTRACTION_ADD = ALL_API + "add";
    public static final String DESTINATION_ADD = ALL_API + "add";
     
}
