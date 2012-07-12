package com.tastymonster.automation.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is all about responsibility. Here's the idea:
 *   a) The IPresentationLayerInfo will build the information about the presentation layer 
 *     (working through whatever mechanism is necessary to build a list of files) 
 *   b) Then, this class will build the list of PageInfo objects by handing off responsibility 
 *     to the IPresentationParser on a file by file basis. The PageInfo object is responsible 
 *     for grouping files together to make a Page 
 *   c) Finally, the Generator is responsible for 
 *     putting the Automation PageObjects together based on whatever template model is specified by
 *     the PageModelFactory (to be implemented later)
 * @author mmerrell
 *
 */
public class PageGenerator {

	private static final String CODEGEN_TEMPLATE_PATH = "automation/codegen/src/com/tastymonster/automation/codegen/templates/";
    private Logger log = LoggerFactory.getLogger( getClass() );
	
	private IPresentationLayerInfo presentationLayer;
	private List<PageInfo> pages;
	
	/**
	 * This is the engine that puts it all together
	 */
	public void generatePages() {
		//Phase 1 - initialize file set
		
		//Initialize the files to be processed by the generator
		log.warn( "Populating list of files to be parsed" );
		initPresentationLayer();
		
		//Phase 2 - transform files into PageInfo objects
		
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
	// Pre-configuration work--get all the paths in order
	//****************************************************************
	/**
	 * Returns the path where the BasePage objects will be stored
	 * @return
	 */
	protected String getBasePagePath() {
		return this.getPresentationLayer().getBasePagePath();
	}

	/**
	 * Returns the path where the DerivedPage objects will be stored
	 * @return
	 */
	protected String getDerivedPagePath() {
		return this.getPresentationLayer().getDerivedPagePath();
	}
	
	/**
	 * Returns the path to the templates on which the generated Page Objects will be based. Right now
	 * this information is still stored in the TastyMonster project, but it can be overridden, and will
	 * be changed to another call when we separate Phase 3 from implementation
	 * @return
	 */
	protected String getCodegenTemplatePath() {
		return CODEGEN_TEMPLATE_PATH;
	}

	//****************************************************************
	// Phase 1 - initialize the presentation layer file set
	//****************************************************************
	private void initPresentationLayer() {
		this.setPresentationLayer( presentationLayer );
	}
	
	/**
	 * Returns the sanitized list of files that make up the presentation layer
	 */
	protected IPresentationLayerInfo getPresentationLayer() {
		return this.presentationLayer;
	}
	
	/**
	 * Injector for the presentationLayer field
	 * @param files
	 */
	public void setPresentationLayer( IPresentationLayerInfo presentationLayer ) {
		this.presentationLayer = presentationLayer;
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
		List<File> files = presentationLayer.getFileList();
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
			generateFile( pageContext, getCodegenTemplatePath() + "BasePageTemplate.vm", pagePath );
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
			generateFile( pageContext, getCodegenTemplatePath() + "DerivedPageTemplate.vm", pagePath );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	protected String getBasePagePath( PageInfo page ) {
		return getBasePagePath() + getBasePageClassName( page ) + ".java";
	}
	
	protected String getDerivedPagePath( PageInfo page ) {
		return getDerivedPagePath() + getDerivedPageClassName( page ) + ".java";
	}
	
	protected String getBasePageClassName( PageInfo page ) {
		return "Base" + getDerivedPageClassName( page );
	}

	protected String getDerivedPageClassName( PageInfo page ) {
		return getPageClassName( page );
	}
	
	private String getPageClassName( PageInfo page ) {
		return StringUtils.capitalize( page.getPageName() ) + "Page";
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
