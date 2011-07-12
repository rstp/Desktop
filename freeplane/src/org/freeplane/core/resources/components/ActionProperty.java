/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2008 Dimitry Polivaev
 *
 *  This file author is Dimitry Polivaev
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.freeplane.core.resources.components;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.freeplane.core.ui.OptionPanelButtonListener;
import org.freeplane.core.util.TextUtils;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Stefan Langer
 * 27.12.2008
 */
public class ActionProperty extends PropertyBean implements IPropertyControl {
	private static RowSpec rowSpec;
	private Icon icon;
	private String labelText;
	JButton mButton = new JButton();	
	private OptionPanelButtonListener buttonListener = new OptionPanelButtonListener();

	/**
	 */
	public ActionProperty(final String name) {
		super(name);
	}

	@Override
	public String getValue() {
		return mButton.getText();
	}

	public void layout(final DefaultFormBuilder builder) {
		mButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				buttonListener.actionPerformed(arg0);
			}
		});
		mButton.setActionCommand(getLabel());
		if (labelText == null) {
			labelText = TextUtils.getOptionalText(getLabel());
		}
		mButton.setText("labelText");
		mButton.setEnabled(true);		
		
		if (ActionProperty.rowSpec == null) {
			ActionProperty.rowSpec = new RowSpec("fill:20dlu");
		}
		if (3 < builder.getColumn()) {
			builder.appendRelatedComponentsGapRow();
			builder.appendRow(ActionProperty.rowSpec);
			builder.nextLine(2);
		}
		else {
			builder.nextColumn(2);
		}		
		builder.nextColumn(2);
		builder.add(mButton);
	}

	public void setEnabled(final boolean pEnabled) {
		mButton.setEnabled(pEnabled);
	}

	public void setImageIcon(final Icon icon) {
		this.icon = icon;
	}

	public void setLabelText(final String labelText) {
		this.labelText = labelText;
	}

	@Override
	public void setValue(final String value) {
	}

	@Override
    protected Component[] getComponents() {
	    return new Component[]{mButton};
    }
}
