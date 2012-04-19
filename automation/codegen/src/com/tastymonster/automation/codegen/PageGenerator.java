package com.tastymonster.automation.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.util.StringUtils;
import org.testng.log4testng.Logger;

import com.tastymonster.automation.codegen.PageInfo;


public class PageGenerator {

	//TODO These paths are hard-coded for the moment 
	private static final String AUTOMATION_PATH = "automation/src/com/tastymonster/automation/";
	private static final String DERIVED_PAGE_PATH = AUTOMATION_PATH + "page/base/";
	private static final String TEMPLATE_PATH = "WEB-INF/templates/";
	private static final String CODEGEN_TEMPLATE_PATH = "automation/codegen/src/com/tastymonster/automation/codegen/templates/";
	private static final String BASE_PAGE_PATH = "automation/generated/src/com/tastymonster/automation/page/base/";

	private Logger log = Logger.getLogger( this.getClass() );
	
	private List<File> files;
	private List<PageInfo> pages;
	
	/**
	 * This is the engine that puts it all together
	 */
	public void generatePages() {
		//Phase 1 - initialize file set
		
		//Initialize the files to be processed by the generator
		log.warn( "Populating list of files to be parsed" );
		initFiles();
		
		//Phase 2 - parse files into PageInfo objects
		
		//Get the complete list of the presentation layer files we want to parse
		//This is just the list of files--this is where it would loop through xwork definitions
		// and other means of putting together the various templates
		log.warn( "Parsing pages to initialize PageInfo objects" );
		initPageInfo();
		
		
		//Phase 3 - Generate the classes
		
		//Generate the pages. Each presentation layer page has been put into a set, and now we use that
		// to populate new Java classes according to vm templates
		log.warn( "Generating pages" );
		for ( PageInfo page: getPages() ) {
			log.warn( "Generating page: " + page.getPageName() );
			generateBasePageFile( page );
			generateDerivedPageFile( page );
		}
	}

	//****************************************************************
	// Phase 1 - initialize the presentation layer file set
	//****************************************************************
	
	/**
	 * Initializes a list of all presentation layer files to be parsed. Each entry contains the path to the file 
	 * as well as the file name. For example:
	 */
	protected void initFiles() {
		//TODO this needs to be abstracted
		List<File> files = new ArrayList<File>();
		this.addFile( files, TEMPLATE_PATH + "index.vm" );
		this.addFile( files, TEMPLATE_PATH + "footer.vm" );
		this.addFile( files, TEMPLATE_PATH + "login.vm" );
		this.addFile( files, TEMPLATE_PATH + "createUser.vm" );
		this.addFile( files, TEMPLATE_PATH + "landing.vm" );
		this.addFile( files, TEMPLATE_PATH + "login.vm" );
		this.addFile( files, TEMPLATE_PATH + "addPatent.vm" );
		this.files = files;
	}

	/**
	 * Add a file to the list of files--this allows for checks to be made on the given file before it is added
	 * @param files
	 * @param path
	 */
	protected void addFile( List<File> files, String path ) {
		File file = new File( path );
		
		//Make sure the file is readable (and perform other checks if necessary
		if ( file.canRead() ) {
			files.add( file );
		}
	}

	/**
	 * Returns a list of all the files to be parsed
	 */
	protected List<File> getFiles() {
		return this.files;
	}
	
	/**
	 * Injector for the files field
	 * @param files
	 */
	public void setFiles( List<File> files ) {
		this.files = files;
	}
	
	//****************************************************************
	// Phase 2 - Parse the presentation layer and build the set of PageInfo objects
	//****************************************************************

	/**
	 * The list of PageInfo objects--metadata about the pages to be generated
	 * @return
	 */
	public List<PageInfo> getPages() {
		return this.pages;
	}

	public void setPages( List<PageInfo> pages ) {
		this.pages = pages;
	}

	/**
	 * Gathers information about all the pages, initializes the "pages" field with the List<PageInfo>
	 * @return
	 */
	protected void initPageInfo() {
		List<File> files = getFiles();
		List<PageInfo> pages = new ArrayList<PageInfo>();
		for ( File file: files ) {
			PageInfo pageInfo = getPageInfo( file );
			pages.add( pageInfo );
		}
		this.pages = pages;
	}

	/**
	 * Given a file (i.e. presentation template) to parse, this returns a PageInfo object
	 * @param file
	 * @return
	 */
	protected PageInfo getPageInfo( File file ) {
		PageInfo page = new PageInfo();
		page.initPage( file );
		return page;
	}
	
	//****************************************************************
	// Phase 3 - From the PageInfo set, generate the base page classes
	//****************************************************************

	/**
	 * Takes one PageInfo object, builds a context around which that file can be generated, and creates the file with its
	 * appropriate content
	 * @param page
	 */
	protected void generateBasePageFile( PageInfo page ) {
		//The context holds all the info about the fields AND the surrounding environment--the package, the imports, and
		// even some helper objects 
		Map<String, Object> pageContext = new LinkedHashMap<String, Object>();
		String pageName = getBasePageClassName( page );
		pageContext.put( "pageName", pageName );
		pageContext.put( "package", page.getPackage() );
		pageContext.put( "parentPage", "AbstractAutomationPage" );
		pageContext.put( "pageURI", page.getPageURI() );
		
		pageContext.put( "fields", page.getFields() );
		
		try {
			String pagePath = getBasePagePath( page );
			log.debug( String.format( "Storing page [%s] to path [%s]", pageName, pagePath ) );
			generateFile( pageContext, CODEGEN_TEMPLATE_PATH + "BasePageTemplate.vm", pagePath );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	private void generateDerivedPageFile( PageInfo page ) {
		Map<String, Object> pageContext = new LinkedHashMap<String, Object>();
		String pageName = getDerivedPageClassName( page );
		pageContext.put( "pageName", pageName );
		pageContext.put( "package", page.getPackage() );
		pageContext.put( "parentPage", getBasePageClassName( page ) );

		try {
			String pagePath = getDerivedPagePath( page );
			log.debug( String.format( "Storing page [%s] to path [%s]", pageName, pagePath ) );
			generateFile( pageContext, CODEGEN_TEMPLATE_PATH + "DerivedPageTemplate.vm", pagePath );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	protected String getBasePagePath( PageInfo page ) {
		return BASE_PAGE_PATH + getBasePageClassName( page ) + ".java";
	}
	
	protected String getDerivedPagePath( PageInfo page ) {
		return DERIVED_PAGE_PATH + getDerivedPageClassName( page ) + ".java";
	}
	
	protected String getBasePageClassName( PageInfo page ) {
		return "Base" + getDerivedPageClassName( page );
	}

	protected String getDerivedPageClassName( PageInfo page ) {
		return getPageClassName( page );
	}
	
	private String getPageClassName( PageInfo page ) {
		return StringUtils.capitalizeFirstLetter( page.getPageName() ) + "Page";
	}

	/**
	 * Generates a single file, based on the Map<String, Object> and template it is given
	 * @param context
	 * @param templateFile
	 * @param pagePath
	 * @throws Exception
	 */
    protected void generateFile( Map<String, Object> context, String templateFile, String pagePath ) throws Exception {
        FileWriter writer = null;
        try {
        	//Create the File object and necessary paths for the generated object
        	File pageFile = new File( pagePath );
        	pageFile.getParentFile().mkdirs();
            writer = new FileWriter( pagePath );
            
            //Merge the template to the new file using the newly generated page file
            mergeVMTemplate( context, templateFile, writer );
        }
        finally {
            writer.close();
        }
    }

    /**
     * Merges the Velocity template with the given Map
     * @param context
     * @param templateFilename
     * @param writer - can be any kind of writer, including StringWriter (useful for testing)
     * @throws Exception 
     * @throws MethodInvocationException 
     * @throws ParseErrorException 
     * @throws ResourceNotFoundException 
     */
    public void mergeVMTemplate( Map<String, Object> context, String templateFilename, Writer writer ) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
        Velocity.mergeTemplate( templateFilename, Velocity.ENCODING_DEFAULT, new VelocityContext( context ), writer );
    }
}
