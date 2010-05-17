package com.availity.twicket;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import twitter4j.Tweet;

public class TweetListPanel extends Panel {
  private static final long serialVersionUID = -4056830780523403665L;

  public TweetListPanel(String id, List<Tweet> tweetList) {
    super(id);
    add(new TweetListView("tweetListView", tweetList));
  }

  private class TweetListView extends ListView {
    private static final long serialVersionUID = -8745103599338918600L;

    private TweetListView(String id, List<Tweet> tweetList) {
      super(id, tweetList);
    }

    @Override
    protected void populateItem(final ListItem listItem) {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      Tweet tweet = (Tweet) listItem.getModelObject();
      listItem.add(new ExternalImageUrl("image_url", tweet.getProfileImageUrl()));
      listItem.add(new Label("date", sdf.format(tweet.getCreatedAt())));
      listItem.add(new ExternalLink("user_link", "http://twitter.com/" + tweet.getFromUser(), tweet.getFromUser()));
      listItem.add(new MultiLineLabel("text", tweet.getText()));
    }
  }
}
