package com.availity.twicket;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;

public class SearchTweetRetriever implements TweetRetriever, Serializable {
  private static final long serialVersionUID = -7238466050788320212L;
  private SearchParams params;
  
  public SearchTweetRetriever(final SearchParams params) {
    this.params = params;
  }
  
  public List<Status> retrieveTweets() {
    if (params == null) {
      return Collections.emptyList();
    }
    return new Twitter().search(params.searchString);
  }
}
