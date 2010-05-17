package com.availity.twicket;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetListPanel extends Panel {
  private static final long serialVersionUID = -4056830780523403665L;

  public TweetListPanel(String id, SearchParams searchParams) {
    super(id);
    add(new TweetListView("tweetListView", new TweetListLDM(searchParams)));
  }
  
  private class TweetListLDM extends LoadableDetachableModel<List<Tweet>> {
    private static final long serialVersionUID = 1L;
    private SearchParams params;
    
    public TweetListLDM(final SearchParams params) {
      this.params = params;
    }

    @Override
    protected List<Tweet> load() {
      Twitter twitter = new TwitterFactory().getInstance();
      if (params == null) {
        return Collections.emptyList();
      }
      Query query = new Query(params.searchString);
      try {
        QueryResult result = twitter.search(query);
        return result.getTweets();
      } catch (TwitterException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private class TweetListView extends ListView<Tweet> {
    private static final long serialVersionUID = -8745103599338918600L;

    private TweetListView(String id, IModel<List<Tweet>> model) {
      super(id, model);
    }

    @Override
    protected void populateItem(final ListItem<Tweet> listItem) {
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
      Tweet tweet = (Tweet) listItem.getModelObject();
      listItem.add(new ExternalImageUrl("image_url", tweet.getProfileImageUrl()));
      listItem.add(new Label("date", sdf.format(tweet.getCreatedAt())));
      listItem.add(new ExternalLink("user_link", "http://twitter.com/" + tweet.getFromUser(), tweet.getFromUser()));
      listItem.add(new MultiLineLabel("text", tweet.getText()));
    }
  }
}
