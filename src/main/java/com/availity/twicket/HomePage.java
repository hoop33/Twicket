package com.availity.twicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * Homepage
 */
public class HomePage extends WebPage {

  private static final long serialVersionUID = 1L;

  public HomePage(final SearchParams searchParams) {

    add(new FeedbackPanel("feedback"));
    add(new SearchPanel("searchPanel"));
    add(new TweetListPanel("tweetListPanel", searchParams));
  }

  public HomePage() {
    this(null);
  }
}
