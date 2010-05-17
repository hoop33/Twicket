package com.availity.twicket;

import java.io.Serializable;
import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;

public class PublicTweetRetriever implements TweetRetriever, Serializable {
  private static final long serialVersionUID = 1316915450820608922L;

  public List<Status> retrieveTweets() {
    return new Twitter().getPublicTimeline();
  }
}
