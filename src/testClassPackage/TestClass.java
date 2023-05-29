package testClassPackage;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import org.testng.annotations.Test;

import SimilarFunctionPackage.API_Common_Function;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import requestRepo.get_Req_Repository;

public class TestClass
{

	@Test
	public static void execute() throws IOException 
	{
		String baseURI= get_Req_Repository.baseURI();
		String resource =get_Req_Repository.resource();	
		int statusCode=API_Common_Function.statusCode(baseURI, resource);
		String responseBody=API_Common_Function.resource(baseURI, resource);
		int id[]= {7,8,10};
		String title[]= {"Samsung Galaxy Book","Microsoft Surface Laptop 4","HP Pavilion 15-DK1056WM"};
		JsonPath jsp=new JsonPath(responseBody);
		int count=jsp.getList("products").size();
		for(int i=0;i<count;i++)
		{
			int exp_id=id[i];
			String exp_title=title[i];
			
			int res_id=jsp.getInt("products["+i+"].id");
			String res_title=jsp.getString("products["+i+"].title");
	     	
			Assert.assertEquals(statusCode, 200);
			Assert.assertEquals(res_id, exp_id);
			Assert.assertEquals(res_title,exp_title);

		}
		
		System.out.println("display the get validation result:"+responseBody);
	}

}


