package br.com.caelum.seleniumdsl.htmlunit;

import br.com.caelum.seleniumdsl.ContentTag;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

class HtmlUnitContentTag implements ContentTag {
    
    private final HtmlPage page;
	private final String id;

	public HtmlUnitContentTag(HtmlPage page, String id) {
		this.page = page;
		this.id = id;
	}

	public boolean contains(String content) {
        return innerHTML().contains(content.trim());
    }

    public boolean exists() {
    	return div() != null;
    }

	private HtmlElement div() {
		return page.getElementById(id);
	}

    public String innerHTML() {
    	if (!exists()) {
			throw new ElementNotFoundException("*", "id", id );
		}
    	StringBuilder html = new StringBuilder();
        for(DomNode child : div().getChildren()) {
			html.append(child.asXml());
		}
		return html.toString().trim();
    }
    
    @Override
	public String toString() {
    	if (!exists()) {
			return "[null]";
		}
		return div().asXml();
	} 

}
