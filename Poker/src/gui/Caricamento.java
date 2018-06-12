package gui;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class Caricamento {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   
   public Caricamento(){
      prepareGUI();
      showProgressBarDemo();
      //System.exit(0);
   }
   
   private void prepareGUI(){
      mainFrame = new JFrame("CARICAMENTO POKER");
      mainFrame.setSize(400,150);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setLayout(new GridLayout(3, 1));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(400,400);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   private JProgressBar progressBar;
   private Task task;
   private JButton startButton;
   private JTextArea outputTextArea;
   
   private void showProgressBarDemo(){
      headerLabel.setText("Caricanto poker in corso..."); 
      progressBar = new JProgressBar(0, 100);
      progressBar.setValue(0);
      progressBar.setStringPainted(true);
      startButton = new JButton("Start");    
      
      startButton.addActionListener((ActionEvent e) -> {
          task = new Task();
          task.start();
      });
      //mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
      controlPanel.add(startButton);
      controlPanel.add(progressBar);
      mainFrame.setVisible(true);  
   }

   private class Task extends Thread {    
      public Task(){
      }
      @Override
      public void run(){
         for(int i =0; i<= 100; i+=10){
            final int progress = i;
            
            SwingUtilities.invokeLater(() -> {
                progressBar.setValue(progress);
                outputTextArea.setText(outputTextArea.getText() );
            });
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {}
         }
         mainFrame.dispose();
      }
   }   
}