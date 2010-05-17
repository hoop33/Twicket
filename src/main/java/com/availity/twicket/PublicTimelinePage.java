package com.availity.twicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class PublicTimelinePage extends WebPage {
  public PublicTimelinePage() {
    add(new BookmarkablePageLink<Void>("home", HomePage.class));
    add(new FeedbackPanel("feedback"));
    add(new TweetListPanel("tweetListPanel", new PublicTweetRetriever()));
  }
}
