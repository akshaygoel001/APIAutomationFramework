package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace addPayload=new AddPlace();
		Location l=new Location();
		List<String> types=new ArrayList<String>();
		types.add("fashion");types.add("shop");
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		addPayload.setAccuracy(50);
		addPayload.setAddress(address);
		addPayload.setLanguage(language);
		addPayload.setName(name);
		addPayload.setPhone_number("(+91) 983 893 3937");
		addPayload.setTypes(types);
		addPayload.setLocation(l);
		addPayload.setWebsite("http://test123.com");
		
		return addPayload;
	}
	public String deletePlacePayload(String placeId) {
		DeletePlace deletePayload=new DeletePlace();
		deletePayload.setPlace_id(placeId);
		
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n"
				+ "";
		
	}

}
