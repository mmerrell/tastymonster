package com.tastymonster.patentmojo.business.inventory;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		add( new Label("version", getApplication().getFrameworkSettings().getVersion() ) );
        // TODO Add your page's components here
    }
    
    public void column() {
    	IColumn<?> column = new PropertyColumn<Object>( new Model<String>( "ID" ), "id" );
    }

}
