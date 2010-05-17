package com.availity.twicket;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Homepage
 */
public class HomePage extends WebPage {

  private static final long serialVersionUID = 1L;

  // TODO Add any page properties or variables here
  /**
   * Constructor that is invoked when page is invoked without a session.
   *
   * @param parameters
   *            Page parameters
   */
  public HomePage(final List<Tweet> tweetList) {
//    String[] searchStrings = (String[]) pageParameters.get("search");
//    String search = searchStrings == null || searchStrings.length == 0 ? null : searchStrings[0];
//    search = search == null ? "" : search.trim();
//    List<Tweet> tweetList = null;
//    if (search.length() > 0) {
//      tweetList = getTweetList(search);
//    }
    add(new FeedbackPanel("feedback"));
    add(new SearchPanel("searchPanel"));
    add(new TweetListPanel("tweetListPanel", tweetList));
  }
  
  public HomePage() {
    this(null);
  }  

  private List<Tweet> getTweetList(String search) {
    Twitter twitter = new TwitterFactory().getInstance();
    Query query = new Query(search);
    try {
      QueryResult result = twitter.search(query);
      return result.getTweets();
    } catch (TwitterException e) {
      error("Error searching Twitter: " + e.getMessage());
    }
    return null;
  }
}
