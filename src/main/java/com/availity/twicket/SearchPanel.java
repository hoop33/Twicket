package com.availity.twicket;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.StringValidator.MinimumLengthValidator;

/**
 * 
 * @author rwarner
 */
public class SearchPanel extends Panel {
  private static final long serialVersionUID = -8610733111417498232L;

  public SearchPanel(String id) {
    super(id);
    add(new SearchForm("searchForm"));
  }

  private class SearchForm extends StatelessForm<SearchParams> {
    private static final long serialVersionUID = -5253297831508495384L;

    private SearchForm(final String id) {
      super(id);
      setModel(new CompoundPropertyModel<SearchParams>(new SearchParams()));
      add(new Label("searchLabel", "Search Terms:"));
      add(new TextField<String>("searchString").add(new MinimumLengthValidator(1)));
    }

    @Override
    protected final void onSubmit() {
      setResponsePage(new HomePage(getModelObject()));
    }
  }
}
