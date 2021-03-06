package edu.ucsf.rbvi.cyBrowser.internal.tasks;

import java.util.Properties;
import javax.swing.SwingUtilities;

import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.application.swing.CytoPanelName;
import org.cytoscape.application.swing.CytoPanelState;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;


import edu.ucsf.rbvi.cyBrowser.internal.model.CyBrowser;
import edu.ucsf.rbvi.cyBrowser.internal.model.CyBrowserManager;
import edu.ucsf.rbvi.cyBrowser.internal.view.ResultsPanelBrowser;
import edu.ucsf.rbvi.cyBrowser.internal.view.SwingBrowser;

public class HideBrowserTask extends AbstractEmptyObservableTask {

	@Tunable (description="Window ID", 
	          longDescription="The ID for the browser window to hide",
	          exampleStringValue="Window 1",
	          context="nogui")
	public String id = null;

	final CyBrowserManager manager;

	public HideBrowserTask(CyBrowserManager manager) {
		this.manager = manager;
	}

	public void run(TaskMonitor monitor) {
		CyBrowser browser = manager.getBrowser(id);
		// System.out.println("Hiding window "+id+" browser "+browser);
		if (browser instanceof ResultsPanelBrowser)
			manager.unregisterCytoPanel((ResultsPanelBrowser)browser);
		else if (browser instanceof SwingBrowser)
			((SwingBrowser)browser).dispose();
		manager.removeBrowser(id);
	}

	@ProvidesTitle
	public String getTitle() {
		return "Hiding Cytoscape Web Browser";
	}
}
