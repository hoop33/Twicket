package com.grailbox.twicket;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.wicket.markup.html.basic.MultiLineLabel;

/**
 *
 * @author rwarner
 */
public class MultiLineLinkLabel extends MultiLineLabel {
  public MultiLineLinkLabel(String id, String label) {
    super(id, MultiLineLinkLabel.embedLinks(label));
    setEscapeModelStrings(false);
  }

  protected static String embedLinks(String str) {
    StringBuffer sb = new StringBuffer();
    Pattern p = Pattern.compile("https?://\\S*");
    Matcher m = p.matcher(str);
    while (m.find()) {
      String link = m.group();
      m.appendReplacement(sb, "<a href='" + link + "'>" + link + "</a>");
    }
    m.appendTail(sb);
    return sb.toString();
  }
}
