package com.availity.twicket;

import java.util.List;

import winterwell.jtwitter.Twitter.Status;

public interface TweetRetriever {
  List<Status> retrieveTweets();
}
