import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


public class AppServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@GET
	@Produces({MediaType.TEXT_PLAIN, MediaType.TEXT_HTML, MediaType.APPLICATION_JSON}) //MediaType.TEXT_PLAIN )
	public String queryHandler(@Context UriInfo info) {
		String ColumnSequence = new String();

		MultivaluedMap<String, String> query_pairs = info.getQueryParameters();
		String p1 = query_pairs.getFirst("login");
		String p2 = query_pairs.getFirst("sort");
		String p3 = query_pairs.getFirst("limit");

		if(p1==null|p2==null|p3==null)
			return "<html> " + "<title>" + "Hello Jersey" + "</title>"
			+ "<body><h1>" + "Please enter params in the format"
			+ "<p> Usage: query?cols=[server_name,12~ip,0~storage_id,10]&sort=name_desc&limit=20 </p>" + 
			"</body></h1>" + "</html> ";
		else
		{

			//Usage: http://localhost:8080/com.netapp.mockrest/rest/query?cols=[server_name,12~ip,0~storage_id,10]&sort=name_desc&limit=20

			//Request Object, to pass to API/DB
			//RestRequest r = new RestRequest();
			Thread.currentThread().setName("REST-Cache-Thread-"+Calendar.getInstance().getTimeInMillis());

			//parse cols
			String ColumnSequenceOld = p1;
			Pattern pattern = Pattern.compile("\\[(.*?)\\]");
			Matcher matcher = pattern.matcher(p1);
			if(matcher.find()){
				ColumnSequence = matcher.group(1);
				System.out.println(ColumnSequence);
			}

			String[] tokens = ColumnSequence.split("~");
			for (String t : tokens){
				System.out.println(t);
				String[] subtokens = t.trim().split(",");
				//System.out.println(Arrays.toString(subtokens));

				//Check out of bound
				if(subtokens.length<2){
					return "<html> " + "<title>" + "Hello Jersey" + "</title>"
							+ "<body><h2>" + "Error in param: "+subtokens[0]+" ;Please enter params in the following format"
							+ "<p> Usage: query?cols=[user_name,12~pwd,8~email,10]&sort=name_desc&limit=20 </p>" + 
							"</body></h2>" + "</html> ";
				}
				else
					//r.setColumns(subtokens[0], subtokens[1]);
					//call API class/DB
					validateLogin(subtokens[0], subtokens[1]);
					System.out.println();
			}

		}
		return "";
	}
	public void validateLogin(String login,String pwd){
		
	
	}
	
	public void apiCaller(){
		
	}
	
}
