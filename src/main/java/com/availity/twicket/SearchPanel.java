package com.availity.twicket;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.value.ValueMap;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 *
 * @author rwarner
 */
public class SearchPanel extends Panel {
  private static final long serialVersionUID = -8610733111417498232L;

  public SearchPanel(String id) {
    super(id);
    add(new SearchForm("searchForm"));
  }

  private class SearchForm extends StatelessForm {
    private static final long serialVersionUID = -5253297831508495384L;

    private SearchForm(final String id) {
      super(id);
      setModel(new CompoundPropertyModel<ValueMap>(new ValueMap()));
      add(new Label("searchLabel", "Search Terms:"));
      add(new TextField<String>("search").setType(String.class));
    }

    @Override
    protected final void onSubmit() {
      ValueMap values = (ValueMap) getModelObject();
      String search = (String) values.get("search");
      search = search == null ? "" : search.trim();
      if (search.length() > 0) {
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query(search);
        try {
          QueryResult result = twitter.search(query);
          setResponsePage(new HomePage(result.getTweets()));
        } catch (TwitterException e) {
          error("Error searching Twitter: " + e.getMessage());
        }
      }
    }
  }
}  

