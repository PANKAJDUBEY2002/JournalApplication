package com.application.journalApplication.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse{

        private Current current;
        @Setter
        @Getter
        public class Current{
            @JsonProperty("temp_c")
            private int tempc;



        }



    }



