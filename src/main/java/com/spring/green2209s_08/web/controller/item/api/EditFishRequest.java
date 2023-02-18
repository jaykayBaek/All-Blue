package com.spring.green2209s_08.web.controller.item.api;

import com.spring.green2209s_08.web.domain.enums.FishSex;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EditFishRequest {
    private Long itemId;
    private FishSex fishSex;
    private String breederName;
    private String size;

    public EditFishRequest(Long itemId, String fishSex, String breederName, String size) {
        this.itemId = itemId;

        if(fishSex.equals("MALE")){
            this.fishSex = FishSex.MALE;
        }
        else if(fishSex.equals("FEMALE")){
            this.fishSex = FishSex.FEMALE;
        }
        else{
            this.fishSex = FishSex.UNKNOWN;
        }

        this.breederName = breederName;
        this.size = size;
    }
}
