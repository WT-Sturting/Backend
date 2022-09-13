package wt.project.sturting.controller.dto.response;

import lombok.Data;
import wt.project.sturting.entity.Location;

@Data
public class LocationResponseDto {
	private String address;
	private Float latitude;
	private Float longitude;

	public LocationResponseDto(Location location) {
		this.address = location.getAddress();
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
	}
}
