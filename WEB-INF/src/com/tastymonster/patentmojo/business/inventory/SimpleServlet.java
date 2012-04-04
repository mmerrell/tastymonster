package com.tastymonster.patentmojo.business.inventory;
import java.util.ArrayList;
import java.util.List;


public class SimpleServlet {
	
	private String message = "User";

	public List<String> getList() {
		List<String> list = new ArrayList<String>();
		list.add( "Hello" );
		list.add( "Goodbye" );
		list.add( "Aloha" );
		return list;
	}
	
	public List<List<String>> get2DList() {
		List<String> list = new ArrayList<String>();
		list.add( "Hello" );
		list.add( "Goodbye" );
		list.add( "Peace" );
		list.add( "Love" );
		list.add( "World" );
		
		List<String> list2 = new ArrayList<String>();
		list2.add( "Bonjour" );
		list2.add( "au revoir" );
		list2.add( "paix" );
		list2.add( "amour" );
		list2.add( "monde" );
		
		List<String> list3 = new ArrayList<String>();
		list3.add( "Guten Tag" );
		list3.add( "Auf Wiedersehen" );
		list3.add( "Frieden" );
		list3.add( "Liebe" );
		list3.add( "Welt" );

		List<List<String>> listOfLists = new ArrayList<List<String>>();
		listOfLists.add( list );
		listOfLists.add( list2 );
		listOfLists.add( list3 );
		return listOfLists;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage( String m ) {
		message = m;
	}

	public List<String> getMainMenuTabs() {
		List<String> mainMenuTabs = new ArrayList<String>();
		mainMenuTabs.add( "homepage" );
		mainMenuTabs.add( "patents" );
		mainMenuTabs.add( "about" );
		mainMenuTabs.add( "blog" );
		mainMenuTabs.add( "contact" );
		return mainMenuTabs;
	}
	
	/** To test exception handling in templates. */
	public boolean whine() {
		throw new IllegalArgumentException();
	}
	
}
