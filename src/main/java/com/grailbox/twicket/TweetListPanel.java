package com.grailbox.twicket;

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

import winterwell.jtwitter.TwitterException;
import winterwell.jtwitter.Twitter.Status;

public class TweetListPanel extends Panel {
  private static final long serialVersionUID = -4056830780523403665L;

  public TweetListPanel(String id, TweetRetriever tweetRetriever) {
    super(id);
    add(new TweetListView("tweetListView", new TweetListLDM(tweetRetriever)));
  }
  
  private class TweetListLDM extends LoadableDetachableModel<List<Status>> {
    private static final long serialVersionUID = 1L;
    private TweetRetriever tweetRetriever;
    
    public TweetListLDM(final TweetRetriever tweetRetriever) {
      this.tweetRetriever = tweetRetriever;
    }

    @Override
    protected List<Status> load() {
      List<Status> list = Collections.emptyList();
      if (tweetRetriever != null) {
        try {
          list = tweetRetriever.retrieveTweets();
        } catch (TwitterException e) {
          error(e.getMessage());
        }
      }
      return list;
    }
  }

  private class TweetListView extends ListView<Status> {
    private static final long serialVersionUID = -8745103599338918600L;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    private TweetListView(String id, IModel<List<Status>> model) {
      super(id, model);
    }

    @Override
    protected void populateItem(final ListItem<Status> listItem) {
      Status status = listItem.getModelObject();
      listItem.add(new ExternalImageUrl("image_url", status.getUser().getProfileImageUrl().toString()));
      listItem.add(new Label("date", sdf.format(status.getCreatedAt())));
      listItem.add(new ExternalLink("user_link", "http://twitter.com/" + status.getUser().getScreenName(), status.getUser().getScreenName()));
      listItem.add(new MultiLineLabel("text", status.getText()));
    }
  }
}
