package com.availity.twicket;

import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;

public class PublicTweetRetriever implements TweetRetriever {

  public List<Status> retrieveTweets() {
    return new Twitter().getPublicTimeline();
  }
}
