package com.availity.twicket;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.Model;


public class ExternalImageUrl extends WebComponent {
  private static final long serialVersionUID = 2605638499951950339L;

  public ExternalImageUrl(String id, String url) {
    super(id);
    add(new AttributeModifier("src", true, new Model<String>(url)));
    setVisible(!(url == null || url.length() == 0));
  }

  protected void onComponentTag(ComponentTag tag) {
    super.onComponentTag(tag);
    checkComponentTag(tag, "img");
  }
}  
