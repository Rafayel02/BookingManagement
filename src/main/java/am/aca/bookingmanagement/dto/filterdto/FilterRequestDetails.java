package am.aca.bookingmanagement.dto.filterdto;

import am.aca.bookingmanagement.entity.Activity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FilterRequestDetails {

    private List<String> category;

    private List<String> activity;

    // latitude longitude distance
    private List<Double> location;

   // private Double latitude;

  //  private Double longitude;

   // private Integer distance;

}
