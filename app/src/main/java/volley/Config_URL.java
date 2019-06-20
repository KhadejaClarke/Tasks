package volley;

//This class is for storing all URLs as a model of URLs

public class Config_URL {
	private static String base_URL = " http://192.168.31.155:5000/";
	public static String URL_CREATE = base_URL+"todo/api/v1.0/tasks";
	public static String URL_READ = base_URL+"todo/api/v1.0/tasks";
	public static String URL_UPDATE = base_URL+"todo/api/v1.0/tasks/<int:task_id>";
	public static String URL_DELETE = base_URL+"todo/api/v1.0/tasks/<int:task_id>";
}
