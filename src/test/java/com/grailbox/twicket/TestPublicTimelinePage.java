package com.grailbox.twicket;

import junit.framework.TestCase;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;

public class TestPublicTimelinePage extends TestCase {
  private WicketTester tester;

  @Override
  public void setUp() {
    tester = new WicketTester(new WicketApplication());
  }

  public void testRenderMyPage() {
    tester.startPage(PublicTimelinePage.class);
    tester.assertRenderedPage(PublicTimelinePage.class);
    tester.assertComponent("home", BookmarkablePageLink.class);
    tester.assertComponent("feedback", FeedbackPanel.class);
    tester.assertComponent("tweetListPanel", TweetListPanel.class);
  }
}
