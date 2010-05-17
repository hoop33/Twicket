package com.availity.twicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;

  public HomePage(final TweetRetriever tweetRetriever) {
    add(new BookmarkablePageLink<Void>("publicTimeline", PublicTimelinePage.class));
    add(new FeedbackPanel("feedback"));
    add(new SearchPanel("searchPanel"));
    add(new TweetListPanel("tweetListPanel", tweetRetriever));
  }

  public HomePage() {
    this(null);
  }
}
