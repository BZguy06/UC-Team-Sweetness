/*
 * PriorityQueueInterfaceView.java
 *
 * public PriorityQueueInterfaceView(SingleFrameApplication app)
 *      Default constructor
 * public void showAboutBox()
 *      Shows the about box
 * public void stopQueue()
 *      Prevents the application from generating more events
 * class ShowQueueTask extends TimerTask
 *      Allows the application to fire one event every 2 seconds
 * public String randomName()
 *      Creates random, 5-character Strings for making event names
 *
 * @author Seth
 *
 */

package priorityqueueinterface;

import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import javax.swing.JDialog;
import javax.swing.JFrame;
import priorityqueue.*;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JOptionPane;


/**
 * The application's main frame.
 */
public class PriorityQueueInterfaceView extends FrameView {

    public PriorityQueueInterfaceView(SingleFrameApplication app) {
        super(app);

        initComponents();
        
        queue = new PriorityQueue();
        Random generator = new Random();

        for (int i = 0; i < 20; i++)
        {
            Event e = new Event(generator.nextInt(50), randomName());
            queue.push(e);
        }

        t = new java.util.Timer();
        t.scheduleAtFixedRate(new ShowQueueTask(), 0, 4000);
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = PriorityQueueInterfaceApp.getApplication().getMainFrame();
            aboutBox = new PriorityQueueInterfaceAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        PriorityQueueInterfaceApp.getApplication().show(aboutBox);
    }

    @Action
    public void stopEvents() {
        stopped = true;
        jButton1.setText("STOPPED");
        jButton1.setEnabled(false);
    }

    public String randomName() {
        Random generator = new Random();
        return Character.toString((char)(generator.nextInt(74) + 48)) + (char)(generator.nextInt(74) + 48) + (char)(generator.nextInt(74) + 48) + (char)(generator.nextInt(74) + 48) + (char)(generator.nextInt(74) + 48);
    }

    class ShowQueueTask extends TimerTask {
        public void run() {
            if (!queue.isEmpty)
            {
                Event e = queue.pop();
                jTextArea1.insert("Info: " + e.getInfo() + ", Priority: " + Integer.toString(e.getPriority()) + "\n", 0);

                // run if the stop button has not been pressed
                if (stopped == false) {
                    int ePriority = e.getPriority();
                    Random generator = new Random();
                    int i = generator.nextInt(10);

                    // .5 probability
                    if (i <= 4) {
                        Event someEvent;
                        if ((ePriority + (3 * ePriority)) < 200) {
                            someEvent = new Event(ePriority + (3 * ePriority), randomName());
                        } else {
                            someEvent = new Event(200, randomName());
                        }
                        queue.push(someEvent);
                        jTextArea1.insert("Created new event: " + someEvent.getInfo() + ", Priority: " + Integer.toString(someEvent.getPriority()) + "\n", 0);
                    }

                    // .2 probability
                    else if (i > 4 && i <= 7) {
                        Event someEvent;
                        if ((ePriority + (10 * ePriority)) < 200) {
                            someEvent = new Event(ePriority + (10 * ePriority), randomName());
                        } else {
                            someEvent = new Event(200, randomName());
                        }
                        queue.push(someEvent);
                        jTextArea1.insert("Created new event: " + someEvent.getInfo() + ", Priority: " + Integer.toString(someEvent.getPriority()) + "\n", 0);
                    }

                    // Ask the user if they want to create a new event
                    int response = JOptionPane.showConfirmDialog(null, "Do you want to insert a new event?");
                    if (response == 0) {
                        String eventName = JOptionPane.showInputDialog("Give the event a 5-character identifier.");
                        while (eventName.length() > 5) {
                            eventName = JOptionPane.showInputDialog("Give the event a 5-character identifier.");
                        }

                        int priority = Integer.parseInt(JOptionPane.showInputDialog("Choose a priority between " + Integer.toString(e.getPriority()) + " and 200."));
                        while (priority > 200 || priority < e.getPriority()) {
                            priority = Integer.parseInt(JOptionPane.showInputDialog("Choose a priority between " + Integer.toString(e.getPriority()) + " and 200."));
                        }

                        Event userEvent = new Event(priority, eventName);
                        queue.push(userEvent);
                        jTextArea1.insert("Created new event: " + userEvent.getInfo() + ", Priority: " + Integer.toString(userEvent.getPriority()) + "\n", 0);
                    }
                }
            } else {
                // if the queue is empty, stop the timer
                t.cancel();
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(priorityqueueinterface.PriorityQueueInterfaceApp.class).getContext().getActionMap(PriorityQueueInterfaceView.class, this);
        jButton1.setAction(actionMap.get("stopEvents")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(priorityqueueinterface.PriorityQueueInterfaceApp.class).getContext().getResourceMap(PriorityQueueInterfaceView.class);
        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private JDialog aboutBox;

    private PriorityQueue queue;
    private java.util.Timer t;
    private boolean stopped;
}
