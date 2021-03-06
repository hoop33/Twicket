package com.grailbox.twicket;

import com.grailbox.twicket.HomePage;
import com.grailbox.twicket.WicketApplication;

import junit.framework.TestCase;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends TestCase {
  private WicketTester tester;

  @Override
  public void setUp() {
    tester = new WicketTester(new WicketApplication());
  }

  public void testRenderMyPage() {
    tester.startPage(HomePage.class);
    tester.assertRenderedPage(HomePage.class);
    tester.assertComponent("publicTimeline", BookmarkablePageLink.class);
    tester.assertComponent("feedback", FeedbackPanel.class);
    tester.assertComponent("searchPanel", SearchPanel.class);
    tester.assertComponent("tweetListPanel", TweetListPanel.class);
  }
}
