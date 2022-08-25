package org.petdata;

public class PetDetails {
public static String petdata(int id,String name,String names) {
	return"{\r\n"
			+ "  \"id\": "+id+",\r\n"
			+ "  \"category\": {\r\n"
			+ "    \"id\": 19,\r\n"
			+ "    \"name\": \"Dog\"\r\n"
			+ "  },\r\n"
			+ "  \"name\": \""+name+"\",\r\n"
			+ "  \"photoUrls\": [\r\n"
			+ "    \"string\"\r\n"
			+ "  ],\r\n"
			+ "  \"tags\": [\r\n"
			+ "    {\r\n"
			+ "      \"id\": 5,\r\n"
			+ "      \"name\": \"kombai\"\r\n"
			+ "    },\r\n"
			+ "     {\r\n"
			+ "      \"id\": 6,\r\n"
			+ "      \"name\": \"rajapalayam\"\r\n"
			+ "    },\r\n"
			+ "     {\r\n"
			+ "      \"id\": 7,\r\n"
			+ "      \"name\": \"rottweiler\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"status\": \""+names+"\"\r\n"
			+ "}";
}
}
