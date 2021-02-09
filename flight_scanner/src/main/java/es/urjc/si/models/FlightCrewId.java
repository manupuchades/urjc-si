package es.urjc.si.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FlightCrewId implements Serializable{
		
	private Long flightId;
    private Long memberId;

}
