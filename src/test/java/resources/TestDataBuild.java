package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String address, String language)
	{
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("8800798933");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		List<String> n =new ArrayList<String>();
		n.add("Shoe");
		n.add("Jeans");
		p.setTypes(n);
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.3427362);
		
		p.setLocation(l);
		
		return p;
	}
	
	public String deletePlace(String placeid)
	{
		return "{\"place_id\":\""+placeid+"\"}";
	}
	
	
}
