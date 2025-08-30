package com.simple.Simple.dto;

import com.simple.Simple.model.BaseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseClass {

    private Long id;

    private String name;

    private String city;

    private Double marks;

    private String imageName;

    private String imageData;  // because spring will auto convert byte[] to base64
}
